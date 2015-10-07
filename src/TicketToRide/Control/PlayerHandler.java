package TicketToRide.Control;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.DestinationCardDeck;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCard;
import TicketToRide.Model.TrainCardDeck;

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

	public static boolean claimARoute(List<Path> world, Player player, Path path, List<TrainCard> cards) {
		int pathCost = path.getCost();
		int playerPiece = player.getPiece();
		pathColor pathColor = path.getColor();
		
		List<TrainCard> playerTrainCards = player.getTrainCards();
		int numColor=0;
		int numLocomotives=0;
		for(TrainCard card:cards){
			if(card.getColor().name().equals(pathColor.name()))
				numColor++;
			else if(card.getColor()==Constants.trainCard.RAINBOW)
				numLocomotives++;
			else
				return false;
		}
		if ((pathColor==Constants.pathColor.GRAY&&path.getCost()==cards.size())
				||(pathColor!=Constants.pathColor.GRAY&&path.getCost()==numColor+numLocomotives) 
				&& world.contains(path) 
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

	public static boolean drawTrainCard(Player player, TrainCard card) {
		List<TrainCard> faceUpCard = TrainCardDeck.faceUpCards;
		List<TrainCard> faceDownCard = TrainCardDeck.cards;
		if (faceUpCard.contains(card)) {
			player.getTrainCards().add(faceUpCard.remove(faceUpCard.indexOf(card)));
			faceUpCard.add(faceDownCard.remove(0));
			return true;
		}
		return false;
	}
	
	public static boolean drawTrainCard(Player player) {
		player.getTrainCards().add(TrainCardDeck.cards.remove(0));
		return true;
	}

	public static boolean drawDesTickets(Player player, DestinationCard...cards) {
		List<DestinationCard> removeCards=DestinationCardDeck.cards.subList(0, 3);
		for(DestinationCard card: cards)
			if(!removeCards.contains(card))
				return false;
		Collections.addAll(player.getDesCards(), cards);
		for(int i=0; i<3&&i<DestinationCardDeck.cards.size();i++)
			DestinationCardDeck.cards.remove(0);
		return true;
	}
}
