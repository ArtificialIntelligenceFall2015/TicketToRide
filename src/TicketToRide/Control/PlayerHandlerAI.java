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
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.TrainCard;

/**
 * This class help decision making for AI
 * @author junxnhe
 *
 */
public class PlayerHandlerAI extends PlayerHandler{

	/**
	 * 
	 * @param player
	 * @return
	 */
	public static decision decisionMaking(PlayerAI player){
		if(routeClaimable(player)){
			return decision.CLAIM_A_ROUTE;
		}else if(completedAllDesTickets(player)){
			return decision.DRAW_DES_TICKETS;
		}else{
			return decision.DRAW_TRAIN_CARDS;
		}
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	private static boolean completedAllDesTickets(PlayerAI player) {
		for(DestinationCard ticket:player.getDesCards()){
			if(!ticket.isCompleted()){
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
		HashMap<pathColor, Integer> minPathCost=PathHandler.minPathCost(player);
		int holdRainbow=Deck.count(player.getTrainCards(), trainCard.RAINBOW);
		boolean claimable=false;
		Entry<pathColor, Integer> claimColor = null;
		pathColor wantClaimColor =null;
		int offNum=0;
		
		Iterator<Entry<pathColor, Integer>> iterator=minPathCost.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<pathColor, Integer> pair=iterator.next();
			pathColor color=pair.getKey();
			int cost=pair.getValue();
			if(color==pathColor.GRAY){
				if(cost<=player.getOwnPath().size()){
					claimable= true;
					if(claimColor==null||cost>claimColor.getValue()){
						claimColor=pair;
					}
				}
			}else{
				int playerHold=Deck.count(player.getTrainCards(), color.toString())+holdRainbow;
				if(cost<=playerHold){
					claimable= true;
					if(claimColor==null||cost>claimColor.getValue()){
						claimColor=pair;
					}
				}else if(!claimable){
					if(wantClaimColor==null||offNum>cost-playerHold){
						wantClaimColor=color;
						offNum=cost-playerHold;
					}
				}
			}
		}
		
		player.setClaimColor(claimColor);
		player.setWantClaimColor(wantClaimColor);
		
		return claimable;
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void drawTrainCardAI(PlayerAI player) {
		pathColor wantClaimColor=player.getWantClaimColor();
		for(int i=0; i<2; i++){
			int index=-1;
			for(TrainCard card:Deck.trainFaceUpCards){
				if(card.getColor().toString().equals(wantClaimColor.toString())){
					index=Deck.trainFaceUpCards.indexOf(card);
				}
			}
			if(index>=0){
				drawTrainCard(player,index);
			}else{
				drawTrainCard(player);
			}
		}
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void claimARouteAI(PlayerAI player){
		Path claimPath=PathHandler.findPath(player);
		List<TrainCard> cardsToSpend=new ArrayList<TrainCard>();
		int cost=claimPath.getCost();
		pathColor color=claimPath.getColor();
		List<TrainCard> playerHoldCards=player.getTrainCards();

		if(color==pathColor.GRAY){
			cardsToSpend=playerHoldCards.subList(0, cost);
		}else{
			List<TrainCard> matchColorCards=new ArrayList<TrainCard>();
			List<TrainCard> rainbowCards=new ArrayList<TrainCard>();
			for(TrainCard card:playerHoldCards){
				if(card.getColor().toString().equals(color.toString())){
					matchColorCards.add(card);
				}else if(card.getColor()==trainCard.RAINBOW){
					rainbowCards.add(card);
				}
				if(matchColorCards.size()>=cost){
					cardsToSpend=matchColorCards.subList(0, cost);
				}else{
					cardsToSpend=matchColorCards;
					cardsToSpend.addAll(rainbowCards.subList(0, cost-matchColorCards.size()));
				}
			}
		}
		claimARoute(player,claimPath,cardsToSpend);
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void drawDesTicketsAI(PlayerAI player) {
		List<DestinationCard> tickets=drawDesTickets(player);
		List<Integer> costs=new ArrayList<Integer>();
		int minCost=0;
		
		for(DestinationCard ticket: tickets){
			AStar aStar=new AStar(player,ticket);
			aStar.run();
			int cost=aStar.getGoal().getCost();
			costs.add(cost);
			if(minCost!=0||minCost>cost){
				minCost=cost;
			}
		}
		
		for(int i=0; i<tickets.size(); i++){
			if(minCost==costs.get(i)||Constants.TAKENCOST>=costs.get(i)){
				player.getDesCards().add(tickets.get(i));
			}else{
				returnDesCardToDeck(tickets.get(i));
			}
		}
	}
}
