package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Constants.strategies;
import TicketToRide.Model.Constants.trainCard;

/**
 * 
 * @author junxnhe
 *
 */

public class PlayerAI extends Player {

	private List<List<Path>> favorPath;
	private Path wantClaimPath;

	private List<DestinationCard> uncompleteableDesCard;

	private HashMap<trainCard, Integer> handCollection;
	private HashMap<trainCard, Integer> deckCollection;

	private strategies[] strategiesList;

	public PlayerAI(playerColor color, strategies... s) {
		super(color);
		favorPath = new ArrayList<List<Path>>();
		uncompleteableDesCard = new ArrayList<DestinationCard>();
		setStrategiesList(s);
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
	 * @param uncompleteableDesCard
	 *            the uncompleteableDesCard to set
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
	 * @param wantClaimPath
	 *            the wantClaimPath to set
	 */
	public void setWantClaimPath(Path wantClaimPath) {
		this.wantClaimPath = wantClaimPath;
	}

	/**
	 * @return the handCollection
	 */
	public HashMap<trainCard, Integer> getHandCollection() {
		return handCollection;
	}

	/**
	 * @param handCollection
	 *            the handCollection to set
	 */
	public void setHandCollection(HashMap<trainCard, Integer> handCollection) {
		this.handCollection = handCollection;
	}

	/**
	 * @return the deckCollection
	 */
	public HashMap<trainCard, Integer> getDeckCollection() {
		return deckCollection;
	}

	/**
	 * @param deckCollection
	 *            the deckCollection to set
	 */
	public void setDeckCollection(HashMap<trainCard, Integer> deckCollection) {
		this.deckCollection = deckCollection;
	}

	public strategies[] getStrategiesList() {
		return strategiesList;
	}

	public void setStrategiesList(strategies[] strategiesList) {
		this.strategiesList = strategiesList;
	}

}
