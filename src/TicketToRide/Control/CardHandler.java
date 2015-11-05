package TicketToRide.Control;

import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.TrainCard;

public class CardHandler {
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
}
