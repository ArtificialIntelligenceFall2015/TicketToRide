package TicketToRide.Model;

import java.util.List;

public abstract class Deck {
	protected List cards;
	protected List faceUpCards;
	
	public abstract void getNext();
	
	public void shuffle(){
		
	}

	public List getFaceUpCards() {
		return faceUpCards;
	}
}
