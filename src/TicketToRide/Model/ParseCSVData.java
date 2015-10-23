/**
 * ParseCSVData.java
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
	private static String destCSVFile = "";
	private static String cityCSVFile = "";
	private static String routeCSVFile = "";
	private static String line = "";
	private static String csvSplitBy = ",";
	private static BufferedReader br = null;

	public static ArrayList<DestinationCard> parseDestinationCards() {
		ArrayList<DestinationCard> destinationCardList = new ArrayList<DestinationCard>();
		br = null;

		try {
			br = new BufferedReader(new FileReader(destCSVFile));
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] destinationCardArray = line.split(csvSplitBy);
				destinationCardList.add(new DestinationCard(
						destinationCardArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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

	public static ArrayList<City> parseCities() {
		ArrayList<City> cityList = new ArrayList<City>();
		br = null;

		try {
			br = new BufferedReader(new FileReader(cityCSVFile));
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] cityArray = line.split(csvSplitBy);
				cityList.add(new City(cityArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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

	public static ArrayList<Path> parseRoutes() {
		ArrayList<Path> pathList = new ArrayList<Path>();
		br = null;

		try {
			br = new BufferedReader(new FileReader(routeCSVFile));
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] pathArray = line.split(csvSplitBy);
				pathList.add(new Path(pathArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
