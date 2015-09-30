package TicketToRide.Model;

import TicketToRide.Model.Constants.*;

public class Path {
	private city city1;
	private city city2;
	private trainColor color;
	private int cost;
	
	public Path(city city1, city city2, trainColor color, int cost) {
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
	public trainColor getColor() {
		return color;
	}
	public void setColor(trainColor color) {
		this.color = color;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
