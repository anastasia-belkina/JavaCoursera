import java.util.*;
import java.beans.Expression;
import java.io.*;
import java.nio.file.*;
import edu.duke.*;

/**
 * Write a description of class TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public static void main (String[] args) throws Exception {
        TestCaesarCipher p = new TestCaesarCipher();
        p.test();
    }
    
    void test() {
        //FileResource fr = new FileResource ();
        //String input = fr.asString();
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("Input: " + input);
        CaesarCipher cc = new CaesarCipher(15);
        input = cc.encrypt(input);
        System.out.println("Encrypted input: " + input);
        input = cc.decrypt(input);
        //input = breakCaesarCipher(input);
        System.out.println("Decrypted input: " + input);
    }
    
    String breakCaesarCipher(String input) {
        input = input.toLowerCase();
        int [] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        CaesarCipher cc = new CaesarCipher(26 - dkey);
        return cc.encrypt(input);
    }
    
    int[] countLetters (String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
                
            }
        }
        return counts;
    }
    
    int maxIndex (int [] vals) {
        int maxDex = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals [maxDex]) {
                maxDex = i;
            }
        }
        
        return maxDex;
    }
}
