package TicketToRide.Model;

import TicketToRide.Model.Constants.city;

public class DestinationCard {
	private city city1;
	private city city2;
	
	private int point;
	private boolean completed=false;
	
	public DestinationCard(city city1, city city2, int point) {
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.point = point;
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
