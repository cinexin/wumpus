/**
 * 
 */
package org.cinexin.games.wumpus.service;

import static org.junit.Assert.*;

import org.cinexin.games.wumpus.constants.LivingActorStatus;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Wumpus;
import org.cinexin.games.wumpus.service.WumpusService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author migui
 * Test for {@link WumpusService}
 */
public class WumpusServiceTest {

	WumpusService wumpusService;
	Wumpus wumpus;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		wumpus = new Wumpus();
		wumpus.setPosition(Position.of(2, 2));
		wumpusService = new WumpusService(wumpus);
	}

	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.WumpusService#die()}.
	 */
	@Test
	public void testDie() {
		wumpusService.die();
		assertEquals(LivingActorStatus.DEAD, wumpus.getStatus());
	}

}
