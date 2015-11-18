/**
 * Game.java
 * This class behave as view of game.
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Constants.decision;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.World;

/**
 * @author Jun He
 *
 */
public class Game {
	public static List<Player> players;
	public static Player currentPlayer;

	/**
	 * 
	 * @param p
	 */
	public Game(Player... p) {
		new World();
		players = new ArrayList<Player>();
		Collections.addAll(players, p);
		currentPlayer=players.get(0);
	}
	
	/**
	 * 
	 * @param p
	 */
	public Game(List<Player> p) {
		new World();
		players = p;
		currentPlayer=players.get(0);
	}
	
	/**
	 * 
	 */
	public static void run(){
		while(!gameEnd()){
			if(currentPlayer instanceof PlayerAI){
				PlayerAI ai=(PlayerAI)currentPlayer;
				decision d=PlayerHandlerAI.decisionMaking(ai);
				PlayerHandlerAI.performAction(ai, d);
			}else{
				//GUI side
			}
			nextPlayer();
		}
	}

	/**
	 * get winners list
	 * 
	 * @return winner
	 */
	public static List<Player> getWinners() {
		List<Player> winner = new ArrayList<Player>();
		int maxScore = 0;

		for (Player player : players)
			Math.max(maxScore, player.getScore());

		for (Player player : players)
			if (maxScore == player.getScore())
				winner.add(player);

		return winner;
	}

	/**
	 * switch currentPlayer to next player
	 */
	public static void nextPlayer() {
		int turnIndex = players.indexOf(currentPlayer);
		turnIndex++;
		currentPlayer=players.get(turnIndex % players.size());
	}

	/**
	 * determine game end
	 * 
	 * @return
	 */
	public static boolean gameEnd() {
		return currentPlayer.getPiece() < 3;
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public static int getRank(Player player) {
		int n = 1;
		for (Player p : players) {
			if (p.getScore() > player.getScore()) {
				n++;
			}
		}
		return n;
	}
}
