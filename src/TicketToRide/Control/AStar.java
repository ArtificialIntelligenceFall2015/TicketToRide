package TicketToRide.Control;
/**
 * @author Jun He
 * @author Sean Fast
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import TicketToRide.Model.Path;

/**
 * This class receives user input from console in the form of a string
 * and converts it to uppercase, then a character array. Then it uses the A*
 * algorithm to compute the solution path
 */
public class AStar {
	private static List<Frontier> frontiers = new ArrayList<Frontier>(); //list of frontier paths
	private static List<Frontier> closed = new ArrayList<Frontier>(); //list of closed paths
	private List<Frontier> goal; //final state of character array (all W's to left of leftmost B)


	/**
	 * This method adds a new frontier to the list
	 */
	public AStar(List<Path> list) {
		frontiers.add(new Frontier(null, list, 0));
	}

	/**
	 * This method performs the A* algorithm on the list
	 */
	public void run() {
		Frontier target = frontiers.remove(0); //pop off initial state
		while (!goal(target)) { //check if open state is goal state
			next(target); //if not, expand target's children states
			Collections.sort(frontiers); //sort frontier list in ascending cost order
			closed.add(target); //add popped off state to closed list
			target = frontiers.remove(0); //repeat process with new target
		}
		goal.add(target); //invariant: target is your goal state
	}

	/**
	 * This method expands the target's children state
	 */
	private void next(Frontier frontier) {
		//TODO
	}

	/**
	 * This method checks if given frontier is goal state
	 */
	private boolean goal(Frontier frontier) {
		//TODO
		return false;
	}
	
	/**
	 * This method checks if list contains given frontier
	 */
	private boolean isContains(List<Frontier>list,Frontier frontier){
		for (Frontier f : list) {
			if (f.equals(frontier)){
				if (frontier.getWeight() < f.getWeight()) //true could be only occurred when list is a reference of frontiers
					list.set(list.indexOf(f), frontier);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return goal 
	 */
	public List<Frontier> getGoal() {
		return goal;
	}

}
