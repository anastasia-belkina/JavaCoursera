import edu.duke.*;
import java.util.*;
import java.beans.Expression;
import java.io.*;
import java.nio.file.*;

/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    
    public static void main (String[] args) throws Exception {
        TestCaesarCipherTwo p = new TestCaesarCipherTwo();
        p.test();
    }
    
    void test() {
        FileResource fr = new FileResource ();
        String input = fr.asString();
        //String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("Input: " + input);
        CaesarCipherTwo cct = new CaesarCipherTwo(12, 2);
        //input = cct.encrypt(input);
        //System.out.println("Encrypted input: " + input);
        input = cct.decrypt(input); 
        //input = breakCaesarCipher(input);
        System.out.println("Decrypted input: " + input);
    }
}