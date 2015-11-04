/**
 * Path.java
 * This class represents the Path object in the game.
 * It is used to for the edges of the graph, and is 
 * read in from a csv file.
 */
package TicketToRide.Model;

import TicketToRide.Model.Constants.pathColor;

/**
 * @author Jun He
 * @author Sean Fast
 *
 */
public class Path {
	private City city1;
	private City city2;
	private pathColor color;
	private int cost = 0;
	private Player owningPlayer = null;

	/**
	 * Basic constructor
	 * 
	 * @param city1
	 * @param city2
	 * @param color
	 * @param cost
	 */
	public Path(City city1, City city2, pathColor color, int cost) {
		this.city1 = city1;
		this.city2 = city2;
		this.color = color;
		this.cost = cost;
	}

	/**
	 * String arg constructor Takes a string arg read in from a line in the csv
	 * file and populates a path object with it
	 * 
	 * @param pathArray
	 *            a single edge read in from csv file line
	 */
	public Path(String[] pathArray) { // TODO This constructor need to be fixed
		int i = 0;

		this.city1 = World.stringToCities.get(pathArray[i++]);
		this.city2 = World.stringToCities.get(pathArray[i++]);
		this.cost = Integer.parseInt(pathArray[i++]);
		this.color = pathColor.valueOf(pathArray[i++]);

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
	 * @return the color
	 */
	public pathColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(pathColor color) {
		this.color = color;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the player
	 */
	public Player getOwningPlayer() {
		return owningPlayer;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setOwningPlayer(Player player) {
		this.owningPlayer = player;
	}

	public boolean equals(Path p) {
		boolean c1 = this.getCity1().equals(p.getCity1());
		boolean c2 = this.getCity2().equals(p.getCity2());
		boolean col = (this.getColor() == p.getColor());
		return c1 && c2 && col;
	}

}
