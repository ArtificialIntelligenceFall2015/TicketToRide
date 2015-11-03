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
	private List<Frontier> frontiers; //list of frontier paths
	private List<Frontier> closed; //list of closed paths
	private Frontier goal; //final state of character array (all W's to left of leftmost B)

	private String startCity;
	private String endCity;
	
	private PlayerAI player;

	/**
	 * This method adds a new frontier to the list
	 */
	public AStar(PlayerAI player,DestinationCard card) {
		this.player=player;
		frontiers = new ArrayList<Frontier>();
		closed = new ArrayList<Frontier>();
		startCity=card.getCity1();
		endCity=card.getCity2();
		List<String> list=new ArrayList<String>();
		list.add(startCity);
		
		frontiers.add(new Frontier(list, 0));
	}

	/**
	 * This method performs the A* algorithm on the list
	 */
	public void run() {
		Frontier target = frontiers.remove(0); //pop off initial state
		while (!goal(target)) { //check if open state is goal state
			expandFronter(target); //if not, expand target's children states
			Collections.sort(frontiers); //sort frontier list in ascending cost order
			closed.add(target); //add popped off state to closed list
			target = frontiers.remove(0); //repeat process with new target
		}
		goal=target; //invariant: target is your goal state
	}

	/**
	 * This method expands the target's children state
	 */
	private void expandFronter(Frontier frontier) {
		String lastCity=frontier.getLastCity();
		int lastCityIndex=World.city.indexOf(lastCity);
		for(int i=0; i<PathHandler.pathMatrix[lastCityIndex].length; i++){
			if(PathHandler.pathMatrix[lastCityIndex][i]){
				List<String> newList = new ArrayList<String>();
				newList.addAll(frontier.getList());
				newList.add(World.city.get(i));
				int cost=frontier.getCost()+Frontier.calPathCost(player, lastCity,World.city.get(i));
				Frontier newFrontier=new Frontier(newList, cost);
				if(!isContains(frontiers,newFrontier)&&!isContains(frontiers,newFrontier)){
					frontiers.add(newFrontier);
				}
			}
		}
	}

	/**
	 * This method checks if given frontier is goal state
	 */
	private boolean goal(Frontier frontier) {
		String lastCity=frontier.getLastCity();
		return lastCity.equals(endCity);
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
	public Frontier getGoal() {
		return goal;
	}

}
