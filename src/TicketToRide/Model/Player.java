package TicketToRide.Model;

import java.util.List;
import TicketToRide.Model.Constants.trainColor;

public class Player {
	private int score;
	private List<TrainCard> trainCards;
	private List<DestinationCard> desCards;
	
	private trainColor color;
	
	private int piece=45;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public trainColor getColor() {
		return color;
	}

	public void setColor(trainColor color) {
		this.color = color;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}
}
