public class Part1_2 {
    public static void main (String[] args) {
        Part1_2 p = new Part1_2();
        p.testSimpleGene();
    }

    void testSimpleGene () {
        boolean indCase = false;
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA: " + dna1);
        
        if (dna1 == dna1.toUpperCase()) {
            dna1 = dna1.toLowerCase();
            indCase = true;
        }
        
        int start = dna1.indexOf("atg");
        int stop = dna1.indexOf("taa", start+3);
        if (start == -1 || stop == -1) {
            System.out.println("There is no valid gene");
        }
        else {
            if (indCase == true) {
                dna1 = dna1.toUpperCase();
            }
            String res1 = findSimpleGene (dna1, start, stop);
            System.out.println("The valid gene is " + res1);
        }
    }

    String findSimpleGene (String dna, int startCodon, int stopCodon) {
        String result = "";
        
        if ((stopCodon-startCodon) % 3 == 0){
            result = dna.substring(startCodon, stopCodon+3);
        }
        else {
            result = "not exist here";
        }
        
        return result;
    }
}