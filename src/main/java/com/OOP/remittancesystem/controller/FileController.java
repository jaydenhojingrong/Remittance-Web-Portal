package com.OOP.remittancesystem.controller;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import com.OOP.remittancesystem.dao.RemittanceDAO;
import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.fileHandling.FileResponse;
import com.OOP.remittancesystem.fileHandling.OpenCSVReadAndParseToBean;
import com.OOP.remittancesystem.service.FileStorageService;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

//     @Autowired
//     private RemittanceService remittanceService;
    
    @Autowired 
    private RemittanceDAO remittanceDAO;
    
    @Autowired
    private OpenCSVReadAndParseToBean openCSV;

    // upload function will take in "multipart files" and "company name" and generate its relative link in the user's system before creating a URI to be downloaded into the system's storage
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file,@RequestParam("company")String company){

        //store csv file in server
        String fileName = fileStorageService.storeFile(file);
        //get download uri for the uploaded csv
        //e.g. localhost:8080/files/everywhereDummy.csv
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
        //uploaded file success get JSON response object showing file name, size etc....
        //example of JSON object:
        // {
        //     "fileName": "paymentDummy.csv",
        //     "fileDownloadURL": "http://localhost:8080/files/paymentDummy.csv",
        //     "fileType": "text/csv",
        //     "size": 606
        // }

        // EDIT THIS TO CHANGE THE RESPONSE 
        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUrl, file.getContentType(), file.getSize());

        //rename into CSV into SSOT headers
        //void method.. but edits the local csv directly
        openCSV.mapKeywords(fileName,  file.getContentType(), fileDownloadUrl);
        
        //cast the csv data into a list of remittance
        List<Remittance> remittanceList = openCSV.csvToRemittanceList(fileDownloadUrl, company);
        //loop the list and...
        for (Remittance remittance: remittanceList) {

            try {
                // insert into db
                remittanceDAO.save((Remittance) remittance);
            } catch (javax.validation.ConstraintViolationException e){
                //capture validation errors in print here!
                //maruni!!!!!!!!!!!!!!!!!
                //save plz!!!!!!! 
                //display to frontend or smth
                String message= "";
                Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                for (ConstraintViolation<?> violation : violations) {
                  message = violation.getMessage() + " ";
                  System.out.println(message);
                }
            }
             
            //call the method here (pass in the remittanceList)
                // remittanceList -> loop
                // check amount to determine the company
                // rename into api naming covention (using headernames)
                // hashmap [company: {data}], [company b: {data}],[companya: {data}], [company b: {data}]
                //pass to yang and char
    
        }

        return new ResponseEntity<FileResponse>(fileResponse, HttpStatus.OK);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            System.out.println(contentType);
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


}
