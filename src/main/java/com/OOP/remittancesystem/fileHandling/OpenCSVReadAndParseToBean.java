
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    //takes in the uploaded csv file
    //rename its headers to adhere to the SSOT format
    public void mapKeywords(String company, String fileDownloadUrl, String renameTo) {
        boolean haveReadHeader = false;
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
        try (Scanner fIn = new Scanner(file, "UTF-8")){
            //create a printerstream from temp.csv to write values into it
            PrintStream writer = new PrintStream(new FileOutputStream("./temp.csv", false));

            //loop scanner till no more lines
            while (fIn.hasNext()) {
               String csvLine = fIn.nextLine();
               //haveReadHeader will only be true at the first iteration of loop
                if (!haveReadHeader){
                    //create scanner to delimit all the headers
                    Scanner scHeaders = new Scanner(csvLine);
                    scHeaders.useDelimiter(",|\r\n|\n");
                    while (scHeaders.hasNext()){
                        //look up db and rename into SSOT header value 
                        String currentHeader = scHeaders.next();
                        if (renameTo.equals("ssot")){
                            newHeaderName = renameHeaderToSsot(currentHeader, company);
                        }
                        else {
                            newHeaderName = renameHeaderToApi(currentHeader, company);
                            // System.out.println(company);
                            // System.out.println("{" +currentHeader + "}  CHANGED TO ----->  {" + newHeaderName + "}");
                        }
                        newHeaders += newHeaderName + ",";
                    }
                    //slice off last "comma" in the string   
                    //populate header values from user uploaded csv into temp.csv
                    writer.println(newHeaders.substring(0,newHeaders.length()-1));
                    haveReadHeader = true;
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

    //looks up for current header in the db and returns the ssot one
    public String renameHeaderToSsot(String header, String company){
        String renamedHeader;
        //remove ? that appears in first value in csv
        if (header.charAt(0) == '?'){
            header = (header.substring(1, header.length()));
        }

        try{
            renamedHeader = headerservice.getSsotByCurrentHeaderAndCompany(header, company).getSsotHeader(); 
        }
        //not found? return null
        catch(NullPointerException e){
            renamedHeader = null;
            
        }
        return renamedHeader;
    }

     //looks up for current header in the db and returns the ssot one
     public String renameHeaderToApi(String header, String company){
        String renamedHeader;
        //remove ? that appears in first value in csv
        if (header.charAt(0) == '?'){
            header = (header.substring(1, header.length()));
        }

        try{
            renamedHeader = headerservice.getApiHeaderBySsotHeaderAndCompany(header, company).getApiHeader(); 
        }
        //not found? return null
        catch(NullPointerException e){
            renamedHeader = null;
            
        }
        return renamedHeader;
    }


    //takes in the stored csv file and convert them to hashmap
    //{fieldName: [row1] [row2]}
    public Map <String, List<String>> csvToHashMap(String company, String fileDownloadUrl) {
        String fullFileName = company + ".csv";
        Map<String, List<String>> mappedCols = new HashMap<String, List<String>>();

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

        // convert csv file to fileReader
        try (FileReader fileReader = new FileReader(fullFileName)){

            // to track column headers
            Map<Integer, String> indexCols = new HashMap<Integer, String>();

            //to read the file
            try(BufferedReader br = new BufferedReader(fileReader)) {
                String sCurrentLine;
                boolean firstLine = true;
                //read the next line
                while ((sCurrentLine = br.readLine()) != null) {
                    //split by comma
                    String[] fields = sCurrentLine.split(",");
                    for (int i = 0; i < fields.length; i++) {
                        //for header row, add into indexCols with its index position
                        //create new entry in mapcols
                        if (firstLine) {
                            indexCols.put(i, fields[i]);
                            mappedCols.put(fields[i], new ArrayList<String>());
                        } 
                        //add in to the respective key
                        else {
                            String colName = indexCols.get(i);
                            if (colName == null) {
                                break;
                            }
                            mappedCols.get(colName).add(fields[i]);
                        }
                    }
                    firstLine = false;
                }
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            // finally {
            //     mappedCols.entrySet().forEach(entry -> {
            //         System.out.println("\n\n\n\n");
            //         System.out.println(entry.getKey());
            //         System.out.println(entry.getValue());
            //     });
            // }

        } 
        catch(Exception e){
        // log exception
        }
       
        return mappedCols;

    }

    

}