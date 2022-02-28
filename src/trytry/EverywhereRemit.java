import com.opencsv.bean.CsvBindByName;

public class EverywhereRemit extends Remittance{
    
    @CsvBindByName(column = "source_type", required = true)
    private String sourceType;
    @CsvBindByName(column = "segment", required = true)
    private String segment;
    @CsvBindByName(column = "Date of Birth", required = true)
    private String sDOB;
    @CsvBindByName(column = "ID Country of Issue", required = true)
    private String sIDCountry;
    @CsvBindByName(column = "sender_currency", required = true)
    private String sCurrency;
    @CsvBindByName(column = "Address City", required = true)
    private String sCity;
    @CsvBindByName(column = "Country of Address", required = true)
    private String sCountryAddress;
    @CsvBindByName(column = "recipient_type", required = true)
    private String rType;
    @CsvBindByName(column = "recipient_country", required = true)
    private String rCountry;
    @CsvBindByName(column = "Mobile Number", required = true)
    private String rMobile;
    @CsvBindByName(column = "Amount", required = true)
    private String amount;

    public EverywhereRemit(){
        

    }
}
