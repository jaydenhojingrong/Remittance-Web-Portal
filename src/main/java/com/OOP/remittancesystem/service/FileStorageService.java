package com.OOP.remittancesystem.service;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.OOP.remittancesystem.exception.FileStorageException;
import com.OOP.remittancesystem.exception.MyFileNotFoundException;
import com.OOP.remittancesystem.fileHandling.FileStorageProperties;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    // auto injecting file properties
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath();

        // String stringPath = "/Users/shawnteo/Documents/GitHub/Remittance-Web-Portal";
        // String stringPath = "/Users/shawnteo/Documents/GitHub/Remittance-Web-Portal";
        // this.fileStorageLocation = Paths.get(stringPath);


        // this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath();

        try {
            // create directory if doesnt exist
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("could not create directory to upload");
        }
    }

    // stores the files
    public String storeFile(MultipartFile file){
        // normalizes filename
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // adding file location
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file" + fileName + "please try again", ex);
        }
    }

    // load the files
    public Resource loadFileAsResource(String fileName){
        try  {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            // convert urlresource to a resource
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return  resource;
            } else {
                // custom exception
                throw new MyFileNotFoundException("file not found" + fileName);
            }
        } catch (MalformedURLException ex){
            throw new MyFileNotFoundException("file not found" + fileName);
        }
    }



}
