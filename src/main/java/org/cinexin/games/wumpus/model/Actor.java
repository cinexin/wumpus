/**
 * 
 */
package org.cinexin.games.wumpus.model;

/**
 * @author migui
 *
 * Any Element in the game is in "Actor" 
 * (ie: The hunter, the Wumpus, walls, water wells)
 * Initially it has a position in the board
 */
public class Actor {
	private Position position;

	/**
	 * default constructor with no args
	 */
	public Actor() {
		
	}
	
		
	/**
	 * @param position
	 */
	public Actor(Position position) {
		this.position = position;
	}


	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
