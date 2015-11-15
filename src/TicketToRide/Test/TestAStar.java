package TicketToRide.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import TicketToRide.Control.AStar;
import TicketToRide.Control.PathHandler;
import TicketToRide.Model.City;
import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Deck;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.World;

public class TestAStar {

	@Test
	public void testAStar(){
		new World();
		AStar aStar=new AStar(new PlayerAI(playerColor.BLACK), Deck.desCardDeck.get(0));
		aStar.run();
		List<City> city=aStar.getGoal().getList();
		for(City c:city){
			System.out.println(c.getCityName());
		}
	}
	
	@Test
	public void testDeterminePathClose(){
		new World();
		Player p=new PlayerAI(playerColor.BLACK);
		p.getDesCards().add(Deck.desCardDeck.get(0));
		AStar aStar=new AStar(p, Deck.desCardDeck.get(0));
		aStar.run();
		List<City> city=aStar.getGoal().getList();
		for(int i=1; i<city.size(); i++){
			PathHandler.getPath(city.get(i-1), city.get(i)).get(0).setOwningPlayer(p);
		}
		PathHandler.determinePathClose(p);
		assertTrue(p.getDesCards().get(0).isCompleted());
	}
}
