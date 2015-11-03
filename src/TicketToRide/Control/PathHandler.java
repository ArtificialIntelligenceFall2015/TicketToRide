/**
 * PathHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.World;
import TicketToRide.Model.City;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.trainCard;

/**
 * @author Jun He
 * This class handler path functionality
 */
public class PathHandler {
	private static int size;
	public static boolean[][] pathMatrix;

	/**
	 * Generate global 2d array as path close assistance
	 */
	static {
		size = World.cities.size();
		pathMatrix = new boolean[size][size];
	}
	
	/**
	 * determine path close
	 * @param cards
	 * @param paths
	 */
	public static void determinePathClose(DestinationCard cards, List<Path> paths){
		for(Path path:paths){
			int c1=World.cities.indexOf(path.getCity1());
			int c2=World.cities.indexOf(path.getCity2());
			pathMatrix[c1][c2]=true;
			pathMatrix[c2][c1]=true;
		}
		cards.setCompleted(pathClose(cards.getCity1(),cards.getCity2(),new ArrayList<City>()));
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param closer
	 * @return
	 */
	private static boolean pathClose(City start, City end, List<City> closer) {
		boolean close = false;
		if (start.equals(end))
			return true;
		else {
			int index = World.cities.indexOf(start);
			for (int i = 0; i < size; i++) {
				if (pathMatrix[index][i] && !closer.contains(World.cities.get(i))) {
					List<City> closerCopy = new ArrayList<City>();
					for (City c: closer) {
						closerCopy.add(c);
					}
					closerCopy.add(start);
					close=close||pathClose(World.cities.get(i),end,closerCopy);
				}
			}
		}
		return close;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public static HashMap<pathColor, Integer> minPathCost(PlayerAI player){
		HashMap<pathColor, Integer> pathCollection=new HashMap<pathColor, Integer>();
		for(List<Path> list:player.getFavorPath()){
			for(Path path: list){
				if(path.getOwningPlayer()==null){
					int cost=path.getCost();
					pathColor color=path.getColor();
					if((pathCollection.containsKey(color)&&pathCollection.get(color)>cost)||!pathCollection.containsKey(color)){
						pathCollection.put(color, cost);
					}
				}
			}
		}
		
		return pathCollection;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public static Path findPathByColorAndCost(PlayerAI player){
		Entry<pathColor, Integer> claimColor =player.getClaimColor();
		for(List<Path> list:player.getFavorPath()){
			for(Path path: list){
				if(path.getColor()==claimColor.getKey()&&path.getCost()==claimColor.getValue())
					return path;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static List<Path> getPath(City c1, City c2) {
		String key = getKey(c1,c2);
		return World.citiesPath.get(key);
	}
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @param path
	 */
	public static void addCitysPath(City c1, City c2, Path path){
		String key = getKey(c1,c2);
		
		if(World.citiesPath.containsKey(key)){
			World.citiesPath.get(key).add(path);
		}else{
			List<Path> list=new ArrayList<Path>();
			list.add(path);
			World.citiesPath.put(key, list);
		}
	}
	
	private static String getKey(City c1, City c2){
		String key = "";
		String c1Name=c1.getCityName();
		String c2Name=c2.getCityName();
		if (c1Name.compareTo(c2Name) < 0) {
			key = c1Name + c2Name;
		} else {
			key = c2Name + c1Name;
		}
		return key;
	}

}
