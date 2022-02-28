import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.*;
import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReadAndParseToBean {
    private static final String SAMPLE_CSV_FILE_PATH = "./everywhereDummy.csv";

    public static List<Remittance> mapCSV() {
        List<Remittance> remittanceList = null;

        try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));) {
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

    public static void mapKeywords(){
        boolean readHeader = false;
        File file = new File(SAMPLE_CSV_FILE_PATH);
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
        
        if (header.equals("Country")){
            header = "sCountry";
        }
        return header;
    }

}