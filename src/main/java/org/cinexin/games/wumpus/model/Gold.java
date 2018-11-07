/**
 * 
 */
package org.cinexin.games.wumpus.model;

/**
 * @author migui
 * 
 * Model class for gold
 */
public class Gold extends Actor {

	private boolean caught = false;
	
	/**
	 * default no-args constructor
	 */
	public Gold() {
		super();
	}

	/**
	 * @param position
	 */
	public Gold(Position position) {
		super(position);
	}

	/**
	 * @return the caught
	 */
	public boolean isCaught() {
		return caught;
	}

	/**
	 * @param caught the caught to set
	 */
	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	
	
}
