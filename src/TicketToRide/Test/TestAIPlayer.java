package TicketToRide.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TicketToRide.Control.PlayerHandler;
import TicketToRide.Control.PlayerHandlerAI;
import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Deck;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.TrainCard;
import TicketToRide.Model.World;

public class TestAIPlayer {
	List<Path> map;
	List<Player> players;
	Player player;
	PlayerAI playerAI;

	@Before
	public void init() {
		map = World.map;
		players = new ArrayList<Player>();
		player = new Player(playerColor.BLUE);
		playerAI = new PlayerAI(playerColor.RED);
		players.add(player);
		players.add(playerAI);
		System.out.println("Size of Path:" + World.map.size());
		System.out.println("Size of City:" + World.cities.size());
		System.out.println("Size of Des Card Deck:" + Deck.desCardDeck.size());
		System.out.println("Size of Train Card Deck:" + Deck.trainCardsDeck.size());
		System.out.println("Size of face up Card Deck:" + Deck.trainFaceUpCards.size());
		Deck.drawStartingHand(players);
		Deck.drawFreshFaceUpTrainCards();
		System.out.println("After Start hand, Size of Train Card Deck:" + Deck.trainCardsDeck.size());
		System.out.println("After Start hand, Size of face up Card Deck:" + Deck.trainFaceUpCards.size());
		while (Deck.trainFaceUpCards.size() > 5) {
			Deck.trainCardsDeck.add(Deck.trainFaceUpCards.remove(0));
		}
		Path route = map.get(9);
		List<Path> paths = new ArrayList<Path>();
		paths.add(route);
		playerAI.getFavorPath().add(paths);
	}

	@Test
	public void testClaimARute() {
		List<TrainCard> cardsToSpend = new ArrayList<TrainCard>();
		cardsToSpend.add(player.getTrainCards().get(0));
		cardsToSpend.add(player.getTrainCards().get(1));
		cardsToSpend.add(player.getTrainCards().get(2));
		Path route = map.get(0);
		assertTrue(PlayerHandler.claimARoute(player, route, cardsToSpend));
		assertTrue(player.getTrainCards().size() == 1);
	}

	@Test
	public void testClaimARuteWithColor() {
		List<TrainCard> cardsToSpend = new ArrayList<TrainCard>();
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		player.getTrainCards().addAll(cardsToSpend);
		Path route = map.get(9);
		assertTrue(PlayerHandler.claimARoute(player, route, cardsToSpend));
		assertTrue(route.getOwningPlayer() == player);
		assertTrue(player.getTrainCards().size() == 4);
	}

	@Test
	public void testDrawTrainCard() {
		PlayerHandler.drawTrainCard(player, 1);
		assertTrue(player.getTrainCards().size() == 5);
		PlayerHandler.drawTrainCard(player);
		assertTrue(player.getTrainCards().size() == 6);
	}

	@Test
	public void testDecisionMaking() {
		playerAI.getDesCards().addAll(PlayerHandler.drawDesTickets());
		System.out.println(PlayerHandlerAI.decisionMaking(playerAI));
	}

	@Test
	public void testDrawTrainCardAI() {
		Path route = map.get(9);
		route.setOwningPlayer(null);
		PlayerAI p = playerAI;
		p.setWantClaimPath(route);
		int size = p.getTrainCards().size();
		PlayerHandlerAI.drawTrainCardAI(p);
		assertTrue(p.getTrainCards().size() - 2 == size);
		System.out.println(p.getTrainCards().get(size).getColor() + " " + p.getTrainCards().get(size + 1).getColor());
	}

	@Test
	public void calimARouteAI() {
		Path route = map.get(9);
		route.setOwningPlayer(null);
		PlayerAI p = playerAI;
		p.setWantClaimPath(route);
		List<TrainCard> cardsToSpend = new ArrayList<TrainCard>();
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		p.getTrainCards().addAll(cardsToSpend);
		PlayerHandlerAI.claimARouteAI(p);
		assertTrue(route.getOwningPlayer() == p);
	}
}
