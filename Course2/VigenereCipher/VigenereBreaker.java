import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        StringBuilder res = new StringBuilder();
        for (int i = whichSlice; i<message.length(); i+=totalSlices) {
            res.append(sb.charAt(i));
        }
        return res.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] keys = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i<klength; i++) {
            String str = sliceString(encrypted, i, klength);
            keys[i] = cc.getKey(str);
        }
        return keys;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource("aida_keyverdi.txt");
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        String[] labels = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String s : labels){
            FileResource fr1 = new FileResource("dictionaries" + "/"+s);
            HashSet<String> dict = readDictionary(fr1);
            dictionaries.put(s, dict);
            System.out.println("Ready with " + s + " language.");
        }
        String decrypted = breakForAllLangs(encrypted, dictionaries);
        System.out.print(decrypted.substring(0, 60));
    }

    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            dict.add(line);
        }
        return dict;
    }

    public int countWords (String message, HashSet<String> dictionary) {
        message = message.toLowerCase();
        String [] messArr = message.split("\\W+");
        int counter = 0;
        for (String word : messArr) {
            if (dictionary.contains(word)) {
                counter++;
            }
        }
        return counter;
    }

    public String breakForLanguage(String message, HashSet<String> dictionary, char mostCommonChar) {
        int maxFreqNorm = 0;
        String decryption = "";
        int needI = 0;
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(message, i, mostCommonChar);
            // for (int key : keys) {
            //     System.out.print(key + " ");
            // }
            VigenereCipher vc = new VigenereCipher(keys);
            String tempDecrypted = vc.decrypt(message);
            int tempFreqNorm = countWords(tempDecrypted, dictionary);
            //System.out.println(tempFreqNorm);
            if (tempFreqNorm > maxFreqNorm) {
                maxFreqNorm = tempFreqNorm;
                decryption = tempDecrypted;
                needI = i;
            }
        }
        // System.out.println(needI);
        // System.out.println(maxFreqNorm);
        return decryption;
    }

    public char mostCommonCharIn (HashSet<String> dictionary) {
        char mostComChar = ' ';
        HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            StringBuilder wordSB = new StringBuilder(word);
            for (int i = 0; i < wordSB.length(); i++) {
                Character ch = wordSB.charAt(i);
                if (freqs.containsKey(ch)) {
                    freqs.put(ch, freqs.get(ch)+1);
                }
                else {
                    freqs.put(ch, 1);
                }
            }
        }
        int mostFreqs = 0;
        for (Character ch : freqs.keySet()) {
            if (freqs.get(ch) > mostFreqs) {
                mostFreqs = freqs.get(ch);
                mostComChar = ch;
            }
        }
        System.out.println("Most common char in this language: " + mostComChar);
        return mostComChar;
    }

    public String breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        String decryption = "";
        String usedLanguage = ""; 
        int maxValidWords = 0;
        for (String lang : languages.keySet()) {
            char mostCommonChar = mostCommonCharIn(languages.get(lang));
            String thisDecryption = breakForLanguage(encrypted, languages.get(lang), mostCommonChar);
            int validWords = countWords(thisDecryption, languages.get(lang));
            if (validWords > maxValidWords) {
                usedLanguage = lang;
                decryption = thisDecryption;
                maxValidWords = validWords;
            }
        }
        System.out.println("Used Language: " + usedLanguage);
        System.out.println("Max valid words: " + maxValidWords);
        return decryption;
    }
}
