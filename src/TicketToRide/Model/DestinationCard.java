/**
 * DestinationCard.java
 * This class represents the Destination Card objects in the game.
 * It is read in from a csv file.
 * 
 */
package TicketToRide.Model;

/**
 * @author Jun He
 * @author Sean Fast
 *
 */
public class DestinationCard {
	private City city1;
	private City city2;
	private int point = 0;
	private boolean completed = false;

	/**
	 * Basic constructor
	 * 
	 * @param city1
	 * @param city2
	 * @param point
	 */
	public DestinationCard(City city1, City city2, int point) {
		this.city1 = city1;
		this.city2 = city2;
		this.point = point;
	}

	/**
	 * String arg constructor Takes a string arg read in from a line in the csv
	 * file and populates a destination card object with it
	 * 
	 * @param destinationCardArray
	 *            a single destination card object read in from csv file line
	 */
	public DestinationCard(String[] destinationCardArray) { // TODO This
															// constructor need
															// to be fixed
		int i = 0;

		this.city1 = World.stringToCities.get(destinationCardArray[i++]);
		this.city2 = World.stringToCities.get(destinationCardArray[i++]);
		this.point = Integer.parseInt(destinationCardArray[i++]);

	}

	/**
	 * @return the city1
	 */
	public City getCity1() {
		return city1;
	}

	/**
	 * @param city1
	 *            the city1 to set
	 */
	public void setCity1(City city1) {
		this.city1 = city1;
	}

	/**
	 * @return the city2
	 */
	public City getCity2() {
		return city2;
	}

	/**
	 * @param city2
	 *            the city2 to set
	 */
	public void setCity2(City city2) {
		this.city2 = city2;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed
	 *            the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
