package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Model.Constants.pathColor;

/**
 * 
 * @author junxnhe
 *
 */

public class PlayerAI extends Player{

	private List<List<Path>> favorPath=new ArrayList<List<Path>>();
	private Entry<pathColor, Integer> claimColor = null;
	private pathColor wantClaimColor =null;

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
