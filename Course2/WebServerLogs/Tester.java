
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {

    public static void main (String[] args) {
        Tester t = new Tester();
        t.testLogAnalyzer();
    }

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //la.printAll();

        //System.out.println("Uniq IP in this log: " + la.countUniqueIPs());

        // System.out.println("LodEntries with status code higther than number in this log: ");
        // la.printAllHigherThanNum(400);

        // ArrayList <String> uniqueIPVisitsOnDay = la.uniqueIPVisitsOnDay("Sep 24");
        // System.out.println("Uniq IP on Date in this log: " + uniqueIPVisitsOnDay.size());

        //System.out.println("Uniq IP with status code in range in this log: " + la.countUniqueIPsInRange(200,299));

        // HashMap<String, Integer> counts = la.countVisitsPerIP();
        // System.out.println(counts);

        //System.out.println("Most Number Visits By IP: " + la.mostNumberVisitsByIP(counts));

        //System.out.println("IPs with most visits: " + la.iPsMostVisits(counts));
        
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        // System.out.println(iPsForDays);

        // System.out.println("Day with the most IP visits: " + la.dayWithMostIPVisits(iPsForDays));

        System.out.println("IPs with most visits on this date: " + la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 29"));
    }
}
