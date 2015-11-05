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
	public static void calcDisCardPoint(Player player) {
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
	 * perform draw train card behave
	 * 
	 * @param player
	 * @param index
	 * @return
	 */
	public static TrainCard drawTrainCard(Player player, int index) {
		List<TrainCard> faceUpCard = Deck.trainFaceUpCards;
		List<TrainCard> faceDownCard = Deck.trainCardsDeck;
		TrainCard card = faceUpCard.remove(index);
		if (faceDownCard.size() > 0)
			faceUpCard.add(faceDownCard.remove(0));
		player.getTrainCards().add(card);
		return card;
	}

	/**
	 * perform draw train card behave
	 * 
	 * @param player
	 * @return
	 */
	public static TrainCard drawTrainCard(Player player) {
		TrainCard card = Deck.trainCardsDeck.remove(0);
		player.getTrainCards().add(card);
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
	public static void returnDesCardToDeck(DestinationCard cards) {
		Deck.desCardDeck.add(cards);
	}
}
