import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Part3_1 {

    public static void main (String[] args) {
        Part3_1 p = new Part3_1();
        //p.testFindStopCodon();
        p.test();   
    }

    void test() {
        //             012345678901234567890123456789012345678901234567
        // String dna3 = "";
        FileResource fr = new FileResource();
        String dna3 = fr.asString();
        //System.out.println(dna3);
        StorageResource res = getAllGenes(dna3);
        processGenes(res); 
        for (String s : res.data()) {
            //double result = cgRatio (s);
            System.out.println("Gene: " + s);
            // System.out.println("CG Ratio in this gene: " + result);
            int countCTG = countCTG(s);
            System.out.println("CTG Ratio in this gene: " + countCTG);
        }

        
    }

    int findStopCodon (String dna, int startIndex, String stopCodon) {
        //System.out.println("StartIndex: " + startIndex);
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        //System.out.println("StopIndex: " + stopIndex);
        int currIndex = startIndex;
        while (stopIndex != -1) {
            stopIndex = dna.indexOf(stopCodon, currIndex);
            if ((stopIndex-startIndex) % 3 == 0){
                return stopIndex;
            }
            currIndex = stopIndex + 1;
        }
        return dna.length();
    }

    String findGene (String dna, int startIndex) {
        String result = "";
        int TAAIndex = findStopCodon(dna, startIndex, "TAA");
        int TGAIndex = findStopCodon(dna, startIndex, "TGA");
        int TAGIndex = findStopCodon(dna, startIndex, "TAG");
        int minInd = Math.min((Math.min(TAAIndex, TGAIndex)), TAGIndex);
        if (minInd == dna.length()) result = "";
        else result = dna.substring(startIndex, minInd+3);
        return result;
    }

    void processGenes (StorageResource sr) {



    }

    StorageResource getAllGenes (String dna) {
        int startIndex = 0;
        int currIndex = 0;
        StorageResource geneList = new StorageResource ();
        while (true) {
            startIndex = dna.indexOf("ATG", currIndex);
            if (startIndex == -1) {
                break;
            }
            String currgene = findGene(dna, startIndex);
            geneList.add(currgene);
            currIndex = startIndex + currgene.length();
            
        }
        return geneList;
    }

    double cgRatio (String dna) {
        double res = 0.0;

        //looking for c
        double cAm = 0.0;
        int currind = 0;
        int startInd = 0;
        while (true) {
            startInd = dna.indexOf("C", currind);
            if (startInd == -1) {
                break;
            }
            cAm++;
            currind = startInd + 1;
            
        }

        //looking for g
        double gAm = 0.0;
        currind = 0;
        startInd = 0;
        while (true) {
            startInd = dna.indexOf("G", currind);
            if (startInd == -1) {
                break;
            }
            gAm++;
            currind = startInd + 1;
            
        }

        res = (cAm + gAm)/dna.length();
        return res;
    }

    int countCTG (String dna) {
        int res = 0;
        int currind = 0;
        int startInd = 0;
        while (true) {
            startInd = dna.indexOf("CTG", currind);
            if (startInd == -1) {
                break;
            }
            res++;
            currind = startInd + 1;
            
        }
        return res;
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    void printAllGene (String dna) {
        int startIndex = 0;
        int currIndex = 0;
        while (true) {
            startIndex = dna.indexOf("ATG", currIndex);
            //System.out.println(startIndex);
            if (startIndex == -1) {
                break;
            }
            String currgene = findGene(dna, startIndex);
            System.out.println("Gene: " + currgene);
            currIndex = startIndex + currgene.length();
            
        }

    }

    int countGenes (String dna) {
        int res = 0;
        int startIndex = 0;
        int currIndex = 0;
        while (true) {
            startIndex = dna.indexOf("ATG", currIndex);
            //System.out.println(startIndex);
            if (startIndex == -1) {
                break;
            }
            String currgene = findGene(dna, startIndex);
            //System.out.println(currgene);
            if (currgene.length() != 0) {
                res++;
                currIndex = startIndex + currgene.length();
            }
            else break;
            
        }
        return res;

    }
}