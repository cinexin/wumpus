/**
 * 
 */
package org.chs.test.service;

import org.chs.test.constants.LivingActorStatus;
import org.chs.test.model.Wumpus;

/**
 * @author migui
 * Service for {@link Wumpus}
 *
 */
public class WumpusService {

	private Wumpus theWumpus;
	
	/**
	 * Constructor
	 * @param theWumpus instance of {@link Wumpus}
	 */
	public WumpusService(final Wumpus theWumpus) {
		this.theWumpus = theWumpus;
	}

	/**
	 * This method is called when the wumpus is killed
	 */
	public void die() {
		theWumpus.setStatus(LivingActorStatus.DEAD);
	}
}
