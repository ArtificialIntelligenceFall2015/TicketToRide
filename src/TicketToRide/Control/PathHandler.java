/**
 * PathHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

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
		int n=0;
		for (DestinationCard ticket : player.getDesCards()) {
			AStar aStar = new AStar(player, ticket);
			aStar.run();
			boolean completed = false;
			if (aStar.getGoal() != null && aStar.getGoal().getCost() == 0) {
				completed = true;
				n++;
			}
			ticket.setCompleted(completed);
		}
		player.setNumTicketComplete(n);
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

	/**
	 * 
	 * @param path
	 * @param card
	 * @return
	 */
	public static boolean canClaimBy(Path path, TrainCard card) {
		return canClaimBy(path.getColor(), card.getColor());
	}

	/**
	 * 
	 * @param path
	 * @param card
	 * @return
	 */
	public static boolean canClaimBy(pathColor path, TrainCard card) {
		return canClaimBy(path, card.getColor());
	}

	/**
	 * 
	 * @param path
	 * @param card
	 * @return
	 */
	public static boolean canClaimBy(Path path, trainCard card) {
		return canClaimBy(path.getColor(), card);
	}

	/**
	 * 
	 * @param path
	 * @param card
	 * @return
	 */
	public static boolean canClaimBy(pathColor path, trainCard card) {
		if (path == pathColor.GRAY || card == trainCard.RAINBOW) {
			return true;
		}
		return path.toString().equals(card.toString());
	}

	/**
	 * 
	 * @param city
	 * @return
	 */
	public static List<City> getConnectCities(City city) {
		List<City> connectCities = new ArrayList<City>();
		for (int i = 0; i < size; i++) {
			int index = World.cities.indexOf(city);
			if (pathMatrix[index][i] == true) {
				connectCities.add(World.cities.get(i));
			}
		}
		return connectCities;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Player> getLongestPathPlayers(){
		List<Player> players=new ArrayList<Player>();
		List<Integer> longestPathWeight=new ArrayList<Integer>();
		int max=0;
		for(Player p:Game.players){
			int weight=getLongestPath(p);
			longestPathWeight.add(weight);
			max=Math.max(max, weight);
		}
		for(int i=0; i<longestPathWeight.size(); i++){
			if(longestPathWeight.get(i)==max){
				players.add(Game.players.get(i));
			}
		}
		return players;
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public static int getLongestPath(Player player) {
		int max=0;
		boolean[] cityAssocPath=getCityAssocPath(player);
		
		for(int i=0; i<cityAssocPath.length; i++){
			if(cityAssocPath[i]){
				City city=World.cities.get(i);
				int longestPathDAG=longestPathDAG(player,city, new ArrayList<Path>());
				max=Math.max(max, longestPathDAG);
			}
		}
		return max;
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	private static boolean[] getCityAssocPath(Player player) {
		boolean[] cityAssocPath=new boolean[World.cities.size()];
		for(Path path:player.getOwnPath()){
			cityAssocPath[World.cities.indexOf(path.getCity1())]=true;
			cityAssocPath[World.cities.indexOf(path.getCity2())]=true;
		}
		return cityAssocPath;
	}

	/**
	 * 
	 * @param player
	 * @param city
	 * @param visited
	 * @return
	 */
	private static int longestPathDAG(Player player, City city, List<Path> visited) {
		int max=0;
		List<Path> adjPaths=new ArrayList<Path>();
		int index=World.cities.indexOf(city);
		for(int i=0; i<size; i++){
			if(pathMatrix[index][i]){
				List<Path> paths=getPath(World.cities.get(index), World.cities.get(i));
				for(Path path:paths){
					if(path.getOwningPlayer()==player){
						adjPaths.add(path);
					}
				}
			}
		}
		
		for(Path path: adjPaths){
			if(!visited.contains(path)){
				List<Path> newVisitedPath=new ArrayList<Path>();
				newVisitedPath.addAll(visited);
				newVisitedPath.add(path);
				City c=path.getCity1()==city?path.getCity2():path.getCity1();
				max=Math.max(max, longestPathDAG(player,c,newVisitedPath)+path.getCost());
			}
		}
		return max;
	}
	
	public static List<Path> generateUnclaimedRoutes() {
		List<Path> unclaimedRoutes = new ArrayList<Path>();
		Player player=Game.currentPlayer;
		HashMap<trainCard, Integer> collection=CardHandler.trainCardCollection(player.getTrainCards());
		
		int maxClaimableCards=0;//non-rainbow claimable cards
		int numRainbow=CardHandler.getCollectionAmount(collection, trainCard.RAINBOW);
		
		Iterator<Entry<trainCard, Integer>> iterator = collection.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<trainCard, Integer> pair = iterator.next();
			if (pair.getKey()!=trainCard.RAINBOW && maxClaimableCards<pair.getValue()){
				maxClaimableCards=pair.getValue();
			}
		}
		
		maxClaimableCards+=numRainbow;
		
		//collect claimable cards
		for (Path p : World.map) {
			if (p.getOwningPlayer() == null) {
				//Check double route and another one own by the current player
				List<Path> pairRoute=PathHandler.getPath(p.getCity1(), p.getCity2());
				boolean own=false;
				if(pairRoute.size()>1 && 
						(pairRoute.get(0).getOwningPlayer()==player 
						|| pairRoute.get(1).getOwningPlayer()==player)){
					own=true;	
				}
				
				if((!own&&
						p.getColor()==pathColor.GRAY 
						&& maxClaimableCards>=p.getCost())
						||(p.getColor()!=pathColor.GRAY 
						&& numRainbow+CardHandler.getCollectionAmount(collection, trainCard.valueOf(p.getColor().toString()))>=p.getCost())){
					unclaimedRoutes.add(p);
				}
			}
		}
		
		return unclaimedRoutes;
		
	}
}
