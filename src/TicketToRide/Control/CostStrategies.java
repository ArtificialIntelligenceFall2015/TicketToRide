/**
 * 
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Model.City;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.strategies;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;

/**
 * @author jun he
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
	 * If the number of the card more close to the number of cost of route,
	 * return lower integer Strategies RC
	 * 
	 * @param path
	 * @return
	 */
	private static int routeVSCard(PlayerAI player, Path path) {
		HashMap<trainCard, Integer> collection = player.getHandCollection();
		int numRainbow = CardHandler.getCollectionAmount(player,
				trainCard.RAINBOW);
		int numOther = 0;
		if (path.getColor() == pathColor.GRAY) {
			Iterator<Entry<trainCard, Integer>> it = collection.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<trainCard, Integer> pair = it.next();
				if (pair.getKey() != trainCard.RAINBOW) {
					numOther = Math.max(numOther, pair.getValue());
				}
			}
		} else {
			trainCard card = trainCard.valueOf(path.getColor().toString());
			numOther = CardHandler.getCollectionAmount(player, card);
		}

		int total = numRainbow + numOther;
		return path.getCost() / (total + 1);
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
	 * Preferred larger cost path PL Preferred medium cost path PM Strategies
	 * PL, PM
	 * 
	 * @param p
	 * @param b
	 * @return
	 */
	private static int preferred(Path p, boolean flag) {
		if (flag)
			return 7 - p.getCost();

		int[] weight = { 0, 5, 3, 1, 2, 4, 6 };
		return weight[p.getCost()];
	}

	/**
	 * Get player list who owned paths connected to the given city
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
	 * if AStar algorithm return cost base on AI player personality if Lowest
	 * cost first algorithm, return the cost of the route
	 * 
	 * @param player
	 * @param p
	 * @param detementPathCloseFlag
	 * @return
	 */
	public static int getCost(Player player, Path p,
			boolean detementPathCloseFlag) {
		if (!detementPathCloseFlag) {
			return calAICost((PlayerAI) player, p);
		} else {
			return p.getCost();
		}
	}

	/**
	 * calculate cost of path base on AI player personality
	 * 
	 * @param player
	 * @param p
	 * @return
	 */
	private static int calAICost(PlayerAI player, Path p) {
		int sum = 0;
		strategies[] list = player.getStrategiesList();
		for (int i = 0; i < list.length; i++) {
			sum += getCostResult(player, p, list[i]) * (i + 1) * 20;
		}
		return sum;
	}

	/**
	 * Call different method by differnt strategies PM and PL only can exist one
	 * in each AI player
	 * 
	 * @param player
	 * @param p
	 * @param s
	 * @return
	 */
	private static int getCostResult(PlayerAI player, Path p, strategies s) {
		int n = 0;
		switch (s) {
		case CR:
			n = combineRoute(player, p);
			break;
		case RC:
			n = routeVSCard(player, p);
			break;
		case PP:
			n = pairPlayers(p);
			break;
		case RR:
			n = routeVSRank(player, p);
			break;
		case PM:
			n = preferred(p, false);
			break;
		case PL:
			n = preferred(p, true);
			break;
		}
		return n;
	}
}
