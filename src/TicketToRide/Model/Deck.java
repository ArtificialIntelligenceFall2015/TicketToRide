/**
 * Deck.java
 * This class view a Deck Object, and perform Deck object behave
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
	public static List<TrainCard> trainCardDiscardDeck;

	/**
	 * init global variable for Deck of face down train card Deck of face up
	 * train card shuffle cards
	 */
	static {
		trainCardsDeck = new ArrayList<TrainCard>();
		trainFaceUpCards = new ArrayList<TrainCard>();
		desCardDeck = ParseCSVData.parseDestinationCards();
		trainCardDiscardDeck = new ArrayList<TrainCard>();
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
	 * draw initial cards for players' hand of train cards
	 * @param players
	 */
	public static void drawStartingHand(List<Player> players) {
		for (Player player : players) {
			for (int i = 0; i < 4; i++) {
				player.getTrainCards().add(trainCardsDeck.remove(0));
			}
		}
	}
	
	/**
	 * draw initial cards for 5 face up train cards
	 */
	public static boolean drawFreshFaceUpTrainCards() {
		boolean deckIsEmpty = false;
		
		for (int i = 0; i < 5; i++) {
			if (trainCardsDeck.size() > 0)
				trainFaceUpCards.add(trainCardsDeck.remove(0));
			else {
				trainFaceUpCards.add(null);
				deckIsEmpty = true;
			}
		}
		return deckIsEmpty;
	}

	/**
	 * discard cards
	 * 
	 * @param card
	 */
	public static void discardAllFaceUpTrainCards(List<TrainCard> discardedTC) {
		trainCardDiscardDeck.addAll(discardedTC);
		discardedTC.clear();
	}
	
	/**
	 * shuffle cards
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
	 * count the amount of named color in the traincard list by using color enum
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
	 * count the amount of named color in the traincard list by using color
	 * string
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
