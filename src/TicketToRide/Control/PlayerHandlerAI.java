package TicketToRide.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.decision;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.TrainCard;

/**
 * This class help decision making for AI
 * 
 * @author junxnhe
 *
 */
public class PlayerHandlerAI extends PlayerHandler {
	
	/**
	 * init method
	 * call this method before do AI functions
	 * @param player
	 */
	public static void populateAIFields(PlayerAI player){
		HashMap<trainCard, Integer> handCollection = CardHandler.trainCardCollection(player.getTrainCards());
		HashMap<trainCard, Integer> deckCollection = CardHandler.trainCardCollection(Deck.trainFaceUpCards);
		player.setHandCollection(handCollection);
		player.setDeckCollection(deckCollection);
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public static decision decisionMaking(PlayerAI player) {
		if (routeClaimable(player)) {
			return decision.CLAIM_A_ROUTE;
		} else if (completedAllDesTickets(player)) {
			return decision.DRAW_DES_TICKETS;
		} else {
			return decision.DRAW_TRAIN_CARDS;
		}
	}
	
	/**
	 * 
	 * @param player
	 * @param d
	 */
	public static void performAction(PlayerAI player, decision d){
		switch(d){
		case CLAIM_A_ROUTE:
			claimARouteAI(player);
			break;
		case DRAW_DES_TICKETS:
			drawDesTicketsAI(player);
			break;
		case DRAW_TRAIN_CARDS:
			drawTrainCardAI(player);
			break;
		default:
		}
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	private static boolean completedAllDesTickets(PlayerAI player) {
		for (DestinationCard ticket : player.getDesCards()) {
			if (!ticket.isCompleted() && !player.getUncompleteableDesCard().contains(ticket)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	private static boolean routeClaimable(PlayerAI player) {
		Path wantClaimPath = null;
		boolean claimable = false;
		int offset = 0;

		for (List<Path> list : player.getFavorPath()) {
			for (Path path : list) {
				if (path.getOwningPlayer() == null) {
					int tempOffSet = numClaimAbleCards(path.getColor(), player.getHandCollection());
					if (offset < tempOffSet) {
						wantClaimPath = path;
						offset = tempOffSet;
					}
				}
			}
		}

		if (offset >= 0) {
			claimable = true;
		}

		player.setWantClaimPath(wantClaimPath);
		return claimable;
	}

	/**
	 * 
	 * @param player
	 */
	public static void drawTrainCardAI(PlayerAI player) {
		pathColor wantClaimColor = player.getWantClaimPath().getColor();
		for (int i = 0; i < 2; i++) {
			int index = -1;
			for (TrainCard card : Deck.trainFaceUpCards) {
				if (PathHandler.canClaimBy(wantClaimColor, card)) {
					index = Deck.trainFaceUpCards.indexOf(card);
				}
			}
			if (index >= 0) {
				drawTrainCard(player, index);
			} else {
				drawTrainCard(player);
			}
		}
	}

	/**
	 * 
	 * @param player
	 */
	public static void claimARouteAI(PlayerAI player) {
		Path claimPath = player.getWantClaimPath();
		List<TrainCard> cardsToSpend = new ArrayList<TrainCard>();
		int cost = claimPath.getCost();
		List<TrainCard> playerHoldCards = player.getTrainCards();

		for (int i = 0; cost > 0; i++) {
			TrainCard card = playerHoldCards.get(i);
			if (PathHandler.canClaimBy(claimPath, card)) {
				cardsToSpend.add(card);
				cost--;
			}
		}

		claimARoute(player, claimPath, cardsToSpend);
	}

	/**
	 * 
	 * @param player
	 */
	public static void drawDesTicketsAI(PlayerAI player) {
		List<DestinationCard> tickets = drawDesTickets(player);
		List<Integer> costs = new ArrayList<Integer>();
		int minCost = 0;

		for (DestinationCard ticket : tickets) {
			AStar aStar = new AStar(player, ticket);
			aStar.run();
			int cost = 0;
			if (aStar.getGoal() == null) {
				cost = Integer.MAX_VALUE;
			} else {
				cost = aStar.getGoal().getCost();
			}
			costs.add(cost);
			if (minCost != 0 || minCost > cost) {
				minCost = cost;
			}
		}

		for (int i = 0; i < tickets.size(); i++) {
			if (minCost == costs.get(i) || Constants.TAKENCOST >= costs.get(i)) {
				player.getDesCards().add(tickets.get(i));
			} else {
				returnDesCardToDeck(tickets.get(i));
			}
		}
	}

	private static int numClaimAbleCards(pathColor color, HashMap<trainCard, Integer> cardCollection) {
		int num = 0;
		Iterator<Entry<trainCard, Integer>> iterator = cardCollection.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<trainCard, Integer> pair = iterator.next();
			if (PathHandler.canClaimBy(color, pair.getKey())) {
				num += pair.getValue();
			}
		}
		return num;

	}
}
