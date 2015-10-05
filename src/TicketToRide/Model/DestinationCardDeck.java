package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

public class DestinationCardDeck{

	private List<DestinationCard> cards;
	
	public DestinationCardDeck(){
		cards=new ArrayList<DestinationCard>();
	}

	public List<DestinationCard> getCards() {
		return cards;
	}
}
