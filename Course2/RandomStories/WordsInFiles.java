import java.util.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.*;

public class WordsInFiles {
    public static void main (String[] args) {
        WordsInFiles cip = new WordsInFiles();
        cip.test();
    }

    private HashMap <String, ArrayList<String>> wordsInFilesMap;
    
    WordsInFiles() {
        wordsInFilesMap = new  HashMap<String, ArrayList<String>> ();
    }

    void test () {
        buildWordFileMap();
        // for (String s : wordsInFilesMap.keySet()){
        //     System.out.println(s + "\t" + wordsInFilesMap.get(s).toString());
        // }
        System.out.println();
        System.out.println("The max number of files, which consist one word is: " + maxNumber());
        System.out.println();
        System.out.println("Words which appears in exactly " + 4 + " files:");
        int temp = 0;
        for (String s : wordsInNumFiles(4)) {
            temp++;
            // System.out.println(s);
            // System.out.println("Files, in which appears the word " + s + ":");
            // printFilesIn(s);
        }     
        System.out.println("Amount of words appears in 4 files: " + temp);

        String s = "sea";
        System.out.println("Files, in which appears the word " + s + ":");
        printFilesIn(s);
        String s2 = "tree";
        System.out.println("Files, in which appears the word " + s2 + ":");
        printFilesIn(s2);
    }

    void printFilesIn (String word) {
        for (String fileName : wordsInFilesMap.get(word)) {
            System.out.println(fileName);
        }
    }

    void buildWordFileMap () {
        wordsInFilesMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String s : wordsInFilesMap.keySet()) {
            if (wordsInFilesMap.get(s).size() == number) {
                words.add(s);
            }
        }
        return words;
    }

    int maxNumber () {
        int maxNumOfFiles = 0;
        int temp = 0;
        for (String s : wordsInFilesMap.keySet()) {
            if (temp == 0) {
                maxNumOfFiles = wordsInFilesMap.get(s).size();
                temp++;
            }
            if (wordsInFilesMap.get(s).size() > maxNumOfFiles) {
                maxNumOfFiles = wordsInFilesMap.get(s).size();
            }
        }
        return maxNumOfFiles;
    }

    void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            //word = putAwaySimbols(word);
            if (wordsInFilesMap.containsKey(word)) {
                if (!wordsInFilesMap.get(word).contains(f.getName())) {
                    ArrayList<String> tempArList = wordsInFilesMap.get(word);
                    tempArList.add(f.getName());
                    wordsInFilesMap.put(word, tempArList);
                }
            }
            else {
                ArrayList<String> tempArList = new ArrayList<String>();
                tempArList.add(f.getName());
                wordsInFilesMap.put(word, tempArList);
            }
        }
    }

    String putAwaySimbols (String word) {
        StringBuilder clearWord = new StringBuilder(word);
        char lastChar = clearWord.charAt(clearWord.length()-1);
        //char firstChar = clearWord.charAt(0);
        if (Character.isLetter(lastChar) == false) {
             clearWord.deleteCharAt(clearWord.length()-1);
        }
        //if (Character.isLetter(firstChar) == false) {
        //      clearWord.deleteCharAt(0);
        //}
        return clearWord.toString();
    }
}