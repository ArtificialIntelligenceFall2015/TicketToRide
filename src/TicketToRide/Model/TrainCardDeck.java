package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;

import TicketToRide.Model.Constants.trainCard;

public class TrainCardDeck extends Deck{
	private HashMap<trainCard, Integer> countColor;

	public TrainCardDeck(){
		cards=new ArrayList<TrainCard>();
	}
	
	@Override
	public void getNext() {
		
	}
}
