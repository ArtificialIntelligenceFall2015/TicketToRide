package TicketToRide.Control;
/**
 * @author Jun He
 * @author Sean Fast
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.City;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.World;

/**
 * uses the A* algorithm to compute the solution path
 */
public class AStar {
	private List<Frontier> frontiers; // list of frontier paths
	private List<Frontier> closed; // list of closed paths
	private Frontier goal; // final state

	private City startCity;
	private City endCity;

	private Player player;

	/**
	 * This method adds a new frontier to the list
	 */
	public AStar(Player player, DestinationCard card) {
		this.player = player;
		frontiers = new ArrayList<Frontier>();
		closed = new ArrayList<Frontier>();
		startCity = card.getCity1();
		endCity = card.getCity2();
		List<City> list = new ArrayList<City>();
		list.add(startCity);

		frontiers.add(new Frontier(list, 0, getHeuristicCost(startCity)));
	}

	/**
	 * This method performs the A* algorithm on the list
	 */
	public void run() {
		while (!frontiers.isEmpty() && (goal == null || goal.getCost() > frontiers.get(0).getWeight())) {
			Frontier target = frontiers.remove(0); // pop off initial state
			if (goal(target) && (goal == null || goal.getCost() > target.getCost()))
				goal = target; // invariant: target is your goal state is goal state
			else {
				expandFronter(target); // if not, expand target's children states
			}
			Collections.sort(frontiers); // sort frontier list in ascending cost order
			closed.add(target); // add popped off state to closed list
		}
	}

	/**
	 * This method expands the target's children state
	 */
	private void expandFronter(Frontier frontier) {
		City lastCity = frontier.getLastCity();
		List<City> connectCities = PathHandler.getConnectCities(lastCity);
		for (City expandCity : connectCities) {
			List<Path> pathList = PathHandler.getPath(lastCity, expandCity);
			boolean pathOwnAble = false;
			for (Path p : pathList) {
				pathOwnAble = pathOwnAble || p.getOwningPlayer() == null || p.getOwningPlayer() == player;
			}
			if (pathOwnAble) {
				List<City> newList = new ArrayList<City>();
				newList.addAll(frontier.getList());
				newList.add(expandCity);
				int cost = frontier.getCost() + calPathCost(lastCity, expandCity);
				int heuristicCost = getHeuristicCost(expandCity);
				Frontier newFrontier = new Frontier(newList, cost, heuristicCost);
				if (!isContains(frontiers, newFrontier) && !isContains(closed, newFrontier)) {
					frontiers.add(newFrontier);
				}
			}
		}
	}

	/**
	 * This method checks if given frontier is goal state
	 */
	private boolean goal(Frontier frontier) {
		City lastCity = frontier.getLastCity();
		return lastCity.equals(endCity);
	}

	/**
	 * This method checks if list contains given frontier
	 */
	private boolean isContains(List<Frontier> list, Frontier frontier) {
		for (Frontier f : list) {
			if (f.equals(frontier)) {
				// true could be only occurred when list is a reference of frontiers
				if (frontier.getWeight() < f.getWeight())
					list.set(list.indexOf(f), frontier);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return goal
	 */
	public Frontier getGoal() {
		return goal;
	}

	private int getHeuristicCost(City c) {
		int x = Math.abs(endCity.getX_val() - c.getX_val());
		int y = Math.abs(endCity.getY_val() - c.getY_val());
		return (int) Math.sqrt(x * x + y * y);
	}

	public int calPathCost(City city1, City city2) {
		List<Path> pathList = PathHandler.getPath(city1, city2);
		int minCost = Integer.MAX_VALUE;
		for (Path p : pathList) {
			if (CostStrategies.ownPath(player, p)) {
				minCost = 0;
			} else {
				minCost = Math.min(minCost, CostStrategies.getCost(player, p));
			}
		}
		return minCost;
	}
}
