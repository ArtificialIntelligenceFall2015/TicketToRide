/**
 * Player.java
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

import TicketToRide.Model.Constants.trainColor;

/**
 * @author Jun He
 *
 */
public class Player {
	private int score = 0;
	private List<TrainCard> trainCards = new ArrayList<TrainCard>();
	private List<DestinationCard> desCards = new ArrayList<DestinationCard>();
	private List<Path> ownPath = new ArrayList<Path>();
	private trainColor color;
	private int piece = 45;

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
	public trainColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(trainColor color) {
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

}
