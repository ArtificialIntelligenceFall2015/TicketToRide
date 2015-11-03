package TicketToRide.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import TicketToRide.Control.PlayerHandler;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Constants.trainColor;
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
	Player playerAI;
	
	@Before
	public void init(){
		map=World.map;
		players=new ArrayList<Player>();
		player=new Player(trainColor.BLUE);
		playerAI=new PlayerAI(trainColor.RED);
		players.add(player);
		players.add(playerAI);
		Deck.startHand(players);
	}
	
	@Test
	public void testClaimARute(){
		System.out.println("testClaimARute:");
		List<TrainCard> cardsToSpend=new ArrayList<TrainCard>();
		cardsToSpend.add(player.getTrainCards().get(0));
		cardsToSpend.add(player.getTrainCards().get(1));
		cardsToSpend.add(player.getTrainCards().get(2));
		Path route=map.get(0);
		System.out.println(route.getCost());
		System.out.println(PlayerHandler.claimARoute(player, route, cardsToSpend));
		System.out.println(player.getTrainCards().size());
	}
	
	@Test
	public void testClaimARuteWithColor(){
		System.out.println("testClaimARuteWithColor:");
		List<TrainCard> cardsToSpend=new ArrayList<TrainCard>();
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		cardsToSpend.add(new TrainCard(trainCard.GREEN));
		player.getTrainCards().addAll(cardsToSpend);
		Path route=map.get(9);
		System.out.println(route.getCost());
		System.out.println(route.getColor());
		System.out.println(PlayerHandler.claimARoute(player, route, cardsToSpend));
		System.out.println(player.getTrainCards().size());
	}
}
