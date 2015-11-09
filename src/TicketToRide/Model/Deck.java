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
	public static HashMap<TrainCard, Integer> removeCount;

	/**
	 * init global variable for Deck of face down train card Deck of face up
	 * train card shuffle cards
	 */
	static {
		trainCardsDeck = new ArrayList<TrainCard>();
		trainFaceUpCards = new ArrayList<TrainCard>();
		desCardDeck = ParseCSVData.parseDestinationCards();
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
	 * perform start hand behave in the game
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
	
	public static void spendCards(List<TrainCard> cardsToSpend){
		for(TrainCard card: cardsToSpend){
			int n=1;
			if(removeCount.containsKey(card)){
				n+=removeCount.get(card);
			}
			removeCount.put(card, n);
		}
	}
}
