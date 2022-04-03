package com.OOP.remittancesystem.controller;

import org.springframework.core.io.Resource;
import org.mockito.internal.stubbing.answers.ThrowsException;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file)
    throws Throwable{

        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();

        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUrl, file.getContentType(), file.getSize());

        Map <String, ArrayList<String>> dataByCompany = companySorter.sortCompany(fileName, fileDownloadUrl);

        // create and store the company data into csv files
        Map <String, String> companyPath = companySorter.createCompanyCSV(dataByCompany);

        Iterator <String> companyIter = companyPath.keySet().iterator();
        while (companyIter.hasNext()){
            String company = companyIter.next();

            openCSV.mapKeywords(company, companyPath.get(company));

            List<Remittance> remittanceList = openCSV.mapCSV(companyPath.get(company), company);
            for (Remittance remittance: remittanceList) {

                try {
                    remittanceDAO.save((Remittance) remittance);
                } catch (javax.validation.ConstraintViolationException e){
                    String message= "";
                    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                    for (ConstraintViolation<?> violation : violations) {
                    message = violation.getMessage() + " ";
                    //  instead of just printing throw the error with column:
                    System.out.println(message + "throwing the error here now!!!!");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
                    // return new ResponseEntity.status(HttpStatus.CREATED).body("HTTP Status will be CREATED (CODE 201)\n");
                    
                    }
                }
            }
        }
        

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


}
