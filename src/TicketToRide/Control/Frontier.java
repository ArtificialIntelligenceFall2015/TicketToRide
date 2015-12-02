package TicketToRide.Control;

/**
 * Jun He
 * Sean Fast
 */

import java.util.List;

import TicketToRide.Model.City;

/**
 * This class defines the Frontier object which represents the frontier of the
 * A* search
 */
public class Frontier implements Comparable<Frontier> {
	List<City> list; // collection of characters in iteration
	int cost; // current cost to get to the path
	int heuristicCost;

	/**
	 * Constructor for Frontier object
	 */
	public Frontier(List<City> list, int cost, int heuristicCost) {
		this.list = list;
		this.cost = cost;
		this.heuristicCost = heuristicCost;
	}

	/**
	 * @return the list
	 */
	public List<City> getList() {
		return list;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @return weight the current cost + the heuristic value heuristic value is
	 */
	public int getWeight() {
		return heuristicCost + cost;
	}

	/**
	 * override compareTo function to allow for the collection to be sorted
	 */
	public int compareTo(Frontier arg0) {
		return getWeight() - arg0.getWeight();
	}

	/**
	 * override equals function to see if the frontier is in the closed or
	 * existing frontier list
	 */
	public boolean equals(Frontier arg0) {
		return this.getLastCity().equals(arg0.getLastCity());
	}

	/**
	 * get last city of the list
	 * 
	 * @return
	 */
	public City getLastCity() {
		return list.get(list.size() - 1);
	}
}