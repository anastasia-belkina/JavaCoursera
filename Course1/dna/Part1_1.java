/*
 This Part1 programs are working with finding one gene, then founding with different registres,
 and then with looking for word in words, then with looking for "youtube.com" in url file
 */
public class Part1_1 {
    public static void main (String[] args) {
        Part1_1 p = new Part1_1();
        p.testSimpleGene();
    }

    void testSimpleGene () {
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        String res1 = findSimpleGene (dna1);
        System.out.println("DNA: " + dna1);
        System.out.println("The valid gene is " + res1);
    }

    String findSimpleGene (String dna) {
        String result = "";
        int start = dna.indexOf("atg");
        int stop = dna.indexOf("taa", start+3);
        if (start == -1 || stop == -1) {
            return "not exist here";
        }
        if ((stop-start) % 3 == 0){
            result = dna.substring(start, stop+3);
        }
        else {
            result = "not exist here";
        }
        
        return result;
    }
}