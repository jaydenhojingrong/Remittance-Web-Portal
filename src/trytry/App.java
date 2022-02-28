import java.util.List;
public class App {

    public static void main(String[] args) {
        
        OpenCSVReadAndParseToBean.mapKeywords();

        List<Remittance> remittanceList = OpenCSVReadAndParseToBean.mapCSV();
        
        for (Remittance remittance: remittanceList) {
            //Remittance remittance = csvUserIterator.next();
            System.out.println("Country : " + remittance.getsCountry());
            System.out.println("First Name : " + remittance.getsFirstName());
            System.out.println("Last Name : " + remittance.getsLastName());
            System.out.println("==========================");
        }

    }

}
