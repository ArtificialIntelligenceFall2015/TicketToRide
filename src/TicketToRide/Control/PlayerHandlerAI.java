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
import TicketToRide.Model.World;
import TicketToRide.View.TicketToRideGui;

/**
 * This class help decision making for AI
 * 
 * @author junxnhe
 *
 */
public class PlayerHandlerAI extends PlayerHandler {

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
		
		for(DestinationCard ticket:player.getDesCards()){
			if(!ticket.isCompleted()&&!player.getUncompleteableDesCard().contains(ticket)){
				Frontier goal=null;
				AStar aStar=new AStar(player, ticket);
				aStar.run();
				goal=aStar.getGoal();
				
				if(goal==null){
					player.getUncompleteableDesCard().add(ticket);
				}else if(goal.getCost()==0){
					ticket.setCompleted(true);
				}else{
					List<Path> paths=new ArrayList<Path>();
					for(int i=1; i<goal.getList().size();i++){
						List<Path> p=PathHandler.getPath(goal.getList().get(i-1), goal.getList().get(i));
						if(p.get(0).getOwningPlayer()==null||p.get(0).getOwningPlayer()==player){
							paths.add(p.get(0));
						}else{
							paths.add(p.get(1));
						}
					}
					player.getFavorPath().add(paths);
				}
			}
		}
	}

	/**
	 * Decision making method
	 * if has route can be claim, claim a route first
	 * if all destination cards in hand are completed or incompletable or no train cards in desk can be draw, draw destination cards
	 * else draw train card
	 * 
	 * @param player
	 * @return
	 */
	public static decision decisionMaking(PlayerAI player) {
		if (routeClaimable(player)) {
			return decision.CLAIM_A_ROUTE;
		} else if (mustDrawDesTickets(player)) {
			return decision.DRAW_DES_TICKETS;
		} else {
			return decision.DRAW_TRAIN_CARDS;
		}
	}

	/**
	 * perform action base on decision
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
	 * check if all destination cards in hand are completed or incompletable or no train cards in desk can be draw
	 * @param player
	 * @return
	 */
	private static boolean mustDrawDesTickets(PlayerAI player) {
		if(Deck.trainCardsDeck.isEmpty())
			return true;
		
		for (DestinationCard ticket : player.getDesCards()) {
			if (!ticket.isCompleted() && !player.getUncompleteableDesCard().contains(ticket)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check if there exist route can be claim base on path what AStar generated
	 * @param player
	 * @return
	 */
	private static boolean routeClaimable(PlayerAI player) {
		if(player.getTrainCards().size()==0)
			return false;
		
		Path wantClaimPath = null;
		boolean claimable = false;
		int offset = Integer.MIN_VALUE;
		int numRainbow =  CardHandler.getCollectionAmount(player, trainCard.RAINBOW);

		for (List<Path> list : player.getFavorPath()) {
			for (Path path : list) {
				if (path.getOwningPlayer() == null && player.getPiece()>=path.getCost()) {
					trainCard trainCardColor=claimAbleCards(path.getColor(), player.getHandCollection());
					int tempOffSet = numRainbow - path.getCost();
					if(trainCardColor!=null)
						tempOffSet += CardHandler.getCollectionAmount(player, trainCardColor);
					if (offset < tempOffSet) {
						wantClaimPath = path;
						offset = tempOffSet;
					}
				}
			}
		}
		
		if(wantClaimPath==null){
			for(Path path:World.map){
				if(path.getOwningPlayer()==null && player.getPiece()>=path.getCost()){
					trainCard trainCardColor=claimAbleCards(path.getColor(), player.getHandCollection());
					int tempOffSet = numRainbow - path.getCost();
					if(trainCardColor!=null)
						tempOffSet += CardHandler.getCollectionAmount(player, trainCardColor);
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
	 * Draw Train Card action for AI player
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
	 * Claim a route action by AI player
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
	 * Draw Destination card action by AI player
	 * @param player
	 */
	public static void drawDesTicketsAI(PlayerAI player, int minTokenNum) {
		List<DestinationCard> tickets = drawDesTickets();
		List<DestinationCardAssist> list=new ArrayList<DestinationCardAssist>();
		

		for (DestinationCard ticket:tickets) {
			AStar aStar = new AStar(player, ticket);
			aStar.run();
			int cost=Integer.MAX_VALUE;
			if(aStar.getGoal()!=null)
				aStar.getGoal().getCost();
			list.add(new DestinationCardAssist(ticket, cost));
		}
		
		Collections.sort(list);
		List<DestinationCard> tokenCards=new ArrayList<DestinationCard>();
		
		for(int i=0; i<list.size(); i++){
			if(i<minTokenNum||list.get(i).cost<Constants.TAKENCOST){
				player.getDesCards().add(list.get(i).ticket);
				tokenCards.add(list.get(i).ticket);
			}else{
				returnDesCardToDeck(list.get(i).ticket);
			}
		}
		TicketToRideGui.appendLog("took the following destination cards:\n" + tokenCards);
	}

	/**
	 * get train card in collection that is able to to claim the given path color
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
	 * @author jhe
	 *
	 */
	private static class DestinationCardAssist implements Comparable<DestinationCardAssist>{

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
