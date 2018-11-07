/**
 * 
 */
package org.chs.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.chs.test.constants.Directions;
import org.chs.test.exception.QuiverEmptyException;
import org.chs.test.exception.WallReachedException;
import org.chs.test.model.Hunter;
import org.chs.test.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author migui
 *
 */
public class HunterServiceTest {

	Hunter hunter;
	HunterService hunterService;
	// we assume a 8x8 square board
	final Position[] bounds =  new Position[] {Position.of(0, 0), Position.of(7, 7)};
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hunter = Mockito.mock(Hunter.class);
		hunterService = new HunterService(hunter);
	}

	/**
	 * Test method for {@link org.chs.test.service.HunterService#extractArrow()}.
	 * @throws QuiverEmptyException 
	 */
	@Test
	public void testExtractArrowNonEmptyQuiver() throws QuiverEmptyException {
		when(hunter.getNumOfArrows()).thenReturn(20);
		hunterService.extractArrow();
	}

	/**
	 * Test method for {@link org.chs.test.service.HunterService#extractArrow()}.
	 * @throws QuiverEmptyException 
	 */
	@Test(expected = QuiverEmptyException.class)
	public void testExtractArrowEmptyQuiver() throws QuiverEmptyException {
		when(hunter.getNumOfArrows()).thenReturn(0);
		hunterService.extractArrow();		
	}

	/**
	 * Test method for {@link org.chs.test.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromUpDirection() {
		when(hunter.getDirection()).thenReturn(Directions.UP);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.RIGHT);
	}
	
	/**
	 * Test method for {@link org.chs.test.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromRightDirection() {
		when(hunter.getDirection()).thenReturn(Directions.RIGHT);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.DOWN);
	}

	/**
	 * Test method for {@link org.chs.test.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromDownDirection() {
		when(hunter.getDirection()).thenReturn(Directions.DOWN);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.LEFT);
	}
	
	/**
	 * Test method for {@link org.chs.test.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromLeftDirection() {
		when(hunter.getDirection()).thenReturn(Directions.LEFT);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.UP);
	}
	
	/**
	 * Test move forward to UP direction - OK
	 * @throws WallReachedException 
	 */
	@Test
	public void testMoveForwardToUpDirection() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2, 3));
		when(hunter.getDirection()).thenReturn(Directions.UP);
		

		final Position newPosition = hunterService.moveForward(bounds);
		final Position expectedNewPosition = new Position(2,2);
		assertEquals(newPosition, expectedNewPosition);
	}
	
	/**
	 * Test move forward to RIGHT direction - OK 
	 */
	@Test
	public void testMoveForwardToRightDirection() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2, 3));
		when(hunter.getDirection()).thenReturn(Directions.RIGHT);
		

		final Position newPosition = hunterService.moveForward(bounds);
		final Position expectedNewPosition = new Position(3,3);
		assertEquals(newPosition, expectedNewPosition);	
	}
	
	/**
	 * Test move forward to DOWN direction - OK
	 */
	@Test
	public void testMovedForwardToDownDirection() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2, 3));
		when(hunter.getDirection()).thenReturn(Directions.DOWN);
		
		final Position newPosition = hunterService.moveForward(bounds);
		final Position expectedNewPosition = new Position(2,4);
		assertEquals(newPosition, expectedNewPosition);	
	}
	
	/**
	 * Test move forward to LEFT direction - OK
	 */
	@Test
	public void testMoveForwardToLeftDirection() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2, 3));
		when(hunter.getDirection()).thenReturn(Directions.LEFT);
		
		final Position newPosition = hunterService.moveForward(bounds);
		final Position expectedNewPosition = new Position(1,3);
		assertEquals(newPosition, expectedNewPosition);		
	}
	
	/**
	 * Test move forward to UP direction - OK
	 * @throws WallReachedException 
	 */
	@Test (expected = WallReachedException.class)
	public void testMoveForwardToUpDirectionLimitReached() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2, 0));
		when(hunter.getDirection()).thenReturn(Directions.UP);	
		hunterService.moveForward(bounds);
	}	
	
	/**
	 * Test move forward to RIGHT direction - right boundary is reached
	 * @throws WallReachedException
	 */
	@Test (expected = WallReachedException.class)
	public void testMoveForwardToRightDirectionLimitReached() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(7, 0));
		when(hunter.getDirection()).thenReturn(Directions.RIGHT);
		hunterService.moveForward(bounds);
	}
	
	/**
	 * Test move forward to DOWN direction - down boundary is reached
	 * @throws WallReachedException
	 */
	@Test (expected = WallReachedException.class)
	public void testMoveForwardToDownDirectionLimitReached() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(2,7));
		when(hunter.getDirection()).thenReturn(Directions.DOWN);
		hunterService.moveForward(bounds);
	}
	
	/**
	 * Test move forward to LEFT direction - left boundary is reached
	 */
	@Test (expected = WallReachedException.class)
	public void testMoveForwardToLeftDirectionLimitReached() throws WallReachedException {
		when(hunter.getPosition()).thenReturn(Position.of(0, 7));
		when(hunter.getDirection()).thenReturn(Directions.LEFT);
		hunterService.moveForward(bounds);
	}	
	
}
