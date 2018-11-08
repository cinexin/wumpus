/**
 * 
 */
package org.cinexin.games.wumpus.util;

import static org.junit.Assert.*;

import org.cinexin.games.wumpus.model.Position;
import org.junit.Test;

/**
 * @author migui
 * 
 * Test class for {@link PositionHelper}
 *
 */
public class PositionHelperTest {

	/**
	 * X
	 *  Y
	 */
	@Test
	public void testAreAdjacent_PositionsAdjacentDownRightUpLeft() {
		final Position positionA = new Position(0,0);
		final Position positionB = new Position(1,1);
		
		assertTrue(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertTrue(PositionHelper.arePositionsAdjacent(positionB, positionA));
	}
	
	/**
	 * 	X
	 * Y
	 */
	@Test
	public void testAreAdjacent_PositionsAdjacentDownLeftUpRight() {
		final Position positionA = new Position(2,2);
		final Position positionB = new Position(1,1);
		
		assertTrue(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertTrue(PositionHelper.arePositionsAdjacent(positionB, positionA));
	}

	/**
	 * X
	 * Y
	 */
	@Test
	public void testAreAdjacent_PositionsAdjacentUpDown() {
		final Position positionA = new Position(1,2);
		final Position positionB = new Position(1,1);
		
		assertTrue(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertTrue(PositionHelper.arePositionsAdjacent(positionB, positionA));		
	}
	
	/**
	 * XY
	 */
	@Test
	public void testAreAdjacent_PositionsAdjacentLeftRight() {
		final Position positionA = new Position(1,1);
		final Position positionB = new Position(2,1);
		
		assertTrue(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertTrue(PositionHelper.arePositionsAdjacent(positionB, positionA));		
	}
	
	/**
	 * X      
	 *    	Y
	 */
	@Test
	public void testAreAdjacent_PositionsNotAdjacent() {
		Position positionA = new Position(1,1);
		Position positionB = new Position(3,1);
		
		assertFalse(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertFalse(PositionHelper.arePositionsAdjacent(positionB, positionA));
		
		positionA = new Position(0,0);
		positionB = new Position(0,2);
		assertFalse(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertFalse(PositionHelper.arePositionsAdjacent(positionB, positionA));

		positionA = new Position(1,0);
		positionB = new Position(2,4);
		assertFalse(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertFalse(PositionHelper.arePositionsAdjacent(positionB, positionA));

		positionA = new Position(1,0);
		positionB = new Position(3,0);
		assertFalse(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertFalse(PositionHelper.arePositionsAdjacent(positionB, positionA));
		
		positionA = new Position(1,0);
		positionB = new Position(3,1);
		assertFalse(PositionHelper.arePositionsAdjacent(positionA, positionB));
		assertFalse(PositionHelper.arePositionsAdjacent(positionB, positionA));
	}
}
