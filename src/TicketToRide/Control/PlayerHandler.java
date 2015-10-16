package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCard;

public class PlayerHandler {

	private static final int[] POINT = { 0, 1, 2, 4, 7, 10, 15 };

	public static void calcDisCardPoint(Player player) {
		int score = 0;
		for (DestinationCard card : player.getDesCards()) {
			if (card.isCompleted()) {
				score += card.getPoint();
			} else {
				score -= card.getPoint();
			}
		}
		player.setScore(player.getScore() + score);
	}
	
	public static void organizeCard(Player player){
		Collections.sort(player.getTrainCards());
	}

	public static boolean claimARoute(Player player, Path path, List<TrainCard> cards) {
		int pathCost = path.getCost();
		int playerPiece = player.getPiece();
		pathColor pathColor = path.getColor();
		
		List<TrainCard> playerTrainCards = player.getTrainCards();
		int numColor=Deck.count(cards, pathColor.name());
		int numLocomotives=Deck.count(cards, Constants.trainCard.RAINBOW);
		
		if ((pathColor==Constants.pathColor.GRAY&&path.getCost()==cards.size())
				||(pathColor!=Constants.pathColor.GRAY&&path.getCost()==numColor+numLocomotives) 
				&& playerPiece >= pathCost) {
			player.setPiece(playerPiece - pathCost);
			for(TrainCard card:cards)
				playerTrainCards.remove(card);
			path.setPlayer(player);
			player.getOwnPath().add(path);
			player.setScore(player.getScore() + POINT[pathCost]);
			return true;
		}
		return false;
	}

	public static TrainCard drawTrainCard(Player player, int index) {
		List<TrainCard> faceUpCard = Deck.trainFaceUpCards;
		List<TrainCard> faceDownCard = Deck.trainCardsDeck;
		TrainCard card=faceUpCard.remove(index);
		if(faceDownCard.size()>0)
			faceUpCard.add(faceDownCard.remove(0));
		return card;
	}
	
	public static TrainCard drawTrainCard(Player player) {
		return Deck.trainCardsDeck.remove(0);
	}

	public static List<DestinationCard> drawDesTickets(Player player) {
		List<DestinationCard> cards=new ArrayList<DestinationCard>();
		for(int i=0; i<3&&i<Deck.desCardDeck.size();i++)
			cards.add(Deck.desCardDeck.remove(0));
		return cards;
	}
}
