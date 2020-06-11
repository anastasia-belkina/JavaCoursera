import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part2 {
    public static void main (String[] args) {
        Part2 p = new Part2();
        //p.testColdestHourInFile();
        //p.testFileWithColdestTemperature();
        //p.testLowesrHumidityInFile();
        p.testLowestHumidityInManyFiles();
        //p.testAverageTemperatureInFile();
        //p.testAverageTemperatureWithHighHumidityInFile();
    }

    void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double averTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        if (averTemp == 0.0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature with high humidity in file is: " + averTemp);
        }
    }

    double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double averange = 0.0;
        double sumTemp = 0.0;
        int counter = 0;
        for (CSVRecord currrow : parser) {
            double currtemp = Double.parseDouble(currrow.get("TemperatureF"));
            double currhumi = Double.parseDouble(currrow.get("Humidity"));
            if (currhumi >= value) {
                if (currtemp != -9999) {
                    sumTemp = sumTemp + currtemp;
                    counter++;
                }
                else {
                    continue;
                }
            }
        }
        if (counter != 0) {
            averange = sumTemp/counter;
        } 
        return averange;
    }

    void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averTemp);
    }

    double averageTemperatureInFile(CSVParser parser) {
        double averTemp = 0.0;
        double sumTemp = 0.0;
        int counter = 0;
        for (CSVRecord currrow : parser) {
            double currtemp = Double.parseDouble(currrow.get("TemperatureF"));
            if (currtemp != -9999) {
                sumTemp = sumTemp + currtemp;
                counter++;
            }
            else {
                continue;
            }
        }
        averTemp = sumTemp/counter;
        return averTemp;
    }

    void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumRow == null) {
                lowestHumRow = currRow;
            }
            else {
                double currtemp = Double.parseDouble(currRow.get("Humidity"));
                double lowestTemp = Double.parseDouble(lowestHumRow.get("Humidity"));
                if (currtemp < lowestTemp) {
                    lowestHumRow = currRow;
                }
            }
        }
        return lowestHumRow;
    }

    void testLowesrHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestHumRow = null;
        for (CSVRecord currRow : parser) {
            if (currRow.get("Humidity").equals("N/A")) {
                continue;
            }
            else {
                if (lowestHumRow == null) {
                    lowestHumRow = currRow;
                }
                else {
                    double currtemp = Double.parseDouble(currRow.get("Humidity"));
                    double coldesttemp = Double.parseDouble(lowestHumRow.get("Humidity"));
                    if (currtemp < coldesttemp) {
                        lowestHumRow = currRow;
                    }
                }
            }
        }
        return lowestHumRow;
    }

    void testFileWithColdestTemperature () {
        File fileColdestDay = fileWithColdestTemperature();
        System.out.println("The name of file with coldest day: " + fileColdestDay.getName());
        FileResource fr = new FileResource(fileColdestDay);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest TemperatureF: " + coldestTemp.get("TemperatureF") + " TimeEST: " + coldestTemp.get("TimeEST"));
    }

    File fileWithColdestTemperature () {
        CSVRecord coldestTempRow = null;
        DirectoryResource dr = new DirectoryResource();
        File fileColdestDay = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            if (coldestTempRow == null) {
                coldestTempRow = currRow;
            }
            else {
                double currtemp = Double.parseDouble(currRow.get("TemperatureF"));
                double coldesttemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
                if (currtemp != -9999 && currtemp < coldesttemp) {
                    coldestTempRow = currRow;
                    fileColdestDay = f;
                }
            }
        }
        return fileColdestDay;
    }

    void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("TemperatureF: " + coldestTemp.get("TemperatureF") + " TimeEST: " + coldestTemp.get("DateUTC"));
    }

    CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestTempRow = null;
        for (CSVRecord currRow : parser) {
            if (coldestTempRow == null) {
                coldestTempRow = currRow;
            }
            else {
                double currtemp = Double.parseDouble(currRow.get("TemperatureF"));
                double coldesttemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
                if (currtemp != -9999 && currtemp < coldesttemp) {
                    coldestTempRow = currRow;
                }
            }
        }
        return coldestTempRow;
    }

    
}