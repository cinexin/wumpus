/**
 * 
 */
package org.cinexin.games.wumpus.model;

import org.cinexin.games.wumpus.constants.LivingActorStatus;

/**
 * @author migui
 * 
 * Model class for Wumpus
 */
public class Wumpus extends Actor {

	private LivingActorStatus status;
	
	/**
	 * Default constructor with no-args
	 */
	public Wumpus() {
		super();
		this.status = LivingActorStatus.ALIVE;
	}

	/**
	 * @param position
	 */
	public Wumpus(Position position) {
		super(position);
		this.status = LivingActorStatus.ALIVE;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Wumpus [status=" + status + ", getPosition()=" + getPosition() + "]";
	}
	
	
}
