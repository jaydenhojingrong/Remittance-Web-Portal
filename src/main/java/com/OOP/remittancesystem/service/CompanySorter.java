package com.OOP.remittancesystem.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

@Service
public class CompanySorter {

    @Autowired
    private FileStorageService fileStorageService;

    public Map <String, ArrayList<String>> sortCompany(String fileName, String fileDownloadUrl){
        // Map <String, String> companyData = new HashMap <String, String>();
        Map <String, ArrayList<String>> companyData = new HashMap <String, ArrayList<String>>();

        // map amount to company
        TreeSet<AmountRange> amountSet =  amountMapping();

        boolean readHeader = true;
        String fullFileName = fileName;

        //create file of the local csv file (in root folder)
        File file = new File(fullFileName);

        //create new inputstream of the server side csv file
        //e.g. localhost:8080/files/everywhereRemit.csv
        //also create outputStream of the local file (in the root folder)
        try (InputStream input = new URL(fileDownloadUrl).openStream();
            FileOutputStream out = new FileOutputStream(file);) {

            file.deleteOnExit();
            IOUtils.copy(input, out);

        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }

        //open scanner to read the copied csv server file
        try (Scanner fIn = new Scanner(file)){
            String header = null;
            int amountCol = 0;
            while (fIn.hasNext()){
                String csvRow = fIn.nextLine();
                if (readHeader){
                    Scanner scHeaders = new Scanner(csvRow);
                    scHeaders.useDelimiter(",");
                    while (scHeaders.hasNext()){
                        //find the column named amount!
                        String ssotHeader = scHeaders.next();
                        if (!ssotHeader.equals("amount")){
                            amountCol ++;
                        }
                        else {
                            break;
                        }
                    }
                    readHeader = false;
                    scHeaders.close();
                    header = csvRow;
                }

                else{
                    //outside the header
                    //check the length of csvCols
                    //if length == amount
                    //throw exception
                    String[] csvCols = csvRow.split(",");
                    //check whether amount col exist
                    if (csvCols.length == amountCol){
                        //TODO: throw and handle exception
                        System.out.println("Amount column not found");
                        break;
                    }

                    String currentCompany = (amountSet.floor(new AmountRange(Long.parseLong(csvCols[amountCol]))).getCompany());
                    if(!companyData.containsKey(currentCompany)){
                        //create add new hashmap entry
                        //put in current row of data
                        companyData.put(currentCompany, new ArrayList<String>(Arrays.asList(header, csvRow)));
                    }
                    else{
                        companyData.get(currentCompany).add(csvRow);
                    }
                }

            }

            // companyData.entrySet().forEach(entry -> {
            //     System.out.println(entry.getKey() + " " + entry.getValue());
            // });
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return companyData;
    }

    public Map <String, String> createCompanyCSV(Map <String, ArrayList <String>> dataByCompany){
        Map <String, String> companyPath = new HashMap <String, String>();

        Iterator <String> companyDataIter = dataByCompany.keySet().iterator();
        //loop through hashmap of company data
        while(companyDataIter.hasNext()){
            String company = companyDataIter.next();
            ArrayList <String> companyData = dataByCompany.get(company);

            //use company name to create csv outputstream
            //create a temp csv file to input value inside
            File tempFile = new File("./"+ company + ".csv");
            try (PrintStream writer = new PrintStream(new FileOutputStream("./"+ company + ".csv", false))){

                for (String row : companyData){
                    writer.println(row);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            
            try{
                FileInputStream input = new FileInputStream(tempFile);
                MultipartFile multipartFile = new MockMultipartFile("file",tempFile.getName(), "text/csv", IOUtils.toByteArray(input));
                fileStorageService.storeFile(multipartFile);
            }
            catch(Exception e){
                System.out.println("its here!!!!!!" + e.getMessage());
            }

            String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(company)
                .toUriString();

            companyPath.put(company, fileDownloadUrl);
            
        }

        return companyPath;
    }

    public TreeSet <AmountRange> amountMapping(){
        TreeSet<AmountRange> amountSet = new TreeSet<>();

        // 1 SGD - 3000 SGD : FinanceNow
        // 3001 SGD - 6000 SGD : EverywhereRemit
        // 6001 SGD - 10000 SGD : PaymentGo
        amountSet.add(new AmountRange("FinanceNow", 0));
        amountSet.add(new AmountRange("EverywhereRemit", 3001));
        amountSet.add(new AmountRange("PaymentGo", 6001));

        return amountSet;
    }
}
