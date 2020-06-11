import edu.duke.*;
import java.util.*;
import java.beans.Expression;
import java.io.*;
import java.nio.file.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public static void main (String[] args) throws Exception {
        //CaesarCipher p = new CaesarCipher();
        //p.testCaesarFromFiles();
        //p.testCaesar();
    }
    
    public CaesarCipher (int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    String encrypt (String input) {
        input = input.toLowerCase();
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    String decrypt (String input) {
        input = input.toLowerCase();
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
}

