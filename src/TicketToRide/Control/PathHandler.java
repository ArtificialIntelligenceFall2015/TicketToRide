/**
 * PathHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.List;

import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.World;

/**
 * @author Jun He
 * This class handler path functionality
 */
public class PathHandler {
	private static int size;
	private static boolean[][] matrix;

	/**
	 * Generate global 2d array as path close assistance
	 */
	static {
		size = World.city.size();
		matrix = new boolean[size][size];
	}
	
	/**
	 * determine path close
	 * @param cards
	 * @param paths
	 */
	public static void determinePathClose(DestinationCard cards, List<Path> paths){
		for(Path path:paths){
			matrix[World.city.indexOf(path.getCity1())][World.city.indexOf(path.getCity2())]=true;
			matrix[World.city.indexOf(path.getCity2())][World.city.indexOf(path.getCity1())]=true;
		}
		cards.setCompleted(pathClose(cards.getCity1(),cards.getCity2(),new ArrayList<String>()));
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param closer
	 * @return
	 */
	private static boolean pathClose(String start, String end, List<String> closer) {
		boolean close = false;
		if (start.equals(end))
			return true;
		else {
			int index = World.city.indexOf(start);
			for (int i = 0; i < size; i++) {
				if (matrix[index][i] && !closer.contains(World.city.get(i))) {
					List<String> closerCopy = new ArrayList<String>();
					for (String c: closer) {
						closerCopy.add(c);
					}
					closerCopy.add(start);
					close=close||pathClose(World.city.get(i),end,closerCopy);
				}
			}
		}
		return close;
	}

}
