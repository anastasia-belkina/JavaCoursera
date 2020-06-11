package BreakingCaesarCipher;
import edu.duke.*;

/**
 * Write a description of class CountingLettersOccurances here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    
    public static void main (String[] args) {
        CaesarBreaker p = new CaesarBreaker();
        p.testDecrypt("C:/Users/Pocht/OneDrive/Рабочий стол/Настюшино/Учеба/JavaCoursera/CaesarCipher/BreakingCaesarCipher/data/mysteryTwoKeysPractice.txt");
    }

    void testDecrypt (String fname) {
        FileResource fr = new FileResource (fname);
        String encrypted = fr.asString();
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println ("Decrypted string: " + decryptTwoKeys (encrypted));
        
    }
    
    String decryptTwoKeys (String encrypted) {
        encrypted = encrypted.toLowerCase();
        String str1 = halfOfString (encrypted, 0);
        System.out.println ("Half of the string from 0: " + str1);
        String str2 = halfOfString (encrypted, 1);
        System.out.println ("Half of the string from 1: " + str2);
        int key1 = 26 - getKey(str1);
        System.out.println ("Key 1: " + key1);
        int key2 = 26 - getKey(str2);
        System.out.println ("Key 2: " + key2);
        CaesarCipher cc = new CaesarCipher ();
        return cc.encryptTwoKeys(encrypted, key1, key2);
        
    }
    
    int getKey (String s) {
        int [] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
        
    }
    
    String halfOfString (String message, int start) {
        StringBuilder halfStr = new StringBuilder();
        StringBuilder currHalfStr = new StringBuilder(message);
        for (int i = start; i < message.length(); i+=2) {
            char ch = currHalfStr.charAt(i);
            halfStr.append(ch);
        }
        return halfStr.toString();
        
    }
    
    String decrypt (String encrypted) {
        encrypted = encrypted.toLowerCase();
        CaesarCipher cc = new CaesarCipher ();
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
        
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
