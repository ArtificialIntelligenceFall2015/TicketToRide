/**
 * 
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.List;

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

	// what is the point value on the destination card to complete the entire
	// route? (is it worth the effort? conservative players choose small point
	// routes, risky players choose big point routes)

	/**
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
	private static int claimRouteByRank(PlayerAI player, Path path) {
		int cost = path.getCost();
		int rank = Game.getRank(player);
		if (cost >= rank) {
			return cost / rank;
		} else {
			return rank / cost;
		}
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
	 * what is point value associated with claiming the route? (15 points for a
	 * 6 car route vs only 2 points for a 2 car route…)
	 * 
	 * @param path
	 * @return
	 */
	private static int routeScore(Path path) {
		return Constants.routeScore[path.getCost()];
	}

	/**
	 * what train cards are currently in my hand? what train cards are face up?
	 * combine the total number of claim able cards and make the difference
	 * between the cost of path the more close to the cost of path, it return
	 * lower cost strategies
	 * 
	 * @param player
	 * @param path
	 * @return
	 */
	private static int cardsInHand(PlayerAI player, Path path) {

		int n = 0;
		if (path.getColor() == pathColor.GRAY) {
			n = player.getTrainCards().size();
		} else {
			trainCard tc = trainCard.valueOf(path.getColor().toString());
			n += player.getHandCollection().get(tc) + player.getHandCollection().get(trainCard.RAINBOW);
			n += player.getDeckCollection().get(tc) + player.getDeckCollection().get(trainCard.RAINBOW);
		}
		return Math.abs(n - path.getCost());
	}

	/**
	 * what is the probability of the train cards i need to complete this
	 * route/path being in the deck? + keep track of total cards of every color
	 * starting at beginning of game - cards i have in my hand (both from blind
	 * draw and face up) - cards face up - cards other players have picked up
	 * from face up (can’t know what they picked up from blind draw) - cards
	 * other players have spent on routes/cards ive spent on routes (discard
	 * pile contents)
	 **/

	/**
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
			sum += getCostResult(list[i]) * (i + 1);
		}
		return sum;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	private static int getCostResult(strategies s) {
		switch (s) {
		// TODO
		}
		return 0;
	}
}
