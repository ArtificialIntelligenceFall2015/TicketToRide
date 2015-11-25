/**
 * Game.java
 * This class behave as view of game.
 */
package TicketToRide.Control;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import TicketToRide.Model.Constants.decision;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.World;
import TicketToRide.View.TicketToRideGui;

/**
 * @author Jun He
 *
 */
public class Game {
	public static List<Player> players;
	public static Player currentPlayer;
	public static boolean firstTurn = true;
	public static TicketToRideGui gui;

	/**
	 * 
	 * @param p
	 * @param gui
	 */
	public Game(List<Player> p, TicketToRideGui gui) {
		new World();
		players = p;
		Game.gui = gui;
		currentPlayer = players.get(0);
	}

	/**
	 * get winners list
	 * 
	 * @return winner
	 */
	public static Player getWinner() {
		Collections.sort(players, new winnerComparator());
		return players.get(0);
	}

	/**
	 * switch currentPlayer to next player
	 */
	public static void nextPlayer() {
		if (gameEnd()) {
			performGameEndedCalculation();
		} else {
			int turnIndex = players.indexOf(currentPlayer);
			turnIndex++;
			currentPlayer = players.get(turnIndex % players.size());

			if (currentPlayer instanceof PlayerAI) {
				PlayerAI ai = (PlayerAI) currentPlayer;
				decision d = PlayerHandlerAI.decisionMaking(ai);
				PlayerHandlerAI.performAction(ai, d);
				nextPlayer();
			}
		}
	}

	/**
	 * determine game end
	 * 
	 * @return
	 */
	public static boolean gameEnd() {
		if (currentPlayer.isLastTurn()) {
			return true;
		}

		if (currentPlayer.getPiece() < 3) {
			currentPlayer.setLastTurn(true);
		}

		return false;
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

	/**
	 * 
	 */
	private static void performGameEndedCalculation() {
		gui.disableTurnChoiceButtons();
		for (Player player : players) {
			PathHandler.determinePathClose(player);
			PlayerHandler.calcDesCardPoint(player);
		}

		List<Player> playersHaveLongestPath = PathHandler.getLongestPathPlayers();

		for (Player player : playersHaveLongestPath) {
			player.setScore(player.getScore() + 10);
		}

		Player winner = getWinner();
		String message = "Game Over!\n";
		if (winner instanceof PlayerAI) {
			message = "Sorry, you lost!";
		} else {
			message = "Congratulations! You won!";
		}
		gui.popupMessage(message);
		System.exit(0);
	}

	/**
	 * 
	 * @author jhe
	 *
	 */
	private static class winnerComparator implements Comparator<Player> {

		@Override
		public int compare(Player p1, Player p2) {
			if (p1.getScore() == p2.getScore()) {
				int numCompleteDesCard1 = p1.getNumTicketComplete();
				int numCompleteDesCard2 = p2.getNumTicketComplete();

				if (numCompleteDesCard1 == numCompleteDesCard2) {
					return PathHandler.getLongestPath(p2) - PathHandler.getLongestPath(p1);

				} else {
					return numCompleteDesCard2 - numCompleteDesCard1;
				}
			} else {
				return p2.getScore() - p1.getScore();
			}
		}
	}
}
