/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
	public static void main (String[] args) {
		BabyBirths p = new BabyBirths();
		//p.printNames();
		//p.testTotalBirths();
		//p.testGetRank();
		//p.testGetName();
		//p.testWhatIsNameInYear();
		//p.testYearOfHighestRank();
		//p.testGetAverageRank();
		p.testGetTotalBirthsRankedHigher();
	}

	void testGetTotalBirthsRankedHigher() {
		int year = 1990;
		String name = "Drew";
		String gender = "M";
		int totalBirthsBefore = getTotalBirthsRankedHigher(year, name, gender);
		System.out.println("Total births with highter rank: " + totalBirthsBefore);
	}

	int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int totBirths = -1;
		int sumBirths = 0;
		String filename = "data/yob" + year + ".csv";
		FileResource fr = new FileResource(filename);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					totBirths = sumBirths;
					return totBirths;
				}
				else {
					sumBirths += Integer.parseInt(rec.get(2));
				}
			}
		}
		
		return totBirths;
	}

	void testGetAverageRank() {
		String name = "Robert";
		String gender = "M";
		double averRank = getAverageRank(name, gender);
		System.out.println("Averange rank of this years: " + averRank);
	}

	double getAverageRank(String name, String gender) {
		double aveRank = -1.0;
		int startYear = 1880;
		int stopYear = 2014;
		double sumRank = 0.0;
		double counter = 0.0;
		for (int year = startYear; year <=stopYear; year++) {
			int currRank = getRank(year, name, gender);
			sumRank+=currRank;
			counter++;
		}
		aveRank = sumRank/counter;
		return aveRank;
	}

	void testYearOfHighestRank() {
		String name = "Mich";
		String gender = "M";
		int highRankYear = yearOfHighestRank(name, gender);
		System.out.println("The year with the highest rank: " + highRankYear);
	}
	
	int yearOfHighestRank (String name, String gender) {
		int startYear = 1880;
		int stopYear = 2014;
		int bestRank = getRank(startYear, name, gender);
		int resYear = startYear;
		for (int year = startYear; year <=stopYear; year++) {
			int currRank = getRank(year, name, gender);
			if (bestRank == -1) {
				bestRank = currRank;
			}
			else {
				if (currRank != -1 && currRank < bestRank) {
					bestRank = currRank;
					resYear = year;
				}
			}
		}
		if (bestRank == -1) resYear = -1;
		return resYear;
	}

	void testWhatIsNameInYear() {
		String name = "Owen";
		int year = 1974;
		int newYear = 2014;
		String gender = "M";
		whatIsNameInYear(name, year, newYear, gender);
	}

	void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int rank = getRank(year, name, gender);
		String newName = getName(newYear, rank, gender);
		System.out.println(name + " born in " + year + " would be " + newName + " if would born in " + newYear);
	}

	void testGetName() {
		int year = 1982;
		int rank = 450;
		String gender = "M";
		String name = getName(year, rank, gender);
		System.out.println("Name of " + rank + " person in year " + year + " with gender " + gender + " is: " + name);
	}

	String getName (int year, int rank, String gender) {
		String result = "NO NAME";
		int counter = 1;
		String filename = "data/yob" + year + ".csv";
		FileResource fr = new FileResource(filename);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (counter == rank) {
					result = rec.get(0);
					return result;
				}
				else counter++;
			}
		}
		return result;
	}

	void testGetRank() {
		int year = 1971;
		String name = "Frank";
		String gender = "M";
		int rank = getRank(year, name, gender);
		System.out.println("Rank of " + name + " in year " + year + " with gender " + gender + " is: " + rank);
	}

	int getRank (int year, String name, String gender) {
		int counter = 1;
		String filename = "data/yob" + year + ".csv";
		FileResource fr = new FileResource(filename);
		int rank = -1;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					rank = counter;
					return rank;
				}
				else counter++;
			}
		}
		return rank;
	}

	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob1905.csv");
		totalBirths(fr);
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoysBirths = 0;
		int totalGirlsBirths = 0;
		int totalNames = 0;
		int totalGirlsNames = 0;
		int totalBoysNames = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			totalNames++;
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoysBirths += numBorn;
				totalBoysNames++;
			}
			else {
				totalGirlsBirths += numBorn;
				totalGirlsNames++;
			}
		}
		System.out.println("Total births = " + totalBirths);
		System.out.println("Total girls births = " + totalGirlsBirths);
		System.out.println("Total boys births= " + totalBoysBirths);
		System.out.println("Total names = " + totalNames);
		System.out.println("Total girls names = " + totalGirlsNames);
		System.out.println("Total boys names = " + totalBoysNames);
	}
}
