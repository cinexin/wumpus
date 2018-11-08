/**
 * 
 */
package org.cinexin.games.wumpus.model;

import org.cinexin.games.wumpus.constants.Directions;
import org.cinexin.games.wumpus.constants.LivingActorStatus;

/**
 * @author migui
 * Model class for Hunter actor
 */
public class Hunter extends Actor {

	private LivingActorStatus status = LivingActorStatus.ALIVE;
	// by default at the beginning hunter is heading right direction
	private Directions direction = Directions.RIGHT;
	private Quiver quiver;
	// flag to mark if the hunter already has the "Gold" or not
	private boolean ownsTheGold = Boolean.FALSE;
	

	/**
	 * Constructor with an initial position in the board
	 * @param position initial position
	 */
	public Hunter(Position position) {
		super(position);
		quiver = new Quiver(position);
	}

	/**
	 * Constructor with an initial position in the board
	 * @param position initial position
	 */
	public Hunter(Position position, int numOfArrows) {
		super(position);
		quiver = new Quiver(position, numOfArrows);
	}
	
	/**
	 * @return the status
	 */
	public LivingActorStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(LivingActorStatus status) {
		this.status = status;
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

	/**
	 * @return the numOfArrows
	 */
	public int getNumOfArrows() {
		if (quiver != null) {
			return quiver.arrows.size();
		} else {
			return 0;
		}
	}

	/**
	 * @return the ownsTheGold
	 */
	public boolean ownsTheGold() {
		return ownsTheGold;
	}

	/**
	 * @param ownsTheGold the ownsTheGold to set
	 */
	public void setOwnsTheGold(boolean ownsTheGold) {
		this.ownsTheGold = ownsTheGold;
	}

	@Override
	public void setPosition(Position position) {
		super.setPosition(position);
		quiver.setPosition(position);
	}

	/**
	 * @return the quiver
	 */
	public Quiver getQuiver() {
		return quiver;
	}

	/**
	 * @param quiver the quiver to set
	 */
	public void setQuiver(Quiver quiver) {
		this.quiver = quiver;
	}
	
}
