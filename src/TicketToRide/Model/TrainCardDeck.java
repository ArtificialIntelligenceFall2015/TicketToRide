package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants.trainCard;

public class TrainCardDeck extends Deck{
	private List<TrainCard> cards;
	private List<TrainCard> faceUpCards;
	
	private HashMap<trainCard, Integer> countColor;

	public TrainCardDeck(){
		cards=new ArrayList<TrainCard>();
	}
	
	@Override
	public void getNext() {
		
	}

	@Override
	public List<TrainCard> getFaceUpCards() {
		return faceUpCards;
	}
}
