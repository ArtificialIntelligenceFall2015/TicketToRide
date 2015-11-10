/**
 * TrainCard.java
 */
package TicketToRide.Model;

import TicketToRide.Control.Frontier;
import TicketToRide.Model.Constants.trainCard;

/**
 * @author Jun He
 *
 */
public class TrainCard implements Comparable<TrainCard> {
	private trainCard color;

	/**
	 * Constructor
	 * 
	 * @param color
	 */
	public TrainCard(trainCard color) {
		this.color = color;
	}

	/**
	 * override compareTo class
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrainCard [color=" + color + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object r) {
		boolean retVal = false;
		
		if (r.getClass() == TrainCard.class) {
			retVal = this.color.equals(((TrainCard) r).color);
		}
		else if (r.getClass() == trainCard.class) {
			retVal = this.color.equals((trainCard) r);
		}

//		
//		if (!(r.getClass() == TrainCard.class) || !(r.getClass() == trainCard.class))
//			retVal = false;
//		else {
//			TrainCard ro = (TrainCard) r;
//			retVal = this.color.equals(ro.color);
//		}
		return retVal;
	}
}
