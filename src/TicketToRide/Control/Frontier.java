package TicketToRide.Control;
/**
 * Concepts of AI - Tinkham
 * Jun He
 * Sean Fast
 */

import java.util.List;

import TicketToRide.Model.City;
import TicketToRide.Model.PlayerAI;

/**
 * This class defines the Frontier object which represents the frontier of the
 * A* search
 */
public class Frontier implements Comparable<Frontier> {
	List<City> list; // collection of characters in iteration
	int cost; // current cost to get to the path

	/**
	 * Constructor for Frontier object
	 */
	public Frontier(List<City> list, int cost) {
		this.list = list;
		this.cost = cost;
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
	 * @return weight 
	 * 	the current cost + the heuristic value heuristic value is
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
		return this.getLastCity().equals(arg0.getLastCity());
	}
	
	public static int calPathCost(PlayerAI player, City city1, City city2){
		//TODO
		return 0;
	}
	
	public City getLastCity(){
		return list.get(list.size()-1);
	}
}
