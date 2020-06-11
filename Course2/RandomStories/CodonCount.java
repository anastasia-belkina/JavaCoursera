import java.util.*;
import edu.duke.*;

public class CodonCount {
    public static void main (String[] args) {
        CodonCount cip = new CodonCount();
        cip.test();
    }

    private HashMap <String, Integer> myDNAMap;
    private Random myRandom;
    private static String DNAdataFile = "dnaMystery2";
    
    CodonCount() {
        myDNAMap = new HashMap<String, Integer>();
        myRandom = new Random();
    }

    void test () {
        FileResource resource = new FileResource(DNAdataFile);
        String DNA = resource.asString();
        DNA = DNA.trim();
        DNA = DNA.toUpperCase();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i,DNA);
            String mostFreqsCodon = getMostCommonCodon();
            System.out.println("And most common codon is " + mostFreqsCodon + " with count " + myDNAMap.get(mostFreqsCodon));

            int start = 1;
            int end = 8;
            printCodonCounts(start, end);

            myDNAMap.clear();
        }
        // for (String s : myDNAMap.keySet()){
        //     System.out.println(myDNAMap.get(s)+"\t"+s);
        // }
    }

    void buildCodonMap (int start, String dna) {
        int startOrd = start;
        int finish = start + 3;
        int codonAm = 0;
        while (finish <= dna.length()) {
            String codon = dna.substring(start, finish);
            //System.out.println(codon);
            if (!myDNAMap.containsKey(codon)){
                myDNAMap.put(codon,1);
                codonAm++;
            }
            else {
                myDNAMap.put(codon,myDNAMap.get(codon)+1);
            }
            start+=3;
            finish+=3;
        }
        System.out.println("Reading frame starting with " + startOrd + " results in " + codonAm + " unique codons");
    }
    
    String getMostCommonCodon () {
        String maxCodon = "";
        int maxCodonFreq = 0;
        int i = 0;
        for (String s : myDNAMap.keySet()){
            if (i == 0) {
                maxCodon = s;
                maxCodonFreq = myDNAMap.get(s);
            }
            if (myDNAMap.get(s) > maxCodonFreq) {
                maxCodon = s;
                maxCodonFreq = myDNAMap.get(s);
            }
            i++;
        }
        return maxCodon;
    }

    void printCodonCounts (int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String s : myDNAMap.keySet()){
            if (myDNAMap.get(s) >= start && myDNAMap.get(s) <=end) {
                System.out.println(myDNAMap.get(s)+"\t"+s);
            }
        }
    }

}