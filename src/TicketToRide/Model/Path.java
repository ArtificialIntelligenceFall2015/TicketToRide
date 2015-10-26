/**
 * Path.java
 * This class represents the Path object in the game.
 * It is used to for the edges of the graph, and is 
 * read in from a csv file.
 */
package TicketToRide.Model;

import TicketToRide.Model.Constants.*;

/**
 * @author Jun He
 * @author Sean Fast
 *
 */
public class Path {
	private city city1;
	private city city2;
	private pathColor color;
	private int cost = 0;
	private Player player = null; // recommend renaming this to owningPlayer -SF

	/**
	 * Basic constructor
	 * @param city1
	 * @param city2
	 * @param color
	 * @param cost
	 */
	public Path(city city1, city city2, pathColor color, int cost) {
		this.city1 = city1;
		this.city2 = city2;
		this.color = color;
		this.cost = cost;
	}

	/**
	 * String arg constructor
	 * Takes a string arg read in from a line in the csv file
	 * and populates a path object with it
	 * @param pathArray a single edge read in from csv file line
	 */
	public Path(String[] pathArray) {
		int i = 0;

		this.city1 = city.valueOf(pathArray[i++]);
		this.city2 = city.valueOf(pathArray[i++]);
		this.cost = Integer.parseInt(pathArray[i++]);
		this.color = pathColor.valueOf(pathArray[i++]);

	}

	/**
	 * @return the city1
	 */
	public city getCity1() {
		return city1;
	}

	/**
	 * @param city1
	 *            the city1 to set
	 */
	public void setCity1(city city1) {
		this.city1 = city1;
	}

	/**
	 * @return the city2
	 */
	public city getCity2() {
		return city2;
	}

	/**
	 * @param city2
	 *            the city2 to set
	 */
	public void setCity2(city city2) {
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
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
