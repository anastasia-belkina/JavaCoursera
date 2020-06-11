
/*
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public static void main (String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.test();
    }

    public void test () {
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        // int i = 0;
        // for (String word : myWords) {
        //     System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        //     i++;
        // }
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));
    }
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique () {
        myFreqs.clear();
        myWords.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
