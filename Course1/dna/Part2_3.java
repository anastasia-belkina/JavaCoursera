public class Part2_3 {
    public static void main (String[] args) {
        Part2_3 p = new Part2_3();
        //p.testFindStopCodon();
        p.test();
    }

    public void findAbc(String input){
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            System.out.println("index " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc",index+3);
            System.out.println("index after updating " + index);
        }
    }
 
    public void test(){
        //findAbc("abcd");
        //       01234567890123456789012345678901234567890
        findAbc("abcabcabcabca");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void testFindStopCodon() {
        //             01234567890123456789
        String dna1 = "ATGTAAxxxATGxxyTGA";
        System.out.println(dna1);
        System.out.println("All 1 Genes: ");
        System.out.println(countGenes(dna1));

        String dna2 = "";
        System.out.println(dna2);
        System.out.println("All 2 Genes: ");
        System.out.println(countGenes(dna2));

        //             01234567890123456789012345678901234
        String dna3 = "xxxxATGyyyyyyTAGyyyTAGATGxxxTAAyyyyyATGwwwTGAaaa";
        System.out.println(dna3);
        System.out.println("All 3 Genes: ");
        System.out.println(countGenes(dna3));

        //             0123456789012
        String dna4 = "ATGxxxxxxxTGA";
        System.out.println(dna4);
        System.out.println("All 4 Genes: ");
        System.out.println(countGenes(dna4));

        //             0123456789012
        String dna5 = "ATGxxxxxxx";
        System.out.println(dna5);
        System.out.println("All 5 Genes: ");
        System.out.println(countGenes(dna5));
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