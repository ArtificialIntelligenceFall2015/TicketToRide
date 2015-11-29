package TicketToRide.Control;

import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.TrainCard;

public class CardHandler {
	/**
	 * Create an hashmap analyze number of cards in each color on the list
	 * @param cards
	 * @return
	 */
	public static HashMap<trainCard, Integer> trainCardCollection(List<TrainCard> cards) {
		HashMap<trainCard, Integer> collection = new HashMap<trainCard, Integer>();
		for (TrainCard card : cards) {
			int value = 1;
			if (collection.containsKey(card.getColor())) {
				value += collection.get(card.getColor());
			}
			collection.put(card.getColor(), value);
		}
		return collection;
	}
	
	/**
	 * get specific amount of color
	 * if key no exist, return 0
	 * @param player
	 * @param card
	 * @return
	 */
	public static int getCollectionAmount(PlayerAI player, trainCard card) {
		HashMap<trainCard, Integer> collection= player.getHandCollection();
		return getCollectionAmount(collection, card);
	}
	
	/**
	 * get specific amount of color
	 * if key no exist, return 0
	 * @param collection
	 * @param card
	 * @return
	 */
	public static int getCollectionAmount(HashMap<trainCard, Integer> collection, trainCard card){
		return collection.containsKey(card)?collection.get(card):0;
	}
}
