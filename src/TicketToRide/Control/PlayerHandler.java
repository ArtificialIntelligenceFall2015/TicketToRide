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

/**
 * @author Jun He This class's methods work as player's behave
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

		if (cardsToSpend.size() != path.getCost() || player.getPiece() < path.getCost()) {
			return false;
		}

		for (TrainCard card : cardsToSpend) {
			if (!PathHandler.canClaimBy(path, card)) {
				return false;
			}
		}

		player.setPiece(player.getPiece() - path.getCost());
		player.getTrainCards().removeAll(cardsToSpend);
		player.getOwnPath().add(path);
		player.setScore(player.getScore() + POINT[path.getCost()]);
		path.setOwningPlayer(player);
		return true;
	}

	/**
	 * draw a face down train card from the deck
	 * 
	 * @param player
	 * @return
	 */
	public static TrainCard drawTrainCard(Player player) {
		//TODO: check if deck is empty
		TrainCard card = Deck.trainCardsDeck.remove(0);
		player.getTrainCards().add(card);
		return card;
	}

	/**
	 * draw a face up train card from the index card
	 * 
	 * @param player
	 * @param index
	 * @return
	 */
	public static TrainCard drawTrainCard(Player player, int index) {
		List<TrainCard> faceUpCards = Deck.trainFaceUpCards;
		List<TrainCard> faceDownCards = Deck.trainCardsDeck;
		TrainCard card = faceUpCards.get(index); //get face up card
		if (faceDownCards.size() > 0) {
			//replace face up card if deck isn't depleted
			faceUpCards.set(index, faceDownCards.remove(0)); 
		}
		else {
			//if empty, replace faceup card in list with null
			faceUpCards.set(index, null);
		}
		player.getTrainCards().add(card); //put face up card in player's hand
		//TODO: might not need to return this card, only return face down card - SF
		return card;
	}
	
	/**
	 * perform draw destination ticket behave
	 * 
	 * @param player
	 * @return
	 */
	public static List<DestinationCard> drawDesTickets(Player player) {
		List<DestinationCard> cards = new ArrayList<DestinationCard>();
		for (int i = 0; i < 3 && i < Deck.desCardDeck.size(); i++)
			cards.add(Deck.desCardDeck.remove(0));
		return cards;
	}

	/**
	 * 
	 * @param cards
	 */
	public static void returnDesCardToDeck(List<DestinationCard> cards) {//TODO: jun doesnt have this-SF
		Deck.desCardDeck.addAll(cards);
	}
	//jun's
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
