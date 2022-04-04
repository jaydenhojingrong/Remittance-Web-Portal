package com.OOP.remittancesystem.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
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
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

@Service
public class CompanySorter {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private HeaderService headerService;

    //takes in the uploaded file and sort them by companies
    //each map entry contains one company with all the csv rows (String) as values
    public Map <String, ArrayList<String>> sortCompany(String fileName, String fileDownloadUrl){
        // Map <String, String> companyData = new HashMap <String, String>();
        Map <String, ArrayList<String>> companyData = new HashMap <String, ArrayList<String>>();

        // map amount to company using a treeset
        TreeSet<AmountRange> amountSet =  amountMapping();
        //get all possible current headers for the SSOT amount
        List<String> amountNames = headerService.findBySsotHeader("amount");

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
        try (Scanner fIn = new Scanner(file, "UTF-8")){
            String header = null;
            int amountCol = 0;
            while (fIn.hasNext()){
                String csvRow = fIn.nextLine();
                if (readHeader){
                    Scanner scHeaders = new Scanner(csvRow);
                    scHeaders.useDelimiter(",");
                    while (scHeaders.hasNext()){
                        //find the column amount in various naming conventions
                        String ssotHeader = scHeaders.next();
                        if (!amountNames.contains(ssotHeader)){    
                            //searching for the amount column
                            amountCol ++;
                        }
                        else {
                            //break and stop counting once found
                            break;
                        }
                    }
                    readHeader = false;
                    scHeaders.close();
                    header = csvRow;
                }

                else{
                    String[] csvCols = csvRow.split(",");
                    //check whether amount col exist
                    if (csvCols.length == amountCol){
                        //TODO: Maars help plz. If the amount column is not found need to throw exception to frontend. cannot process the file
                        System.out.println("Amount column not found");
                        break;
                    }

                    //identify the company name by checking its amount
                    String currentCompany = (amountSet.floor(new AmountRange(Long.parseLong(csvCols[amountCol]))).getCompany());
                    //does company exist in the companyData? if not then add in a new map entry
                    if(!companyData.containsKey(currentCompany)){
                        //key = company name, value = row of headers with the next row of data
                        companyData.put(currentCompany, new ArrayList<String>(Arrays.asList(header, csvRow)));
                    }
                    //already exist? get the arraylist and add the next row
                    else{
                        companyData.get(currentCompany).add(csvRow);
                    }
                }

            }
            // printing a hashmap (for debugging)
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
            
            try (FileInputStream input = new FileInputStream(tempFile)){
                MultipartFile multipartFile = new MockMultipartFile("file",tempFile.getName(), "text/csv", IOUtils.toByteArray(input));
                fileStorageService.storeFile(multipartFile);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(company+".csv")
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
