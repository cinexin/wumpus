/**
 * 
 */
package org.chs.test.service;

import static org.junit.Assert.*;

import org.chs.test.constants.LivingActorStatus;
import org.chs.test.model.Position;
import org.chs.test.model.Wumpus;
import org.junit.Before;
import org.junit.Test;

/**
 * @author migui
 *
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
		wumpus.setPosition(new Position(2,2));
		wumpusService = new WumpusService(wumpus);
	}

	/**
	 * Test method for {@link org.chs.test.service.WumpusService#die()}.
	 */
	@Test
	public void testDie() {
		wumpusService.die();
		assertEquals(LivingActorStatus.DEAD, wumpus.getStatus());
	}

}
