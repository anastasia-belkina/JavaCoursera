import edu.duke.*;
import java.util.*;

public class CharactersInPlay {

    private ArrayList<String> Characters;
    private ArrayList<Integer> CharFreqs;

    public CharactersInPlay() {
        Characters = new ArrayList<String>();
        CharFreqs = new ArrayList<Integer>();
    }

    public static void main (String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }

    public void tester () {
        findAllCharacters();
        int i = 0;
        for (String word : Characters) {
            if (CharFreqs.get(i) <= 1) {}
            else {
                System.out.println(Characters.get(i) + "\t" + CharFreqs.get(i));
            }
            i++;
        }
        int index = findIndexOfMax();
        System.out.println("The Character that occurs most often and its count are: " + Characters.get(index) + " " + CharFreqs.get(index));
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }

    public void findAllCharacters () {
        Characters.clear();
        CharFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.lines()) {
            // System.out.println(s);
            String [] SplitLines = s.split("\\.");
            // System.out.println(SplitLines.length);

            // for (String word : SplitLines) {
            //     System.out.println(word);
            // }

            s = SplitLines[0];
            int index = Characters.indexOf(s);
            if (index == -1){
                Characters.add(s);
                CharFreqs.add(1);
            }
            else {
                int freq = CharFreqs.get(index);
                CharFreqs.set(index,freq+1);
            }
        }
    }

    public int findIndexOfMax(){
        int max = CharFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < CharFreqs.size(); k++){
            if (CharFreqs.get(k) > max){
                max = CharFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void charactersWithNumParts (int num1, int num2) {
        System.out.println("The characters with number of speaking parts which is more than " + num1 + " and less then " + num2 + ":");
        int i = 0;
        for (String word : Characters) {
            if (CharFreqs.get(i) >= num1 && CharFreqs.get(i) <= num2) {
                System.out.println(Characters.get(i) + "\t" + CharFreqs.get(i));
            }
            i++;
        }
    }
}
