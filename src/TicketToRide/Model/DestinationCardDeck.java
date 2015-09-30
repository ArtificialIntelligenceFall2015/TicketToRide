package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

public class DestinationCardDeck extends Deck{

	protected List<DestinationCard> cards;
	protected List<DestinationCard> faceUpCards;
	
	public DestinationCardDeck(){
		cards=new ArrayList<DestinationCard>();
	}
	
	@Override
	public void getNext() {
		
		
	}

	@Override
	public List<DestinationCard> getFaceUpCards() {
		return faceUpCards;
	}

}
