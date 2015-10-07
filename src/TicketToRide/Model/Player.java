package TicketToRide.Model;

import java.util.HashMap;
import java.util.List;
import TicketToRide.Model.Constants.trainColor;

public class Player {
	private int score=0;
	private List<TrainCard> trainCards;
	private List<DestinationCard> desCards;
	private List<Path> ownPath;
	
	private trainColor color;
	
	private int piece=45;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public List<DestinationCard> getDesCards() {
		return desCards;
	}

	public void setDesCards(List<DestinationCard> desCards) {
		this.desCards = desCards;
	}

	public List<Path> getOwnPath() {
		return ownPath;
	}

	public void setOwnPath(List<Path> ownPath) {
		this.ownPath = ownPath;
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

	public List<TrainCard> getTrainCards() {
		return trainCards;
	}

	public void setTrainCards(List<TrainCard> trainCards) {
		this.trainCards = trainCards;
	}
}
