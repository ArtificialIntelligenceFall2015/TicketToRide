/**
 * PathHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.List;

import TicketToRide.Model.City;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCard;
import TicketToRide.Model.World;

/**
 * @author Jun He This class handler path functionality
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
		for (Path path : World.map) {
			int c1 = World.cities.indexOf(path.getCity1());
			int c2 = World.cities.indexOf(path.getCity2());
			pathMatrix[c1][c2] = true;
			pathMatrix[c2][c1] = true;
		}
	}

	/**
	 * determine path close
	 * 
	 * @param cards
	 * @param paths
	 */
	public static void determinePathClose(Player player) {
		for(DestinationCard ticket:player.getDesCards()){
			AStar aStar=new AStar(player, ticket);
			aStar.run();
			boolean completed=false;
			if(aStar.getGoal()!=null&&aStar.getGoal().getCost()==0){
				completed=true;
			}
			ticket.setCompleted(completed);
		}
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static List<Path> getPath(City c1, City c2) {
		String key = getKey(c1, c2);
		return World.citiesPath.get(key);
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @param path
	 */
	public static void addCitysPath(City c1, City c2, Path path) {
		String key = getKey(c1, c2);

		if (World.citiesPath.containsKey(key)) {
			World.citiesPath.get(key).add(path);
		} else {
			List<Path> list = new ArrayList<Path>();
			list.add(path);
			World.citiesPath.put(key, list);
		}
	}

	private static String getKey(City c1, City c2) {
		String key = "";
		String c1Name = c1.getCityName();
		String c2Name = c2.getCityName();
		if (c1Name.compareTo(c2Name) < 0) {
			key = c1Name + c2Name;
		} else {
			key = c2Name + c1Name;
		}
		return key;
	}

	public static boolean canClaimBy(Path path, TrainCard card) {
		return canClaimBy(path.getColor(), card.getColor());
	}

	public static boolean canClaimBy(pathColor path, TrainCard card) {
		return canClaimBy(path, card.getColor());
	}

	public static boolean canClaimBy(Path path, trainCard card) {
		return canClaimBy(path.getColor(), card);
	}

	public static boolean canClaimBy(pathColor path, trainCard card) {
		if (path == pathColor.GRAY || card == trainCard.RAINBOW) {
			return true;
		}
		return path.toString().equals(card.toString());
	}
	
	public static List<City> getConnectCities(City city){
		List<City> connectCities=new ArrayList<City>();
		for(int i=0; i<size; i++){
			int index=World.cities.indexOf(city);
			if(pathMatrix[index][i]==true){
				connectCities.add(World.cities.get(i));
			}
		}
		return connectCities;
	}
}
