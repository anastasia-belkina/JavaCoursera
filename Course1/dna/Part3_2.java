import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Part3_2 {
    public static void main (String[] args) {
        Part3_2 p = new Part3_2();
        p.test();   
    }

    void test() {
        FileResource fr = new FileResource();
        String dna3 = fr.asString();
        //             012345678901234567890123456789012345678901234567
        // String dna3 = "ATGTAA";
        dna3 = dna3.toUpperCase();
        System.out.println(dna3);
        int cCTG = countCTG(dna3);
        System.out.println("CTG appearing here: " + cCTG);
        printAllGene (dna3);
        StorageResource res = getAllGenes(dna3);
        processGenes(res);        
    }

    void processGenes (StorageResource sr) {
        //print all the Strings in sr that are longer than 9 characters
        //print the number of Strings in sr that are longer than 9 characters
        int coS9 = 0;
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        int coCGRat = 0;
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        //print the length of the longest gene in sr
        int lonGene = 0;
        int cCTG = 0;
        for (String s : sr.data()) {
            if (s.length() > 60) {
                System.out.println("Gene longer 9: " + s);
                coS9++;
            }
            double cgrat = cgRatio(s);
            if (cgrat > 0.35) {
                System.out.println("Gene whose C-G-ratio is higher than 0.35: " + s);
                coCGRat++;
            }
            if (s.length() > lonGene) {
                lonGene = s.length();
            }
        }
        System.out.println("Genes longer 60: " + coS9);
        System.out.println("Genes whose C-G-ratio is higher than 0.35: " + coCGRat);
        System.out.println("Longest: " + lonGene);
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
        if (minInd == dna.length() || minInd == -1) {
            result = "";
        }
        else {
            result = dna.substring(startIndex, minInd+3);
        }
        return result;
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
            if (currgene.length() != 0) {
                geneList.add(currgene);
                currIndex = startIndex + currgene.length();
            }
            else {
                currIndex = startIndex + currgene.length() + 1;
            }
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

    void printAllGene (String dna) {
        int startIndex = 0;
        int currIndex = 0;
        int count = 0;
        while (true) {
            startIndex = dna.indexOf("ATG", currIndex);
            //System.out.println(startIndex);
            if (startIndex == -1) {
                break;
            }
            String currgene = findGene(dna, startIndex);
            if (currgene.length() != 0) {
                System.out.println("Gene: " + currgene);
                count++;
                currIndex = startIndex + currgene.length();
            }
            else {
                currIndex = startIndex + currgene.length() + 1;
            }
        }
        System.out.println("Here are this amount of genes: " + count);

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