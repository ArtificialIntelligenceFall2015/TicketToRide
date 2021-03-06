/**
 * @author junxnhe
 * This class is unit test for Longest path search
 */
package TicketToRide.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import TicketToRide.Control.AStar;
import TicketToRide.Control.PathHandler;
import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Deck;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.World;

public class TestLongestPath {

	@Test
	public void testLongestPath(){
		new World();
		Player player=new Player(playerColor.RED);
		AStar aStar=new AStar(player, Deck.desCardDeck.get(0));
		aStar.run();
		List<Path> list=new ArrayList<Path>();
		for(int i=1; i<aStar.getGoal().getList().size(); i++){
			Path path=PathHandler.getPath(aStar.getGoal().getList().get(i-1), aStar.getGoal().getList().get(i)).get(0);
			path.setOwningPlayer(player);
			list.add(path);
			player.getOwnPath().add(path);
		}
		
		int cost=0;
		for(Path path: list){
			cost+=path.getCost();
		}
		assertEquals(PathHandler.getLongestPath(player),cost);
	}
}