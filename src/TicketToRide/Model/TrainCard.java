package TicketToRide.Model;

import TicketToRide.Model.Constants.trainCard;

public class TrainCard implements Comparable<TrainCard>{
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


	public int compareTo(TrainCard arg0) {
		return this.color.name().compareTo(arg0.color.name());
	}
}
