/**
 * 
 */
package org.cinexin.games.wumpus.model;

/**
 * @author migui
 * Model for Pits (Water Wells) in the Game
 */
public class Pit extends Actor {

	/**
	 * default constructor with no-args
	 */
	public Pit() {
		super();
	}

	/**
	 * Constructor with an initial position in the board
	 * @param position
	 */
	public Pit(Position position) {
		super(position);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pit [getPosition()=" + getPosition() + "]";
	}

	
}
