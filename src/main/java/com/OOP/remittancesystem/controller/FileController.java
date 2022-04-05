package com.OOP.remittancesystem.controller;

import org.springframework.core.io.Resource;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import com.OOP.remittancesystem.dao.RemittanceDAO;
import com.OOP.remittancesystem.dao.TransactionDAO;
import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.entity.Transactions;
import com.OOP.remittancesystem.fileHandling.FileResponse;
import com.OOP.remittancesystem.fileHandling.OpenCSVReadAndParseToBean;
import com.OOP.remittancesystem.service.CompanySorter;
import com.OOP.remittancesystem.service.FileStorageService;
import com.OOP.remittancesystem.service.HeaderService;
import com.OOP.remittancesystem.service.RemittanceService;
import com.OOP.remittancesystem.service.ValidationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CompanySorter companySorter;
    
    @Autowired 
    private RemittanceDAO remittanceDAO;
    
    @Autowired
    private OpenCSVReadAndParseToBean openCSV;

    @Autowired
    private HeaderService headerService;

    @Autowired
    private RemittanceService remittanceService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private TransactionDAO transactionDAO;

    @PostMapping("/extractheaders")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file) {

        // ----todo call clearSpoil
        validationService.clearWhatSpoil();

        //store the file in the server
        //e.g. localhost:8080/files/dummy.csv
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();

        //create a fileResponse for json output at the end
        ArrayList<String> headers = headerService.extractHeaders(fileDownloadUrl);
        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUrl, file.getContentType(), file.getSize(), headers);
                
        System.out.println(headers);
        System.out.println("over here");

        return new ResponseEntity <FileResponse> (fileResponse, HttpStatus.OK);
}

    @PostMapping        
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponse> processFile(@RequestParam String fileName,@RequestParam String fileDownloadUrl,@RequestParam String username)
    throws Throwable{

        //take in the file which was earlier put in server.. and sort them in an arrayList by company
        //e.g. <EverywhereRemit: [row1],[row2],[row3], PaymentGo: [row1]...>
        Map <String, ArrayList<String>> dataByCompany = companySorter.sortCompany(fileName, fileDownloadUrl);

        // create and store the company data into csv files
        Map <String, String> companyPath = companySorter.createCompanyCSV(dataByCompany);

        //loop through all identified companies in the csv file
        Iterator <String> companyIter = companyPath.keySet().iterator();

        Map <String, List<Remittance>> remitEntriesDB = new HashMap<String, List<Remittance>>();

        while (companyIter.hasNext()){
        
            String company = companyIter.next();

            System.out.println(company);

            //for each company csv file.. scan through the headers and rename them into SSOT format
            openCSV.mapKeywords(company, companyPath.get(company), "ssot");

            //for each company csv.. cast them into a list of remittance (csv -> list of objects)
            List<Remittance> remittanceList = openCSV.mapCSV(companyPath.get(company), company);

            remitEntriesDB.put(company, remittanceList);
            //loop each data (row and insert it to db)
            openCSV.mapKeywords(company, companyPath.get(company), "api");
            Map <String, List<String>> remittanceMap = openCSV.csvToHashMap(company, companyPath.get(company));

            //System.out.print(remittanceService.toJSON(remittanceMap));
            remittanceMap.entrySet().forEach(entry -> {
                        try {

                                for (int i = 0; i <= entry.getValue().size()-1; i++){
                                        String value = entry.getValue().get(i);

                                        validationService.sizeValidation(value,entry.getKey(), company);
                                        validationService.regexValidation(value,entry.getKey(), company);

                                }

                        } catch (Exception e){
                                System.out.println(e);
                                
                }
                        
            });
        }

        // validation 
        if (validationService.getSpoil()){
                ArrayList<String> spoilStore = validationService.getWhatSpoil();
                FileResponse spoilResponse = new FileResponse(spoilStore);
                return new ResponseEntity<FileResponse>(spoilResponse, HttpStatus.OK);
        } 
        
        companyIter = companyPath.keySet().iterator();
        while(companyIter.hasNext()){
                String company = companyIter.next();
                List<Remittance> remittanceList = remitEntriesDB.get(company);
                Map <String, List<String>> remittanceMap = openCSV.csvToHashMap(company, companyPath.get(company));
                ArrayList<String> jsonBody = remittanceService.toJSON(remittanceMap);
                
                
                String status = "";
                for(int i = 0; i < jsonBody.size(); i++){
                        ResponseEntity<String> response = sendTransaction(company,jsonBody.get(i));
                        int messageLen = response.getBody().length();
                        status = response.getBody().substring(21, messageLen - 2);
                        // System.out.println(status);
                }  

                // save to db, row by row
                for (Remittance remittance: remittanceList) {
                        remittanceDAO.save((Remittance) remittance);
                }

                Transactions transaction = new Transactions(username, fileName, company, status);
                transactionDAO.save(transaction);
        }


        //return successful upload entity
        validationService.resetSpoil();


        //return success msg, if code reaches here, its success
        ArrayList<String> responseList = new ArrayList<String>();
        responseList.add("Success");
        FileResponse successResponse = new FileResponse(responseList);
        return new ResponseEntity<FileResponse>(successResponse, HttpStatus.OK);

    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            // System.out.println(contentType);
        }catch (IOException ex){
            System.out.println("count not determine fileType");
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


    

    @PostMapping("/call")
    public ResponseEntity<String> callTest() {

// request url
        String url = "https://prelive.paywho.com/api/smu_sandbox";

// create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

// create headers
        HttpHeaders headers = new HttpHeaders();
// set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
// set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// request body parameters
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        map.put("title", "Spring Boot 101");
        map.put("body", "A powerful tool for building web apps.");

// build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

// send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

// check response
        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return response;
    }
    

    @PostMapping("/send")
    public ResponseEntity<String> sendTransaction(String company, String jsonbody) {

        // request url
        String url = "https://prelive.paywho.com/api/smu_send_transaction";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // request body parameters
        // Map<String, Object> map = new HashMap<>();
        // map.putAll(renamedMap);
        //add HashMap into arg
        // build the request
        try {
                // Map<String, Object> map = new HashMap<>();
                // map.put("access_token","C9zC7BaCBZPpXbBnMJHX14XeWKsCHg");
                // map.put("api_name",company.toLowerCase());
                // JSONObject json = new JSONObject(jsonbody.get(0).toString());
                
                // map.put("payload", json.toString());

                // HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

                // ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

                // if (response.getStatusCode() == HttpStatus.CREATED) {
                //         System.out.println("Request Successful");
                //         System.out.println(response.getBody());
                // } else {
                //         System.out.println("Request Failed");
                //         System.out.println(response.getStatusCode());
                // }
                JSONObject obj = new JSONObject();
                obj.put("access_token","C9zC7BaCBZPpXbBnMJHX14XeWKsCHg");
                obj.put("api_name",company.toLowerCase());
                System.out.println("Here!!" + jsonbody);
                JSONObject json = new JSONObject(jsonbody.toString());
                
                obj.put("payload", json);
                // add HashMap into arg
                // build the request
                HttpEntity<String> entity = new HttpEntity<String>(obj.toString(), headers);
                System.out.print("passed me");
                System.out.print(entity);
                // send POST request
                // String response = restTemplate.postForObject(url, entity, String.class);
                // check response
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                System.out.println("Response below");
                System.out.println(response);
                if (response.getStatusCode() == HttpStatus.CREATED) {
                        System.out.println("Request Successful");
                        System.out.println(response.getBody());
                } else {
                        System.out.println("Request Failed");
                        System.out.println(response.getStatusCode());
                }

                // ResponseEntity<String> output = new ResponseEntity<String>(response.data);
                // System.out.println(response1);
                // return null;
                return response;
        } 
        catch (JSONException e) {
                //TODO: handle exception
                System.out.println("wrong la");
                System.out.println(e.getMessage());
                return null;                       
        }
    }
}