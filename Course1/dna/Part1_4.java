import edu.duke.URLResource;

public class Part1_4 {
    public static void main (String[] args) {
        Part1_4 p = new Part1_4();
        p.testing();
    }

    void testing () {
        URLResource ur = new URLResource ("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String YT = "youtube.com"; 
        String res = "";
        System.out.println("LOOOOOOOOOL");
        for (String s : ur.words()) {
            String stemp = s;
            stemp = stemp.toLowerCase();
            if (stemp.indexOf(YT) != -1) {
                int fdq = stemp.indexOf("\"");
                int ldq = stemp.indexOf("\"", fdq+1);
                res = s.substring(fdq,ldq+1);
                System.out.println(res);
            }
        }
    }

    boolean twoOccurrences (String stringA, String stringB) {
        boolean result = false;
        int temp = stringB.indexOf(stringA);
        temp = stringB.indexOf(stringA, temp + stringA.length());
        if (temp != -1) {
            result = true;
        }
        return result;
    }

    String lastPart (String stringA, String stringB) {
        String result = "";
        int temp = stringB.indexOf(stringA);
        if (temp != -1) {
            int StrBLen = stringB.length();
            int StrALen = stringA.length();
            result = stringB.substring(temp+StrALen, StrBLen);
        }
        else result = stringB;
        return result;
    }
}