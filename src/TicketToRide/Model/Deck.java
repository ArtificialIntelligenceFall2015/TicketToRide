/**
 * Deck.java
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import TicketToRide.Model.Constants.trainCard;

/**
 * @author Jun He
 *
 */
public class Deck {
	public static List<DestinationCard> desCardDeck;
	public static List<TrainCard> trainCardsDeck;
	public static List<TrainCard> trainFaceUpCards;
	public static HashMap<TrainCard, Integer> countColor;

	/**
	 * 
	 */
	static {
		trainCardsDeck = new ArrayList<TrainCard>();
		trainFaceUpCards = new ArrayList<TrainCard>();
		for (trainCard tc : trainCard.values()) {
			int size = 12;
			if (tc == trainCard.RAINBOW)
				size = 14;
			for (int i = 0; i < size; i++) {
				TrainCard c = new TrainCard(tc);
				trainCardsDeck.add(c);
			}
		}
		shuffle(trainCardsDeck);
		shuffle(desCardDeck);
	}

	/**
	 * 
	 * @param players
	 */
	public static void startHand(List<Player> players) {
		for (Player player : players) {
			for (int i = 0; i < 4; i++) {
				player.getTrainCards().add(trainCardsDeck.remove(0));
			}
		}

		for (int i = 0; i < 5; i++) {
			trainFaceUpCards.add(trainCardsDeck.remove(0));
		}
	}

	/**
	 * 
	 * @param card
	 */
	public static <T> void shuffle(List<T> card) {
		Random r = new Random();
		int s = card.size();
		for (int i = 0; i < s; i++) {
			Collections.swap(card, r.nextInt(s), r.nextInt(s));
		}
	}

	/**
	 * 
	 * @param cards
	 * @param color
	 * @return
	 */
	public static int count(List<TrainCard> cards, trainCard color) {
		int n = 0;
		for (TrainCard card : cards) {
			if (card.getColor() == color)
				n++;
		}
		return n;
	}

	/**
	 * 
	 * @param cards
	 * @param name
	 * @return
	 */
	public static int count(List<TrainCard> cards, String name) {
		trainCard color = trainCard.valueOf(name);
		return count(cards, color);
	}
}
