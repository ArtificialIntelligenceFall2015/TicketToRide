/**
 * Game.java
 * This class behave as view of game.
 */
package TicketToRide.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Player;

/**
 * @author Jun He
 *
 */
public class Game {
	public static List<Player> players;
	public static Player turn;

	/**
	 * 
	 * @param p
	 */
	public Game(Player... p) {
		players = new ArrayList<Player>();
		Collections.addAll(players, p);
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
	 * switch turn to next player
	 */
	public static void nextPlayer() {
		int turnIndex = players.indexOf(turn);
		if (turnIndex == players.size() - 1)
			turn = players.get(0);
		else
			turn = players.get(turnIndex++);
	}

	/**
	 * determine game end
	 * 
	 * @return
	 */
	public static boolean gameEnd() {
		return turn.getPiece() < 3;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public static int getRank(Player player){
		int n=1;
		for(Player p:players){
			if(p.getScore()>player.getScore()){
				n++;
			}
		}
		return n;
	}
}
