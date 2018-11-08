/**
 * 
 */
package org.cinexin.games.wumpus.model;

import org.cinexin.games.wumpus.constants.Directions;

/**
 * @author migui
 * Model for Arrows that {@link Hunter} can have
 */
public class Arrow extends Actor {

	private Directions direction;
	
	/**
	 * 
	 */
	public Arrow() {
	}

	/**
	 * @param position
	 */
	public Arrow(Position position) {
		super(position);
	}

	/**
	 * @return the direction
	 */
	public Directions getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	
}
