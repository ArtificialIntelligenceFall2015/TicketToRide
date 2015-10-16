package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Path;
import TicketToRide.Model.Player;

public class Game {
	private List<Player> players;
	private Player turn;
	
	public Game(Player... p){
		players=new ArrayList<Player>();
		Collections.addAll(players, p);
	}
	
	public List<Player> getWinners(){
		List<Player> winner=new ArrayList<Player>();
		int maxScore=players.get(0).getScore();
		
		for(Player player:players)
			Math.max(maxScore, player.getScore());
		
		for(Player player:players)
			if(maxScore==player.getScore())
				winner.add(player);
		
		return winner;
	}
	
	public void nextPlayer(){
		int turnIndex=players.indexOf(turn);
		if(turnIndex==players.size()-1)
			turn=players.get(0);
		else
			turn=players.get(turnIndex++);
	}
	
	public boolean gameEnd(){
		return turn.getPiece()<3;
	}
	
}
