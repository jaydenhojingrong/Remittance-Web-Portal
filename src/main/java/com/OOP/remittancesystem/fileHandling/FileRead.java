package com.OOP.remittancesystem.fileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import com.OOP.remittancesystem.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class FileRead {
    @Autowired
    private static FileStorageService fileStorageService;

    public static void main(String[] args) {
        System.out.println(fileStorageService.loadFileAsResource("../../../../../../../everywhereDummy.csv"));
    }
    public static void readFile(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        if (file.exists()){
            Scanner fileIn = new Scanner(file);

            while(fileIn.hasNext()){
                String currentLine = fileIn.nextLine();
                System.out.println(currentLine);
            }
            fileIn.close();
        }
    }
    
}
