/**
 * 
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import TicketToRide.Model.City;
import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.strategies;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;

/**
 * @author junxnhe
 *
 */
public class CostStrategies {

	/**
	 * do i own the path from x->y? (if someone else owns it, it isnt a valid
	 * path) if own by player, return cost 0, no other calculation
	 * 
	 * @param player
	 * @param path
	 * @return
	 */
	public static boolean ownPath(Player player, Path path) {
		return path.getOwningPlayer() == player;
	}

	/**
	 * Strategies RR
	 * 
	 * what is my rank on the scoreboard? (if im in the lead, i could be
	 * conservative and take small routes that keep me there and avoid big
	 * routes that might deduct points) (if im in last place, i can afford to be
	 * risky and go for big route cards to gain a lead) cost/rank, if less than
	 * 1, rank/cost
	 * 
	 * @param player
	 * @param path
	 * @return
	 */
	private static int routeVSRank(PlayerAI player, Path path) {
		int cost = path.getCost();
		int rank = Game.getRank(player);
		return cost > rank ? cost / rank : rank / cost;
	}

	/**
	 * how close are we to the end of the game? (every player’s inventory of
	 * train pieces) how many train pieces do i have left? (is it possible to
	 * complete the route with the shortage of trains i have left to place on
	 * the board?)
	 * 
	 * @return
	 */
	private static int closeEndGame() {
		int n = 45;
		for (Player player : Game.players)
			n = Math.min(n, player.getPiece());
		return n / 9;
	}

	/**
	 * Strategies PP
	 * 
	 * does this path connect 2 paths owned by another player/players? (can i
	 * block someone from completing a route? am i in a rush to complete this
	 * route before someone cuts me off?)
	 * 
	 * @param path
	 * @return
	 */
	private static int pairPlayers(Path path) {
		int n = 0;
		List<Player> p1 = getOwningPlayerList(path.getCity1());
		List<Player> p2 = getOwningPlayerList(path.getCity2());

		for (Player p : p2) {
			if (p1.contains(p)) {
				n++;
			}
		}
		return n;
	}

	/**
	 * Strategies RC
	 * 
	 * @param path
	 * @return
	 */
	private static int routeVSCard(PlayerAI player, Path path) {
		HashMap<trainCard, Integer> collection=player.getHandCollection();
		int numRainbow=CardHandler.getCollectionAmount(player, trainCard.RAINBOW);
		int numOther=0;
		if(path.getColor()==pathColor.GRAY){
			Iterator<Entry<trainCard, Integer>> it=collection.entrySet().iterator();
			while(it.hasNext()){
				Entry<trainCard, Integer> pair= it.next();
				if(pair.getKey()!=trainCard.RAINBOW){
					numOther=Math.max(numOther, pair.getValue());
				}
			}
		}else{
			trainCard card=trainCard.valueOf(path.getColor().toString());
			numOther=CardHandler.getCollectionAmount(player, card);
		}
		
		int total=numRainbow+numOther;
		return path.getCost()/(total+1);
	}

	/**
	 * Strategies CR
	 * 
	 * can one or more of the routes on these destination cards be combined? (do
	 * they overlap, killing two birds with one stone?) The higher combined
	 * route, which has better cost
	 * 
	 * @param player
	 * @param path
	 * @return
	 */
	private static int combineRoute(PlayerAI player, Path path) {
		int n = 0;
		for (List<Path> paths : player.getFavorPath()) {
			for (Path p : paths) {
				if (p == path)
					n++;
			}
		}
		return n;
	}
	

	/**
	 * Strategies PL, PM
	 * 
	 * @param p
	 * @param b
	 * @return
	 */
	private static int preferred(Path p, boolean flag) {
		if(flag)
			return 7-p.getCost();
		
		int[] weight={0,5,3,1,2,4,6};
		return weight[p.getCost()];
	}

	/**
	 * 
	 * @param cityFromPath
	 * @return
	 */
	private static List<Player> getOwningPlayerList(City cityFromPath) {
		List<City> connectCities = PathHandler.getConnectCities(cityFromPath);
		List<Player> players = new ArrayList<Player>();
		for (City city : connectCities) {
			List<Path> paths = PathHandler.getPath(cityFromPath, city);
			for (Path p : paths) {
				if (p.getOwningPlayer() != null) {
					players.add(p.getOwningPlayer());
				}
			}
		}
		return players;
	}

	/**
	 * 
	 * @param player
	 * @param p
	 * @return
	 */
	public static int getCost(Player player, Path p) {
		if (player instanceof PlayerAI) {
			return calAICost((PlayerAI) player, p);
		} else {
			return p.getCost();
		}
	}

	/**
	 * 
	 * @param player
	 * @param p
	 * @return
	 */
	private static int calAICost(PlayerAI player, Path p) {
		int sum = 0;
		strategies[] list = player.getStrategiesList();
		for (int i = 0; i < list.length; i++) {
			sum += getCostResult(player, p, list[i]) * (i + 1);
		}
		return sum;
	}

	/**
	 * 
	 * @param player 
	 * @param p 
	 * @param s
	 * @return
	 */
	private static int getCostResult(PlayerAI player, Path p, strategies s) {
		int n=0;
		switch (s) {
		case CR:
			n= combineRoute(player, p);
			break;
		case RC:
			n= routeVSCard(player, p);
			break;
		case PP:
			n= pairPlayers(p);
			break;
		case RR:
			n= routeVSRank(player, p);
			break;
		case PM:
			n= preferred(p, false);
			break;
		case PL:
			n= preferred(p, true);
			break;
		}
		return n;
	}
}
