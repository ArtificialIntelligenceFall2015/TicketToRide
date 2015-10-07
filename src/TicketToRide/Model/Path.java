package TicketToRide.Model;

import TicketToRide.Model.Constants.*;

public class Path {
	private city city1;
	private city city2;
	private pathColor color;
	private int cost;
	private Player player=null;
	
	public Path(city city1, city city2, pathColor color, int cost) {
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.color = color;
		this.cost = cost;
	}
	
	public city getCity1() {
		return city1;
	}
	public void setCity1(city city1) {
		this.city1 = city1;
	}
	public city getCity2() {
		return city2;
	}
	public void setCity2(city city2) {
		this.city2 = city2;
	}
	public pathColor getColor() {
		return color;
	}
	public void setColor(pathColor color) {
		this.color = color;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
