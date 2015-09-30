package TicketToRide.Control;

import java.util.List;

import TicketToRide.Model.DestinationCardDeck;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCardDeck;

public class Game {
	private List<Path> world;
	private List<Player> players;
	private Player turn;
	private TrainCardDeck trainCardDeck;
	private DestinationCardDeck desCardDeck;
}
