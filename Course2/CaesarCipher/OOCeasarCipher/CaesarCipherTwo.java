import edu.duke.*;
import java.util.*;
import java.beans.Expression;
import java.io.*;
import java.nio.file.*;

/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    String encrypt (String input) {
        input = input.toLowerCase();
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i+=2) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet1.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        
        for(int i = 1; i < encrypted.length(); i+=2) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet2.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    String decrypt (String encrypted) {
        encrypted = encrypted.toLowerCase();
        String str1 = halfOfString (encrypted, 0);
        System.out.println ("Half of the string from 0: " + str1);
        String str2 = halfOfString (encrypted, 1);
        System.out.println ("Half of the string from 1: " + str2);
        int key1 = 26 - getKey(str1);
        System.out.println ("Key 1: " + key1);
        int key2 = 26 - getKey(str2);
        System.out.println ("Key 2: " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.encrypt(encrypted);
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
    
    int[] countLetters (String message) {
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
