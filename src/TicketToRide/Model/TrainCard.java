package TicketToRide.Model;

import TicketToRide.Model.Constants.trainCard;

public class TrainCard {
	private trainCard color;

	public TrainCard(trainCard color) {
		super();
		this.color = color;
	}

	public trainCard getColor() {
		return color;
	}

	public void setColor(trainCard color) {
		this.color = color;
	}
}
