

public class WordPlay {

    public static void main (String[] args) {
		WordPlay p = new WordPlay();
		p.test();
	}
    
    void test () {
        // char ch = 'E';
        // boolean bu = isVowel(ch);
        // System.out.println("The result is: " + bu);

        // String phrase = "Hello World";
        // char ch1 = '~';
        // String result = replaceVowels(phrase, ch1);
        // System.out.println("The result is: " + result);

        String phrase = "dna ctgaaactga";
        char ch1 = 'a';
        String result = emphasize(phrase, ch1);
        System.out.println("The result is: " + result);
    }

    String emphasize (String phrase, char ch1) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            if (ch == ch1) {
                if (i%2 == 0) {
                    result.setCharAt(i, '*');
                }
                else result.setCharAt(i, '+');
            }
        }
        
        return result.toString();
    }

    String replaceVowels (String phrase, char ch1) {
        StringBuilder result = new StringBuilder(phrase);
        for(int k=0; k < result.length(); k++){
            char ch = result.charAt(k);
            if (isVowel(ch)) {
                result.setCharAt(k, ch1);
            }
        }
        return result.toString();
    }

    boolean isVowel (char ch) {
        if(ch=='a' || ch=='A' || ch=='e' || ch=='E' || ch=='i' || ch=='I' || ch=='o' || ch=='O' || ch=='u' || ch=='U') {    
            return true;
        }    
        else {
            return false;
        }
    }
}