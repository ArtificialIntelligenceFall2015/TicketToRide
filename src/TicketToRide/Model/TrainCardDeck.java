package TicketToRide.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants.trainCard;

public class TrainCardDeck{
	public static List<TrainCard> cards;
	public static List<TrainCard> faceUpCards;
	
	static{
		cards=new ArrayList<TrainCard>();
		faceUpCards=new ArrayList<TrainCard>();
		List<trainCard> array=Arrays.asList(trainCard.values()).subList(0, trainCard.values().length-1);
		for(trainCard tc:array){
			for(int i=0; i<12; i++){
				TrainCard c=new TrainCard(tc);
				cards.add(c);
			}
		}
		cards.add(new TrainCard(trainCard.RAINBOW));
		cards.add(new TrainCard(trainCard.RAINBOW));
	}
	
	private HashMap<trainCard, Integer> countColor;
}
