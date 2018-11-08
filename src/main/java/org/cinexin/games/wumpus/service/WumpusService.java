/**
 * 
 */
package org.cinexin.games.wumpus.service;

import org.cinexin.games.wumpus.constants.LivingActorStatus;
import org.cinexin.games.wumpus.model.Wumpus;

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
	 * This method is called when the {@link Wumpus} is killed
	 */
	public void die() {
		theWumpus.setStatus(LivingActorStatus.DEAD);
	}
}
