package TicketToRide.Model;

import java.util.List;
import TicketToRide.Model.Constants.trainColor;

public class Player {
	private int score;
	private List<TrainCard> trainCards;
	private List<DestinationCard> desCards;
	private List<Path> ownPath;
	
	private trainColor color;
	
	private int piece=45;
	
	private static final int[] POINT={0,1,2,4,7,10,15};

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public trainColor getColor() {
		return color;
	}

	public void setColor(trainColor color) {
		this.color = color;
	}

	public int getPiece() {
		return piece;
	}

	public void takeAwayPiece(int p) {
		this.piece -= p;
	}
	
	public void addPath(Path p){
		ownPath.add(p);
		score+=POINT[p.getCost()];
	}
	
	public void calcDisCardPoint(){
		for(DestinationCard dc:desCards){
			if(dc.isCompleted()){
				score+=dc.getPoint();
			}else{
				score-=dc.getPoint();
			}
		}
	}
	
	public boolean buyPath(List<Path> world, Path path){
		if(world.contains(path)&&getPiece()>=path.getCost()){
			takeAwayPiece(path.getCost());
			addPath(world.remove(world.indexOf(path)));
			return true;
		}
		return false;
	}
}
