/**
 * Player.java
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

import TicketToRide.Model.Constants.playerColor;

/**
 * @author Jun He
 *
 */
public class Player {
	private int score = 0;
	private List<TrainCard> trainCards;
	private List<DestinationCard> desCards;
	private List<Path> ownPath;
	private playerColor color;
	private int piece = 45;

	public Player(playerColor color){
		trainCards=new ArrayList<TrainCard>();
		desCards=new ArrayList<DestinationCard>();
		ownPath=new ArrayList<Path>();
		this.color=color;
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
	 * @return the desCards
	 */
	public List<DestinationCard> getDesCards() {
		return desCards;
	}

	/**
	 * @return the ownPath
	 */
	public List<Path> getOwnPath() {
		return ownPath;
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

}
