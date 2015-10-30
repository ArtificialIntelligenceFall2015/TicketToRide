package TicketToRide.Control;
/**
 * Concepts of AI - Tinkham
 * Jun He
 * Sean Fast
 */

import java.util.List;

import TicketToRide.Model.Path;
import TicketToRide.Model.Player;

/**
 * This class defines the Frontier object which represents the frontier of the
 * A* search
 */
public class Frontier implements Comparable<Frontier> {
	Frontier preFrontier; // previous frontier in path
	List<Path> list; // collection of characters in iteration
	int cost; // current cost to get to the path

	/**
	 * Constructor for Frontier object
	 */
	public Frontier(Frontier preFrontier, List<Path> list, int cost) {
		this.preFrontier = preFrontier;
		this.list = list;
		this.cost = cost;
	}

	/**
	 * @return the list
	 */
	public List<Path> getList() {
		return list;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @return weight 
	 * 			the current cost + the heuristic value heuristic value is
	 *         the number of B's in first four spaces
	 */
	public int getWeight() {
		return getHeuristicCost() + cost;
	}

	private int getHeuristicCost() {
		// TODO Auto-generated method stub
		return 0;
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
		//TODO
		return false;
	}

	/**
	 * @return preFrontier object
	 */
	public Frontier getPreFrontier() {
		return preFrontier;
	}

	/**
	 * @param preFrontier
	 */
	public void setPreFrontier(Frontier preFrontier) {
		this.preFrontier = preFrontier;
	}
	
	public static int calPathCost(Player player, Path path){
		//TODO
		return 0;
	}
}
