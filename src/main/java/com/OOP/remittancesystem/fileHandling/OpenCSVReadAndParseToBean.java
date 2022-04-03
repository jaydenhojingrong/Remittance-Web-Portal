
package com.OOP.remittancesystem.fileHandling;

import com.OOP.remittancesystem.entity.Remittance;
import com.OOP.remittancesystem.service.FileStorageService;
import com.OOP.remittancesystem.service.HeaderService;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.List;


@Component
public class OpenCSVReadAndParseToBean {

    @Autowired
    private HeaderService headerservice;

    @Autowired
    private FileStorageService fileStorageService;

    //responsible for taking in a csv file, parse into a bean and then into a list of remittance
    public List<Remittance> mapCSV(String fileDownloadUrl, String company) {
        List<Remittance> remittanceList = null;
        
        try (InputStream input = new URL(fileDownloadUrl).openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");)
            {   
                Class <?> companyClass = getClassByString(company);

                //convert csv data into rimittance bean
                CsvToBean<Remittance> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(companyClass)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                        
                //parse bean into a list of remittance
                remittanceList = csvToBean.parse();
                return remittanceList;
            }
      
        catch(IOException e){
            System.out.println("File missing");
        }

        catch(ClassNotFoundException e){
            System.out.printf("Class: %s not found. \n Please check your input form-data - company - when calling files api", company);
        }

        catch(Exception e){
            System.out.println("Error msg: " + e.getMessage());
            System.out.println("Entity name and db name does not match. Check entity.");

        }
        
        return remittanceList;
    }

    //takes in a String and returns the its Class object 
    //throws classnotfound if there is no such class found in this project
    // e.g. data must be EverywhereRemit, FinanceNow or PaymentGo
    public Class <?> getClassByString(String company) throws ClassNotFoundException{
        return Class.forName("com.OOP.remittancesystem.entity." + company);
    }
    
    public void mapKeywords(String company, String fileDownloadUrl) {
        boolean readHeader = false;
        String fullFileName = company + ".csv";

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

        //create a temp csv file to input value inside
        File tempFile = new File("./temp.csv");
        String newHeaderName;
        String newHeaders = "";

        //create new scanner of the local csv file (root folder)
        try (Scanner fIn = new Scanner(file)){
            //create a printerstream from temp.csv to write values into it
            PrintStream writer = new PrintStream(new FileOutputStream("./temp.csv", false));

            //loop scanner till no more lines
            while (fIn.hasNext()) {
               String csvLine = fIn.nextLine();
               //readHeader will only be true at the first iteration of loop
                if (!readHeader){
                    //create scanner to delimit all the headers
                    Scanner scHeaders = new Scanner(csvLine);
                    scHeaders.useDelimiter(",|\r\n|\n");
                    while (scHeaders.hasNext()){
                        //look up db and rename into SSOT header value 
                        String currentHeader = scHeaders.next();
                        newHeaderName = renameHeader(currentHeader, company);
                        // System.out.println(currentHeader + " CHANGED TOOOOO-----> " + newHeaderName);
                        newHeaders += newHeaderName + ",";
                    }
                    //slice off last "comma" in the string   
                    //populate header values from user uploaded csv into temp.csv
                    writer.println(newHeaders.substring(0,newHeaders.length()-1));
                    readHeader = true;
                    scHeaders.close();

               }
               else{
                //code will flow here if it is not at the header row
                //populate non-header data from user uploaded csv into temp.csv
                writer.println(csvLine);
               }
            //    fIn.nextLine();
            }	
            writer.close();

            // Path fileToDeletePath = Paths.get("everywhereDummy.csv");
            // System.out.println("it here!!!!!!:                    "  +fileToDeletePath);
            // Files.delete(fileToDeletePath);

         } 
         catch(IOException e) {
             e.printStackTrace();
         }
         finally{
             //for debugging
             //delete local file
             //rename temp as whatever name local file was 
            System.out.println("Does file exists?: " + file.exists());
            System.out.println("Was file deleted?: " + file.delete());
            System.out.println("Was file renamed?: " + tempFile.renameTo(file));
            try (FileInputStream input = new FileInputStream(file)){
                MultipartFile multipartFile = new MockMultipartFile("file",file.getName(), "text/csv", IOUtils.toByteArray(input));
                fileStorageService.storeFile(multipartFile);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
         }
    }

    public String renameHeader(String header, String company){
        String renamedHeader;
        try{
            
            // renamedHeader = headerservice.getSsotByCurrentHeader(header).getSsotHeader();
            renamedHeader = headerservice.getSsotByCurrentHeaderAndCompany(header, company).getSsotHeader();
            
        }
        catch(NullPointerException e){
            renamedHeader = null;
            
        }
        // System.out.println("here: " + header);
        // System.out.println("here: " + renamedHeader);
        return renamedHeader;
    }

}