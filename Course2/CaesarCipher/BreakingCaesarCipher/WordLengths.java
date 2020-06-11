 
import edu.duke.*;

/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public static void main (String[] args) throws Exception {
        WordLengths p = new WordLengths();
        p.testCountWordLengths();
    }
    
    void testCountWordLengths () {
        FileResource resource = new FileResource ("C:/Users/Pocht/OneDrive/Рабочий стол/Настюшино/Учеба/JavaCoursera/CaesarCipher/BreakingCaesarCipher/data/manywords.txt");
        int resHowManyWords = 0;
        int maxWordLength = 0;
        for (String word : resource.words()) {
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
            resHowManyWords++;
        }
        System.out.println("maxWordLength: " + maxWordLength);
        int [] counts = new int [maxWordLength+1];
        counts = countWordLengths (resource, counts);
        int maxValueID = indexOfMax (counts);
        System.out.println("The index of position of the largest element in counts (the biggest amount of words this (index) size / NOT IT'S AMOUNT): " + maxValueID);
    }
    
    int [] countWordLengths (FileResource resource, int [] counts) {
        for (String word : resource.words()) {
            //System.out.println("Without work on word: " + word);
            word = putAwaySimbols(word);
            //System.out.println("After work on word: " + word);
            int wordLength = word.length();
            counts[wordLength]++;
        }
        int i = 0;
        for (int wordAmount : counts) {
            if (wordAmount != 0) {
                System.out.println(wordAmount + " words of length " + i);
            }
            i++;
        }
        return counts;
    }
    
    int indexOfMax (int [] values) {
        int maxValueID = 0;
        int i = 0;
        for (int value : values) {
            if (values[i] > values [maxValueID]) {
                maxValueID = i;
            }
            i++;
        }
        return maxValueID;
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
