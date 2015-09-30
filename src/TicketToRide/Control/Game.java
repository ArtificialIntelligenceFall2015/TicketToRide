package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.DestinationCardDeck;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCardDeck;

public class Game {
	private List<Path> world;
	private List<Player> players;
	private Player turn;
	private TrainCardDeck trainCardDeck;
	private DestinationCardDeck desCardDeck;
	
	public Game(Player... p){
		world=new ArrayList<Path>();
		players=new ArrayList<Player>();
		Collections.addAll(players, p);
		turn=players.get(0);
		trainCardDeck=new TrainCardDeck();
		desCardDeck=new DestinationCardDeck();
	}
	
	public List<Player> getWinner(){
		List<Player> winner=new ArrayList<Player>();
		int maxScore=players.get(0).getScore();
		
		for(Player player:players)
			Math.max(maxScore, player.getScore());
		
		for(Player player:players)
			if(maxScore==player.getScore())
				winner.add(player);
		
		return winner;
	}
	
}
