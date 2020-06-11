
public class Part1_3 {
    public static void main (String[] args) {
        Part1_3 p = new Part1_3();
        p.testing();
    }

    void testing () {
        String stringA = "L";
        String stringB = "LOLOL";
        //boolean res = twoOccurrences(stringA, stringB);
        String res = lastPart(stringA, stringB);
        System.out.println("The part of the string after " + stringA + " in " + stringB + " is " + res);

        stringA = "LOH";
        stringB = "LOHLOH";
        //res = twoOccurrences(stringA, stringB);
        res = lastPart(stringA, stringB);
        System.out.println("The part of the string after " + stringA + " in " + stringB + " is " + res);

        stringA = "LOH";
        stringB = "AAAA";
        res = lastPart(stringA, stringB);
        System.out.println("The part of the string after " + stringA + " in " + stringB + " is " + res);
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