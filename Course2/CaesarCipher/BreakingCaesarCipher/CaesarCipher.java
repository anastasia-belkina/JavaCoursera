package BreakingCaesarCipher;

import edu.duke.*;
import java.util.*;
import java.beans.Expression;
import java.io.*;
import java.nio.file.*;

public class CaesarCipher {

    public static void main (String[] args) throws Exception {
        CaesarCipher p = new CaesarCipher();
        //p.testCaesarFromFiles();
        p.testCaesar();
    }

    static void testCaesar() throws Exception {
        //int key = 15;
        //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String result = encrypt(message, key);
        //System.out.println(result);

        int key1 = 24;
        int key2 = 6;
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        String result = encryptTwoKeys(message, key1, key2);
        System.out.println(result);
        

    }

    static void testCaesarFromFiles() throws Exception {
        String fname = "message2.txt";
        int key = 23;
        File file = new File(fname);
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader reader = new BufferedReader(fr);
        String line = "";
        ArrayList <String> StrArr= new ArrayList <String>();
        while (line != null) {
            line = reader.readLine();
            //System.out.println(line);
            StrArr.add(line);
        }
        //String message = StrArr.get(0);
        for (String message : StrArr) {
            System.out.println(message);
            String encrypted = encrypt(message, key);
            System.out.println(encrypted);
            String decrypted = encrypt(encrypted, 26-key);
            System.out.println(decrypted);
        }
    }

    static String encryptTwoKeys (String input, int key1, int key2) {
        char charrr  = 'a';
        input = input.toLowerCase();
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghigklmnopqrstuvwxyz";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (i%2 == 0) {
                if(idx != -1){
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else {
                if(idx != -1) {
                char newChar = shiftedAlphabet2.charAt(idx);
                encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    static String encrypt(String input, int key) {
        input = input.toLowerCase();
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "abcdefghigklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

}

