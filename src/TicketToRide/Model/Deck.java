package TicketToRide.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import TicketToRide.Model.Constants.trainCard;

public class Deck {
	public static List<DestinationCard> desCardDeck;
	public static List<TrainCard> trainCardsDeck;
	public static List<TrainCard> trainFaceUpCards;
	public static HashMap<TrainCard, Integer> countColor;
	
	static{
		trainCardsDeck=new ArrayList<TrainCard>();
		trainFaceUpCards=new ArrayList<TrainCard>();
		List<trainCard> array=Arrays.asList(trainCard.values()).subList(0, trainCard.values().length-1);
		for(trainCard tc:array){
			for(int i=0; i<12; i++){
				TrainCard c=new TrainCard(tc);
				trainCardsDeck.add(c);
			}
		}
		trainCardsDeck.add(new TrainCard(trainCard.RAINBOW));
		trainCardsDeck.add(new TrainCard(trainCard.RAINBOW));
		shuffle(trainCardsDeck);
	}
	
	public static void startHand(List<Player> players){
		for(Player player:players){
			for(int i=0; i<4; i++){
				player.getTrainCards().add(trainCardsDeck.remove(0));
			}
		}
		
		for(int i=0; i<5; i++){
			trainFaceUpCards.add(trainCardsDeck.remove(0));
		}
	}
	
	public static <T> void shuffle(List<T> card){
		Random r=new Random();
		int s=card.size();
		for(int i=0; i<s; i++){
			Collections.swap(card, r.nextInt(s), r.nextInt(s));
		}
	}
}
