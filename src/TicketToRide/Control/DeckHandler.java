package TicketToRide.Control;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import TicketToRide.Model.Player;

public class DeckHandler {
	public static <T> void shuffle(List<T> card){
		Random r=new Random();
		int s=card.size();
		for(int i=0; i<s; i++){
			Collections.swap(card, r.nextInt(s), r.nextInt(s));
		}
	}
}
