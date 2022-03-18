package com.OOP.remittancesystem.controller;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.fileHandling.FileResponse;
import com.OOP.remittancesystem.fileHandling.OpenCSVReadAndParseToBean;
import com.OOP.remittancesystem.fileHandling.RemittanceService;
import com.OOP.remittancesystem.service.FileStorageService;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RemittanceService remittanceService;


    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();

        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUrl, file.getContentType(), file.getSize());

        OpenCSVReadAndParseToBean.mapKeywords(fileName,  file.getContentType(), fileDownloadUrl);

        System.out.println(fileDownloadUrl);
        List<Remittance> remittanceList = OpenCSVReadAndParseToBean.mapCSV(fileDownloadUrl);
        System.out.println(remittanceList);
        for (Remittance remittance: remittanceList) {
                // remittanceService.save((EverywhereRemit) remittance)
            remittanceService.saveRemittance((EverywhereRemit) remittance);
            //Remittance remittance = csvUserIterator.next();
            System.out.println("Country : " + remittance.getsCountry());
            System.out.println("First Name : " + remittance.getsFirstName());
            System.out.println("Last Name : " + remittance.getsLastName());
            System.out.println("==========================");
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
