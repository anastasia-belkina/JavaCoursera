import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;    
    private ArrayList<String> useWordsList;
    private ArrayList<String> useCategoriesList;
    private Random myRandom;
    private int amountOfChangeWords;
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
    }

	public static void main (String[] args) {
        GladLibMap cip = new GladLibMap();
        cip.makeStory();
        cip.totalWordsInMap();
        cip.totalWordsConsidered();
    }

    public void makeStory() {
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\n" + "Amount of changed words: " + amountOfChangeWords);
    }

    private void totalWordsConsidered () {
        int usedCategories = 0;
        for (String label : useCategoriesList) {
            System.out.println(label);
            if (!label.equals("number")){
                usedCategories += myMap.get(label).size();
            }
        }
        System.out.println("All words to be chosen in used categories: " + usedCategories);
    }
    
    private void totalWordsInMap() {
        int allWords = 0;
        for (String label : myMap.keySet()) {
            System.out.println(label);
            allWords += myMap.get(label).size();
        }
        System.out.println("All possible words to be chosen in all categories: " + allWords);
    }
	
	private void initializeFromSource(String source) {
        String[] labels = {"country","noun", "animal", "adjective","name","color", "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }	
        useWordsList = new ArrayList<String>();
        useCategoriesList = new ArrayList<String>();
    }
    
    private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
        if (!useCategoriesList.toString().contains(label)) {
            useCategoriesList.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
		// return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		amountOfChangeWords++;
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		boolean temp = true;
		while (temp) {
			if (useWordsList.toString().contains(sub)) {
				sub = getSubstitute(w.substring(first+1,last));
			}
			else {
				useWordsList.add(sub);
				temp = false;
			}
		}
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	

}
