
package com.OOP.remittancesystem.fileHandling;
import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.Remittance;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.List;

public class OpenCSVReadAndParseToBean {
//    private static final String SAMPLE_CSV_FILE_PATH = "./everywhereDummy.csv";

    public static List<Remittance> mapCSV(String fileDownloadUrl) {
        List<Remittance> remittanceList = null;


        try (InputStream input = new URL(fileDownloadUrl).openStream();
            Reader reader = new InputStreamReader(input, "UTF-8"); ){
            CsvToBean<Remittance> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(EverywhereRemit.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
                    

            remittanceList = csvToBean.parse();
            return remittanceList;
        }
      
        catch(IOException e){
            System.out.println("File missing");
        }

        catch(Exception e){
            System.out.println("Header missing");
        }
        
        finally{
            
        }
        return remittanceList;
    }

    public static void mapKeywords(String fileName, String fileType, String fileDownloadUrl) {
        boolean readHeader = false;


        String fullFileName = fileName;

        File file = new File(fullFileName);

        try (InputStream input = new URL(fileDownloadUrl).openStream();) {

            file.deleteOnExit();
            FileOutputStream out = new FileOutputStream(file);
            IOUtils.copy(input, out);

        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }

        File tempFile = new File("./temp.csv");
        String newHeaderName;
        String newHeaders = "";

        try (Scanner fIn = new Scanner(file)){

            PrintStream writer = new PrintStream(new FileOutputStream("./temp.csv", false));

            while (fIn.hasNext()) {
               String csvLine = fIn.nextLine();
                if (!readHeader){

                    Scanner scHeaders = new Scanner(csvLine);
                    scHeaders.useDelimiter(",|\r\n|\n");
                    while (scHeaders.hasNext()){
                        newHeaderName = renameHeader(scHeaders.next());
                        newHeaders += newHeaderName + ",";
                    }

                    writer.println(newHeaders.substring(0,newHeaders.length()-1));
                    readHeader = true;
                    scHeaders.close();

               }
               else{
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
            file.exists();
            file.delete();
            tempFile.renameTo(file); 
         }
        
    }

    public static String renameHeader(String header){

       
        return header;
    }

}