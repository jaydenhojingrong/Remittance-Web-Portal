package com.OOP.studentsystem.fileHandling;

import com.OOP.studentsystem.service.StudentService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RemittanceService remittanceService;

    @PostMapping
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
}
