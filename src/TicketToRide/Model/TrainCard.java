/**
 * TrainCard.java
 */
package TicketToRide.Model;

import TicketToRide.Model.Constants.trainCard;

/**
 * @author Jun He
 *
 */
public class TrainCard implements Comparable<TrainCard> {
	private trainCard color;

	public TrainCard(trainCard color) {
		this.color = color;
	}

	public int compareTo(TrainCard arg0) {
		return this.color.name().compareTo(arg0.color.name());
	}

	/**
	 * @return the color
	 */
	public trainCard getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(trainCard color) {
		this.color = color;
	}
}
