/**
 * Player.java
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

import TicketToRide.Model.Constants.playerColor;

/**
 * @author Jun He
 * @author Sean Fast
 */
public class Player {
	private int score = 0;
	private List<TrainCard> trainCards = new ArrayList<TrainCard>();
	private List<DestinationCard> desCards = new ArrayList<DestinationCard>();
	private List<Path> ownPath = new ArrayList<Path>();
	private playerColor color;
	private int piece = 45;
	private boolean lastTurn;
	private int numTicketComplete = 0;

	public Player(playerColor color) {
		trainCards = new ArrayList<TrainCard>();
		desCards = new ArrayList<DestinationCard>();
		ownPath = new ArrayList<Path>();
		this.color = color;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the trainCards
	 */
	public List<TrainCard> getTrainCards() {
		return trainCards;
	}

	/**
	 * @param trainCards
	 *            the trainCards to set
	 */
	public void setTrainCards(List<TrainCard> trainCards) {
		this.trainCards = trainCards;
	}

	/**
	 * @return the desCards
	 */
	public List<DestinationCard> getDesCards() {
		return desCards;
	}

	/**
	 * @param desCards
	 *            the desCards to set
	 */
	public void setDesCards(List<DestinationCard> desCards) {
		this.desCards = desCards;
	}

	/**
	 * @return the ownPath
	 */
	public List<Path> getOwnPath() {
		return ownPath;
	}

	/**
	 * @param ownPath
	 *            the ownPath to set
	 */
	public void setOwnPath(List<Path> ownPath) {
		this.ownPath = ownPath;
	}

	/**
	 * @return the color
	 */
	public playerColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(playerColor color) {
		this.color = color;
	}

	/**
	 * @return the piece
	 */
	public int getPiece() {
		return piece;
	}

	/**
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(int piece) {
		this.piece = piece;
	}

	/**
	 * @return the lastTurn
	 */
	public boolean isLastTurn() {
		return lastTurn;
	}

	/**
	 * @param lastTurn
	 *            the lastTurn to set
	 */
	public void setLastTurn(boolean lastTurn) {
		this.lastTurn = lastTurn;
	}

	/**
	 * @return the numTicketComplete
	 */
	public int getNumTicketComplete() {
		return numTicketComplete;
	}

	/**
	 * @param numTicketComplete
	 *            the numTicketComplete to set
	 */
	public void setNumTicketComplete(int numTicketComplete) {
		this.numTicketComplete = numTicketComplete;
	}

	public String toString() {
		return this.color.name() + "\t" + this.score + "\t" + this.desCards
				+ "\t" + this.trainCards + "\t" + piece;
	}

	public String printTotals() {
		return this.color.name() + "\t" + this.score + "\t"
				+ this.desCards.size() + "\t\t" + this.trainCards.size()
				+ "\t\t" + piece;
	}

}
