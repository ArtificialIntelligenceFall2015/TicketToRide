package TicketToRide.Model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Deck {
	
	public abstract void getNext();
	
	public <T> void shuffle(List<T> card){
		Random r=new Random();
		int s=card.size();
		for(int i=0; i<s; i++){
			Collections.swap(card, r.nextInt(s), r.nextInt(s));
		}
	}

	public abstract List<?> getFaceUpCards();
}
