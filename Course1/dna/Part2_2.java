/*
Here we just looking how many times string a occurence in string b
*/
public class Part2_2 {
    public static void main (String[] args) {
        Part2_2 p = new Part2_2();
        p.testHowMany();
    }

    void testHowMany() {
        String stringa = "aa";
        String stringb = "a";
        int res = howMany(stringa, stringb);
        System.out.println(res);
    }

    int howMany (String stringa, String stringb) {
        int result = 0;
        int currind = 0;
        int startInd = 0;
        while (true) {
            startInd = stringb.indexOf(stringa, currind);
            if (startInd == -1) {
                break;
            }
            result++;
            currind = startInd + stringa.length();
            
        }
        
        return result;       
    }
}