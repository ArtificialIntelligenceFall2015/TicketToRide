/**
 * Game.java
 * This class behave as view of game.
 */
package TicketToRide.Control;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

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
	public static boolean gameover=false;

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
			String msg = "End of " + Game.currentPlayer.getColor() + "'s turn.\nNext Player's Turn...";
			JOptionPane.showMessageDialog(null, msg, "Next Player's Turn", JOptionPane.INFORMATION_MESSAGE);
			int turnIndex = players.indexOf(currentPlayer);
			turnIndex++;
			turnIndex=turnIndex % players.size();
			currentPlayer = players.get(turnIndex);
			if(firstTurn&&turnIndex==0)
				firstTurn=false;
			
			gui.retallyPlayerTrainCardHand();
			gui.displayCurrentPlayerDestinationCards();
			gui.updateCurrentPlayerAvatar();
			gui.updateScoreboard();
			TicketToRideGui.appendLog("'s turn.");

			if (currentPlayer instanceof PlayerAI) {
				PlayerAI ai = (PlayerAI) currentPlayer;
				PlayerHandlerAI.populateAIFields(ai);
				if(Game.firstTurn){
					PlayerHandlerAI.drawDesTicketsAI(ai, 2);
				}else{
					decision d = PlayerHandlerAI.decisionMaking(ai);
					PlayerHandlerAI.performAction(ai, d);
				}
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
			TicketToRideGui.appendLog("has kicked off the final round! One more turn for each player!");
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
		gui.popupMessage("Game Over!");
		TicketToRideGui.appendLogInfo("Game Over!");
		gui.popupMessage("Compute Destination Card Score.");
		TicketToRideGui.appendLogInfo("Destination Card Results:");
		for (Player player : players) {
			PathHandler.determinePathClose(player);
			PlayerHandler.calcDesCardPoint(player);
		}
		gui.updateScoreboard();

		gui.popupMessage("Compute Longest Path.");
		List<Player> playersHaveLongestPath = PathHandler.getLongestPathPlayers();

		String name="";
		for(Player player:playersHaveLongestPath){
			name+=player.getColor()+" ";
		}
		gui.popupMessage("Player(s) who have longest path: "+name);
		TicketToRideGui.appendLogInfo("Player(s) with the longest path: " + name);
		for (Player player : playersHaveLongestPath) {
			player.setScore(player.getScore() + 10);
		}
		
		gui.updateScoreboard();

		Player winner = getWinner();
		String message = "";
		if (winner instanceof PlayerAI) {
			message = "Sorry, you lost!";
		} else {
			message = "Congratulations! You won!";
		}
		gui.popupMessage(message);
		TicketToRideGui.appendLogInfo(message);
		TicketToRideGui.appendLogInfo("Writing all actions in game to log.txt...");
		TicketToRideGui.writeLogToFile();
		gameover=true;
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
