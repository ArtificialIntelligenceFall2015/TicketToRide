package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Control.AStar;
import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.trainColor;

/**
 * 
 * @author junxnhe
 *
 */

public class PlayerAI extends Player{

	private List<List<Path>> favorPath;
	private Entry<pathColor, Integer> claimColor;
	private pathColor wantClaimColor;

	public PlayerAI(trainColor color) {
		super(color);
		favorPath=new ArrayList<List<Path>>();
	}
	
	/**
	 * @return the favorPath
	 */
	public List<List<Path>> getFavorPath() {
		return favorPath;
	}

	/**
	 * @return the claimColor
	 */
	public Entry<pathColor, Integer> getClaimColor() {
		return claimColor;
	}

	/**
	 * @param claimColor the claimColor to set
	 */
	public void setClaimColor(Entry<pathColor, Integer> claimColor) {
		this.claimColor = claimColor;
	}

	/**
	 * @return the wantClaimColor
	 */
	public pathColor getWantClaimColor() {
		return wantClaimColor;
	}

	/**
	 * @param wantClaimColor the wantClaimColor to set
	 */
	public void setWantClaimColor(pathColor wantClaimColor) {
		this.wantClaimColor = wantClaimColor;
	}

}
