/**
 * 
 */
package org.chs.test.model;

import org.chs.test.constants.Directions;
import org.chs.test.constants.LivingActorStatus;

/**
 * @author migui
 * Model class for Hunter actor
 */
public class Hunter extends Actor {

	private LivingActorStatus status = LivingActorStatus.ALIVE;
	// by default at the beginning hunter is heading right direction
	private Directions direction = Directions.RIGHT;
	private int numOfArrows;
	// flag to mark if the hunter already has the "Gold" or not
	private boolean ownsTheGold = Boolean.FALSE;
	
	/**
	 * default constructor with no-args
	 */
	public Hunter() {
		super();
	}

	/**
	 * Constructor with an initial position in the board
	 * @param position initial position
	 */
	public Hunter(Position position) {
		super(position);
	}

	/**
	 * Constructor with an initial position in the board
	 * @param position initial position
	 */
	public Hunter(Position position, int numOfArrows) {
		super(position);
		this.numOfArrows = numOfArrows;
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
		return numOfArrows;
	}

	/**
	 * @param numOfArrows the numOfArrows to set
	 */
	public void setNumOfArrows(int numOfArrows) {
		this.numOfArrows = numOfArrows;
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

}