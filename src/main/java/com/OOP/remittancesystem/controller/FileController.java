package com.OOP.remittancesystem.controller;

import org.springframework.core.io.Resource;
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
import com.OOP.remittancesystem.entity.Remittance;
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
    public ResponseEntity<FileResponse> processFile(@RequestParam("file")MultipartFile file)
    throws Throwable{

        //store the file in the server
        //e.g. localhost:8080/files/dummy.csv
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
        List<String> test = new ArrayList<>();
        //create a fileResponse for json output at the end
        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUrl, file.getContentType(), file.getSize(),test );

        //take in the file which was earlier put in server.. and sort them in an arrayList by company
        //e.g. <EverywhereRemit: [row1],[row2],[row3], PaymentGo: [row1]...>
        Map <String, ArrayList<String>> dataByCompany = companySorter.sortCompany(fileName, fileDownloadUrl);

        // create and store the company data into csv files
        Map <String, String> companyPath = companySorter.createCompanyCSV(dataByCompany);

        //loop through all identified companies in the csv file
        Iterator <String> companyIter = companyPath.keySet().iterator();

        Map <String, List<Remittance>> kensen = new HashMap<String, List<Remittance>>();

       


        while (companyIter.hasNext()){
        
            String company = companyIter.next();

            System.out.println(company);

            //for each company csv file.. scan through the headers and rename them into SSOT format
            openCSV.mapKeywords(company, companyPath.get(company), "ssot");

            //for each company csv.. cast them into a list of remittance (csv -> list of objects)
            List<Remittance> remittanceList = openCSV.mapCSV(companyPath.get(company), company);

            kensen.put(company, remittanceList);
            //loop each data (row and insert it to db)
            openCSV.mapKeywords(company, companyPath.get(company), "api");
            Map <String, List<String>> remittanceMap = openCSV.csvToHashMap(company, companyPath.get(company));

        //     System.out.print(remittanceService.toJSON(remittanceMap));
            remittanceMap.entrySet().forEach(entry -> {
                        try {
                                // System.out.println(company+"\n");
                                // System.out.println(entry.getKey());
                                // System.out.println(entry.getValue());
                                // System.out.println("-----------------------------");


                                for (int i = 0; i <= entry.getValue().size()-1; i++){
                                        String value = entry.getValue().get(i);

                                        boolean sizeBool = validationService.sizeValidation(value,entry.getKey(), company);
                                        boolean regexBool = validationService.regexValidation(value,entry.getKey(), company);

                                        // System.out.println(entry.getKey());
                                        // System.out.print("sizebool");
                                        // System.out.println(sizeBool);
                                        // System.out.print("regexbool");
                                        // System.out.println(regexBool);
                                        // System.out.println(value);
                                        // System.out.println("-----------------------------");
                                        // System.out.println("\n\n\n\n\n");

                                }

                        } catch (Exception e){
                                System.out.println(e);
                                
                        }
                        
                
            });


            // todo put the .save in another for loop

        // -----
        //     for (Remittance remittance: remittanceList) {

        //         try {
        //             remittanceDAO.save((Remittance) remittance);
        //         } 
        //         //handles validation error under entity
        //         //throws json back to front end
        //         catch (javax.validation.ConstraintViolationException e){
        //             String message= "";
        //             Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        //             for (ConstraintViolation<?> violation : violations) {
        //             message = violation.getMessage() + " ";
        //             System.out.println(message + " Throwing the exception.");
        //             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        //             // return new ResponseEntity.status(HttpStatus.CREATED).body("HTTP Status will be CREATED (CODE 201)\n");
        //             }
        //         }
        //     }

            //Convert List<Remittance> remittanceList into  Map<String apiHeader, String value> remittanceMap 
            //call createrequestbody
            //send to hy api
            
        }

        // validation 
        if (validationService.getSpoil()){
                ArrayList<String> spoilStore = validationService.getWhatSpoil();
                FileResponse spoilResponse = new FileResponse(spoilStore);
                return new ResponseEntity<FileResponse>(spoilResponse, HttpStatus.OK);
        } 
        
        // save to db

        //  Map <String, List<Remittance>> kensen = new HashMap<String, List<Remittance>>();
        companyIter = companyPath.keySet().iterator();
        while(companyIter.hasNext()){
                List<Remittance> remittanceList = kensen.get(companyIter.next());
                for (Remittance remittance: remittanceList) {
                        remittanceDAO.save((Remittance) remittance);
                    } 
        }
        // while (companyIter.hasNext()){
        //         String company = companyIter.next();
        //         List<Remittance> remittanceList = openCSV.mapCSV(companyPath.get(company), company);

        //         for (Remittance remittance: remittanceList) {
        //             remittanceDAO.save((Remittance) remittance);
        //         } 
        //     }
        

        //return successful upload entity
        
        return new ResponseEntity<FileResponse>(fileResponse, HttpStatus.OK);
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
    public ResponseEntity<String> sendTransaction(HashMap<String,String> renamedMap) {

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
        Map<String, Object> map = new HashMap<>();
        map.putAll(renamedMap);
        //add HashMap into arg
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


}
