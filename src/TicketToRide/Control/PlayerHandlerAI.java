package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private static PlayerHandlerAI instance=new PlayerHandlerAI();

	/**
	 * init method call this method before do AI functions
	 * 
	 * @param player
	 */
	public static void populateAIFields(PlayerAI player) {
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
	public static void performAction(PlayerAI player, decision d) {
		switch (d) {
		case CLAIM_A_ROUTE:
			claimARouteAI(player);
			break;
		case DRAW_DES_TICKETS:
			drawDesTicketsAI(player, 1);
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
		if(Deck.desCardDeck.isEmpty())
			return false;
		
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
		int numRainbow =  player.getHandCollection().get(trainCard.RAINBOW);

		for (List<Path> list : player.getFavorPath()) {
			for (Path path : list) {
				if (path.getOwningPlayer() == null && player.getPiece()>=path.getCost()) {
					trainCard trainCardColor=claimAbleCards(path.getColor(), player.getHandCollection());
					int tempOffSet = player.getHandCollection().get(trainCardColor)+numRainbow;
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
					if(i==0||(i==1&&card.getColor()!=trainCard.RAINBOW)){
						index = Deck.trainFaceUpCards.indexOf(card);
					}
				}
			}
			if (index >= 0) {
				if(Deck.trainFaceUpCards.get(index).getColor()==trainCard.RAINBOW){
					i++;
				}
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
		trainCard trainCardColor=claimAbleCards(claimPath.getColor(), player.getHandCollection());

		for (int i = 0; cost > 0 && i < playerHoldCards.size(); i++) {
			TrainCard card = playerHoldCards.get(i);
			if (card.getColor()==trainCardColor) {
				cardsToSpend.add(card);
				cost--;
			}
		}
		
		for (int i = 0; cost > 0 && i < playerHoldCards.size(); i++) {
			TrainCard card = playerHoldCards.get(i);
			if (card.getColor()==trainCard.RAINBOW) {
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
	public static void drawDesTicketsAI(PlayerAI player, int minTokenNum) {
		List<DestinationCard> tickets = drawDesTickets(player);
		List<DestinationCardAssist> list=new ArrayList<DestinationCardAssist>();
		

		for (DestinationCard ticket:tickets) {
			AStar aStar = new AStar(player, ticket);
			aStar.run();
			int cost=aStar.getGoal().getCost();
			list.add(PlayerHandlerAI.getInstance().getAssistClass(ticket, cost));
		}
		
		Collections.sort(list);
		
		for(int i=0; i<list.size(); i++){
			if(i<minTokenNum||list.get(i).cost<Constants.TAKENCOST)
				player.getDesCards().add(list.get(i).ticket);
		}
	}

	/**
	 * 
	 * @param color
	 * @param cardCollection
	 * @return
	 */
	private static trainCard claimAbleCards(pathColor color, HashMap<trainCard, Integer> cardCollection) {
		if(color!=pathColor.GRAY){
			return trainCard.valueOf(color.name());
		}
		
		int maxClaimableCards=0;//non-rainbow claimable cards
		trainCard trainCardColor=null;
		Iterator<Entry<trainCard, Integer>> iterator = cardCollection.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<trainCard, Integer> pair = iterator.next();
			if (pair.getKey()!=trainCard.RAINBOW && PathHandler.canClaimBy(color, pair.getKey())) {
				if(maxClaimableCards<pair.getValue()){
					maxClaimableCards=pair.getValue();
					trainCardColor=pair.getKey();
				}
			}
		}
		return trainCardColor;
	}
	
	/**
	 * 
	 * @return
	 */
	protected static PlayerHandlerAI getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * @param ticket
	 * @param cost
	 * @return
	 */
	private DestinationCardAssist getAssistClass(DestinationCard ticket, int cost){
		return new DestinationCardAssist(ticket,cost);
	}
	
	/**
	 * 
	 * @author jhe
	 *
	 */
	private class DestinationCardAssist implements Comparable<DestinationCardAssist>{

		DestinationCard ticket;
		int cost;
		
		/**
		 * 
		 * @param ticket
		 * @param cost
		 */
		private DestinationCardAssist(DestinationCard ticket, int cost) {
			this.ticket = ticket;
			this.cost = cost;
		}

		@Override
		public int compareTo(DestinationCardAssist o) {
			return cost-o.cost;
		}
	}
}
