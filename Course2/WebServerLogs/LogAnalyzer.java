
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new  ArrayList<LogEntry>();   
    }
        
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            //System.out.println(line);
            WebLogParser wp = new WebLogParser();
            LogEntry lg = wp.parseEntry(line);
            //System.out.println(lg);
            records.add(lg);
        }
        System.out.println("Log size: " + records.size());
    }
        
    public void printAll() {
         for (LogEntry le : records) {
            System.out.println(le);
         }
    }

    public int countUniqueIPs () {
        ArrayList <String> uniqIP = new ArrayList <String>();
        for (LogEntry le : records) {
            String IPadr = le.getIpAddress();
            if (!uniqIP.contains(IPadr)) {
                uniqIP.add(IPadr);
            }
        }
        return uniqIP.size();
    }

    public void printAllHigherThanNum (int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
        ArrayList <String> uniqIP = new ArrayList <String>();
        for (LogEntry le : records) {
            String IPadr = le.getIpAddress();
            if (!uniqIP.contains(IPadr) && le.getAccessTime().toString().contains(someday)) {
                uniqIP.add(IPadr);
            }
        }
        return uniqIP;
    }

    public int countUniqueIPsInRange (int low, int high) {
        int uniIPsInRange = 0;
        ArrayList <String> uniqIP = new ArrayList <String>();
        for (LogEntry le : records) {
            String IPadr = le.getIpAddress();
            if (!uniqIP.contains(IPadr) && (le.getStatusCode() >= low && le.getStatusCode() <= high) ) {
                uniqIP.add(IPadr);
                uniIPsInRange++;
            }
        }
        return uniIPsInRange;

    }

    public HashMap<String, Integer> countVisitsPerIP () {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip)+1);
            }
            else {
                counts.put(ip, 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (int num : counts.values()) {
            if (num > max) max = num;
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
        ArrayList<String> maxIP = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == max) {
                maxIP.add(ip);
            }
        }
        return maxIP;
    }

    public HashMap<String, ArrayList<String>> iPsForDays () {
        HashMap<String, ArrayList<String>> IPforDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            // System.out.println(date);
            date = date.substring(4, 10);
            // System.out.println(date);
            String ip = le.getIpAddress();
            if (IPforDays.containsKey(date)) {
                ArrayList<String> temp = IPforDays.get(date);
                temp.add(ip);
                IPforDays.put(date, temp);
            }
            else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(ip);
                IPforDays.put(date, temp);
            }
        }
        return IPforDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
        String popDay = "";
        int maxNumIp = 0;
        for (String day : iPsForDays.keySet()) {
            if (iPsForDays.get(day).size() > maxNumIp) {
                popDay = day;
            }
        }
        return popDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> iPsForDays, String day) {
        ArrayList<String> IParr = new ArrayList<String>();
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip : iPsForDays.get(day)) {
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip)+1);
            }
            else {
                counts.put(ip, 1);
            }
        }
        IParr = iPsMostVisits(counts);
        return IParr;
    }
     
}
