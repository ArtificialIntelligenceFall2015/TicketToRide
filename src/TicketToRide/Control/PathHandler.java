/**
 * PathHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.city;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;

/**
 * @author Jun He
 *
 */
public class PathHandler {
	private static List<city> cityArray;
	private static int size;
	private static boolean[][] matrix;

	static {
		cityArray = Arrays.asList(Constants.city.values());
		size = cityArray.size();
		matrix = new boolean[size][size];
	}
	
	public static void determinePathClose(DestinationCard cards, List<Path> paths){
		int size=Constants.city.values().length;
		matrix= new boolean[size][size];
		for(Path path:paths){
			matrix[cityArray.indexOf(path.getCity1())][cityArray.indexOf(path.getCity2())]=true;
			matrix[cityArray.indexOf(path.getCity2())][cityArray.indexOf(path.getCity1())]=true;
		}
		cards.setCompleted(pathClose(cards.getCity1(),cards.getCity2(),new ArrayList<city>()));
	}

	private static boolean pathClose(city start, city end, List<city> closer) {
		boolean close = false;
		if (start == end)
			return true;
		else {
			int index = cityArray.indexOf(start);
			for (int i = 0; i < size; i++) {
				if (matrix[index][i] && !closer.contains(cityArray.get(i))) {
					List<city> closerCopy = new ArrayList<city>();
					for (city c: closer) {
						closerCopy.add(c);
					}
					closerCopy.add(start);
					close=close||pathClose(cityArray.get(i),end,closerCopy);
				}
			}
		}
		return close;
	}

}
