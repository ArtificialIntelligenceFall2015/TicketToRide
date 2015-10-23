/**
 * TrainCardDeck.java
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Control.DeckHandler;
import TicketToRide.Model.Constants.trainCard;

/**
 * @author Jun He
 *
 */
public class TrainCardDeck {
	public static List<TrainCard> cards;
	public static List<TrainCard> faceUpCards;
	public static HashMap<trainCard, Integer> countColor;

	static {
		cards = new ArrayList<TrainCard>();
		faceUpCards = new ArrayList<TrainCard>();
		List<trainCard> array = Arrays.asList(trainCard.values()).subList(0,
				trainCard.values().length - 1);
		for (trainCard tc : array) {
			for (int i = 0; i < 12; i++) {
				TrainCard c = new TrainCard(tc);
				cards.add(c);
			}
		}
		cards.add(new TrainCard(trainCard.RAINBOW));
		cards.add(new TrainCard(trainCard.RAINBOW));
		DeckHandler.shuffle(cards);
	}

	public static void startHand(List<Player> players) {
		for (Player player : players) {
			for (int i = 0; i < 4; i++) {
				player.getTrainCards().add(cards.remove(0));
			}
		}

		for (int i = 0; i < 5; i++) {
			faceUpCards.add(cards.remove(0));
		}
	}
}
