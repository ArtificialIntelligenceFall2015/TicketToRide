package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import TicketToRide.Model.Constants.pathColor;
import TicketToRide.Model.Constants.playerColor;

/**
 * 
 * @author junxnhe
 *
 */

public class PlayerAI extends Player{

	private List<List<Path>> favorPath;
	private Path wantClaimPath;
	
	private List<DestinationCard> uncompleteableDesCard;

	public PlayerAI(playerColor color) {
		super(color);
		favorPath=new ArrayList<List<Path>>();
		uncompleteableDesCard=new ArrayList<DestinationCard>();
	}
	
	/**
	 * @return the favorPath
	 */
	public List<List<Path>> getFavorPath() {
		return favorPath;
	}

	/**
	 * @return the uncompleteableDesCard
	 */
	public List<DestinationCard> getUncompleteableDesCard() {
		return uncompleteableDesCard;
	}

	/**
	 * @param uncompleteableDesCard the uncompleteableDesCard to set
	 */
	public void setUncompleteableDesCard(List<DestinationCard> uncompleteableDesCard) {
		this.uncompleteableDesCard = uncompleteableDesCard;
	}

	/**
	 * @return the wantClaimPath
	 */
	public Path getWantClaimPath() {
		return wantClaimPath;
	}

	/**
	 * @param wantClaimPath the wantClaimPath to set
	 */
	public void setWantClaimPath(Path wantClaimPath) {
		this.wantClaimPath = wantClaimPath;
	}

}
