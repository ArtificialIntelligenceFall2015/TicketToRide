package TicketToRide.Control;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

	public static boolean claimARoute(List<Path> world, Player player, Path path) {
		int pathCost = path.getCost();
		int playerPiece = player.getPiece();
		TrainCard pathColor = path.getColor();
		HashMap<TrainCard, Integer> playerTrainCards = player.getTrainCards();
		int amountOfCard = playerTrainCards.get(pathColor);
		if (amountOfCard >= pathCost && world.contains(path) && playerPiece >= pathCost) {
			player.setPiece(playerPiece - pathCost);
			playerTrainCards.put(pathColor, amountOfCard - pathCost);
			player.getOwnPath().add(world.remove(world.indexOf(path)));
			player.setScore(player.getScore() + POINT[pathCost]);
			return true;
		}
		return false;
	}

	public static boolean drawTrainCard(TrainCardDeck trainCardDeck, Player player, TrainCard card) {
		List<TrainCard> faceUpCard = trainCardDeck.getFaceUpCards();
		List<TrainCard> faceDownCard = trainCardDeck.getCards();
		if (faceUpCard.contains(card)) {
			faceUpCard.remove(faceUpCard.indexOf(card));
			faceUpCard.add(faceDownCard.remove(0));
			player.getTrainCards().put(card, player.getTrainCards().get(card) + 1);
			return true;
		}
		return false;
	}
	
	public static boolean drawTrainCard(TrainCardDeck trainCardDeck, Player player) {
		List<TrainCard> faceDownCard = trainCardDeck.getCards();
		TrainCard card=faceDownCard.remove(0);
		player.getTrainCards().put(card, player.getTrainCards().get(card) + 1);
		return true;
	}

	public static boolean drawDesTickets(DestinationCardDeck desCardDeck, Player player, DestinationCard...cards) {
		List<DestinationCard> removeCards=desCardDeck.getCards().subList(0, 3);
		for(DestinationCard card: cards)
			if(!removeCards.contains(card))
				return false;
		Collections.addAll(player.getDesCards(), cards);
		for(int i=0; i<3&&i<desCardDeck.getCards().size();i++)
			desCardDeck.getCards().remove(0);
		return true;
	}
}
