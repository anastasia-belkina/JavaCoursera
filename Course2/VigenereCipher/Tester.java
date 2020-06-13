import edu.duke.FileResource;

public class Tester {

    public static void main (String[] args) {
        Tester t = new Tester();
        t.testVigenere();
    }

    void testVigenere () {
        VigenereBreaker vc = new VigenereBreaker();
        vc.breakVigenere();
        
        //FileResource fr = new FileResource("dictionaries/Portuguese");
        //System.out.println(vc.sliceString("abcdefghijklm", 4, 5));

        // int[] keys = vc.tryKeyLength(fr.asString(), 4, 'e');
        // for (int key : keys) {
        //     System.out.print(key + " ");
        // }

        
        //vc.mostCommonCharIn(vc.readDictionary(fr));

    }
}