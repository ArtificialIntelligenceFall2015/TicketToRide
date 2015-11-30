/**
 * PlayerHandler.java
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCard;
import TicketToRide.View.TicketToRideGui;

/**
 * @author Jun He 
 * @author Sean Fast
 * 
 * This class's methods work as player's behave
 */
public class PlayerHandler {

	private static final int[] POINT = { 0, 1, 2, 4, 7, 10, 15 };

	/**
	 * calculate destination ticket point
	 * 
	 * @param player
	 */
	public static void calcDesCardPoint(Player player) {
		int score = 0;
		for (DestinationCard card : player.getDesCards()) {
			if (card.isCompleted()) {
				score += card.getPoint();
			} else {
				score -= card.getPoint();
			}
		}
		player.setScore(player.getScore() + score);
	}

	/**
	 * Sort cards
	 * 
	 * @param player
	 */
	public static void organizeCard(Player player) {
		Collections.sort(player.getTrainCards());
	}

	/**
	 * perform claim a route behave
	 * 
	 * @param player
	 * @param path
	 * @param cards
	 * @return
	 */
	public static boolean claimARoute(Player player, Path path, List<TrainCard> cardsToSpend) {
		player.setPiece(player.getPiece() - path.getCost()); //subtract pieces from inventory
		
		for (TrainCard tc : cardsToSpend)
			player.getTrainCards().remove(tc); //remove each train card from players hand
		
		player.getOwnPath().add(path); //add route to players list of owned routes
		player.setScore(player.getScore() + POINT[path.getCost()]); //update players score
		path.setOwningPlayer(player); //mark route as owned by player in global path list
		Deck.spendCards(cardsToSpend); //add spent cards to discard deck
		TicketToRideGui.appendLog("claimed the route:\n\t" + path + "\n\tWith the cards:" + cardsToSpend
				+ "\n\tFor a total of " + POINT[path.getCost()] + " points.");
		Game.gui.repaintGraph();
		return true;
	}

	/**
	 * draw a face down train card from the deck
	 * 
	 * @param player
	 * @return
	 */
	public static TrainCard drawTrainCard(Player player) {
		Deck.performIfDeckEmpty();
		TrainCard card = Deck.trainCardsDeck.remove(0);
		player.getTrainCards().add(card);
		//TicketToRideGui.appendLog("took a face down train card."); //human player mode
		TicketToRideGui.appendLog("took a face down train card of color: " + card.getColor()); //TODO: add flag for observe mode
		return card;
	}

	/**
	 * draw a face up train card from the index card
	 * 
	 * @param player
	 * @param index
	 * @return card
	 */
	public static TrainCard drawTrainCard(Player player, int index) {
		Deck.performIfDeckEmpty();
		List<TrainCard> faceUpCards = Deck.trainFaceUpCards;
		List<TrainCard> faceDownCards = Deck.trainCardsDeck;
		TrainCard card = faceUpCards.get(index); // get face up card
		
		if(!faceDownCards.isEmpty())
			faceUpCards.set(index, faceDownCards.remove(0)); //replace taken face up card
		else{
			faceUpCards.remove(index);
		}
		Game.gui.refreshIfTripleRainbow();

		player.getTrainCards().add(card); //add taken card to players hand
		TicketToRideGui.appendLog("took a face up train card of color: " + card.getColor());
		Game.gui.repaintFaceUpTrainCards();
		return card;
	}
	
	/**
	 * perform draw destination ticket behave
	 * 
	 * @param player
	 * @return
	 */
	public static List<DestinationCard> drawDesTickets() {
		List<DestinationCard> cards = new ArrayList<DestinationCard>();
		for (int i = 0; i < 3 && i < Deck.desCardDeck.size(); i++)
			cards.add(Deck.desCardDeck.remove(0));
		return cards;
	}

	/**
	 * 
	 * @param cards
	 */
	public static void returnDesCardToDeck(List<DestinationCard> cards) {
		Deck.desCardDeck.addAll(cards);
	}
	

	/**
	 * Return unwant destination card to deck
	 * @param cards
	 */
	public static void returnDesCardToDeck(DestinationCard cards) { 
		Deck.desCardDeck.add(cards);
	}
	
	/**
	 * Convert destination card list to string form for display only
	 */
	public static List<String> convertDesCardListToString(List<DestinationCard> desCardList) {
		List<String> desCardStringList = new ArrayList<String>();
		for (int i = 0; i < desCardList.size(); i++)
			desCardStringList.add(desCardList.get(i).toString());
		
		return desCardStringList;
	}
	
}
