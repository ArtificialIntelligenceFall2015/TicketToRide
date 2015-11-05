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
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.World;

/**
 * uses the A* algorithm to compute the solution path
 */
public class AStar {
	private List<Frontier> frontiers; // list of frontier paths
	private List<Frontier> closed; // list of closed paths
	private Frontier goal; // final state of character array (all W's to left of
							// leftmost B)

	private City startCity;
	private City endCity;

	private PlayerAI player;

	/**
	 * This method adds a new frontier to the list
	 */
	public AStar(PlayerAI player, DestinationCard card) {
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
		Frontier target = frontiers.remove(0); // pop off initial state
		while (!goal(target) || !frontiers.isEmpty()) { // check if open state
														// is goal state
			expandFronter(target); // if not, expand target's children states
			Collections.sort(frontiers); // sort frontier list in ascending cost
											// order
			closed.add(target); // add popped off state to closed list
			target = frontiers.remove(0); // repeat process with new target
		}
		if (goal(target))
			goal = target; // invariant: target is your goal state
	}

	/**
	 * This method expands the target's children state
	 */
	private void expandFronter(Frontier frontier) {
		City lastCity = frontier.getLastCity();
		int lastCityIndex = World.cities.indexOf(lastCity);
		for (int i = 0; i < PathHandler.pathMatrix[lastCityIndex].length; i++) {
			boolean pathExist = PathHandler.pathMatrix[lastCityIndex][i];
			if (pathExist) {
				List<Path> pathList = PathHandler.getPath(lastCity, World.cities.get(i));
				boolean pathOwnAble = false;
				for (Path p : pathList) {
					pathOwnAble = pathOwnAble || p.getOwningPlayer() == null || p.getOwningPlayer() == player;
				}
				if (pathOwnAble) {
					City expandCity = World.cities.get(i);
					List<City> newList = new ArrayList<City>();
					newList.addAll(frontier.getList());
					newList.add(expandCity);
					int cost = frontier.getCost() + calPathCost(lastCity, expandCity);
					int heuristicCost = getHeuristicCost(expandCity);
					Frontier newFrontier = new Frontier(newList, cost, heuristicCost);
					if (!isContains(frontiers, newFrontier) && !isContains(frontiers, newFrontier)) {
						frontiers.add(newFrontier);
					}
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
				if (frontier.getWeight() < f.getWeight()) // true could be only
															// occurred when
															// list is a
															// reference of
															// frontiers
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
		// TODO
		return 0;
	}

}
