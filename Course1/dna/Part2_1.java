/*
This prog will look for many genes in string. Also we adding more two codons: TAA, TAG, TGA
*/
public class Part2_1 {
    public static void main (String[] args) {
        Part2_1 p = new Part2_1();
        p.testFindStopCodon();
    }

    void testFindStopCodon() {
        //             01234567890123456789
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println(dna1);
        System.out.println("All 1 Genes: ");
        printAllGene (dna1);
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
        if (minInd == dna.length()) result = "minind is dna.length";
        else result = dna.substring(startIndex, minInd+3);
        return result;
    }

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
}