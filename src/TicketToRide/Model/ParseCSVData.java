/**
 * ParseCSVData.java
 * This class provides static methods for parsing a given CSV file line by line 
 * into an array of strings which is then passed to the matching class to create
 * unique objects for each line in the CSV file for that respective class.
 */
package TicketToRide.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sean Fast
 *
 */
public class ParseCSVData {
	private static String destCSVFile = "DestinationCardArray.csv";
	private static String cityCSVFile = "NodeArray.csv";
	private static String routeCSVFile = "EdgeArray.csv";
	private static String csvSplitBy = ",";
	

	/**
	 * Reads a csv file of destination card data line by line For each line it
	 * creates a new destination card object and adds it to the list, then
	 * returns the list
	 * 
	 * @return destinationCardList a list of destination cards
	 */
	public static ArrayList<DestinationCard> parseDestinationCards() {
		ArrayList<DestinationCard> destinationCardList = new ArrayList<DestinationCard>();
		BufferedReader br = null;
		String line = "";
		
		try {
			// create a new buffered reader and open the csv file for reading
			br = new BufferedReader(new FileReader(destCSVFile));
			// read in the first line which will be thrown out because we don't
			// want
			// to capture the column headings of the CSV file.
			line = br.readLine();

			// as long as there is another line in the file, we want to parse
			// it.
			while ((line = br.readLine()) != null) {
				// splits the csv line text into an array of strings
				String[] destinationCardArray = line.split(csvSplitBy);
				// create an object for this line of data and add it to the
				// destinationCardList
				destinationCardList.add(new DestinationCard(destinationCardArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close io connections
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return destinationCardList;
	}

	/**
	 * Reads a csv file of city data line by line For each line it creates a new
	 * city object and adds it to the list, then returns the list
	 * 
	 * @return cityList a list of city objects
	 */
	public static ArrayList<City> parseCities() {
		ArrayList<City> cityList = new ArrayList<City>();
		BufferedReader br = null;
		String line = "";
		
		try {
			// create a new buffered reader and open the csv file for reading
			br = new BufferedReader(new FileReader(cityCSVFile));
			// read in the first line which will be thrown out because we don't
			// want
			// to capture the column headings of the CSV file.
			line = br.readLine();

			// as long as there is another line in the file, we want to parse
			// it.
			while ((line = br.readLine()) != null) {
				// splits the csv line text into an array of strings
				String[] cityArray = line.split(csvSplitBy);
				// create an object for this line of data and add it to the
				// cityList
				cityList.add(new City(cityArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close io connections
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return cityList;
	}

	/**
	 * Reads a csv file of route data line by line For each line it creates a
	 * new Path object and adds it to the list, then returns the list
	 * 
	 * @return pathList a list of Path objects
	 */
	public static ArrayList<Path> parseRoutes() {
		ArrayList<Path> pathList = new ArrayList<Path>();
		BufferedReader br = null;
		String line = "";

		try {
			// create a new buffered reader and open the csv file for reading
			br = new BufferedReader(new FileReader(routeCSVFile));
			// read in the first line which will be thrown out because we don't
			// want
			// to capture the column headings of the CSV file.
			line = br.readLine();

			// as long as there is another line in the file, we want to parse
			// it.
			while ((line = br.readLine()) != null) {
				// splits the csv line text into an array of strings
				String[] pathArray = line.split(csvSplitBy);
				// create an object for this line of data and add it to the
				// pathList
				pathList.add(new Path(pathArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close io connections
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return pathList;
	}

}
