import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public static void main (String[] args) {
        Part1 p = new Part1();
        p.tester();
    }

    void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //1
        // String country = "Nauru";
        // String res = countryInfo(parser, country);
        // System.out.println(res);
        //2
        // String exp1 = "cotton";
        // String exp2 = "flowers";
        // listExportersTwoProducts(parser, exp1, exp2);
        //3
        // String exportItem = "cocoa";
        // int result = numberOfExporters(parser, exportItem);
        // System.out.println(result);
        //4
        String amount = "$999,999,999,999";
        bigExporters(parser, amount);
    }

    String countryInfo (CSVParser parser, String country) {
        String res = "NOT FOUND";
        for (CSVRecord record : parser) {
            //String CountryTemp = record.get("Country");
            //if (CountryTemp.contains(country)) {
            if (record.get("Country").equals(country)) {
                res = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return res;
            }
        }
        return res;
    }

    void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String ExportsTemp = record.get("Exports");
            if (ExportsTemp.contains(exportItem1) && ExportsTemp.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    int  numberOfExporters (CSVParser parser, String exportItem) {
        int res = 0;
        for (CSVRecord record : parser) {
            String ExportsTemp = record.get("Exports");
            if (ExportsTemp.contains(exportItem)) {
                res++;
            }
        }
        return res;
    }

    void bigExporters (CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String ValueTemp = record.get("Value (dollars)");
            if (ValueTemp.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

}