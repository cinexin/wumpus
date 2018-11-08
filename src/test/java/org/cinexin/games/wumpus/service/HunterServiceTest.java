/**
 * 
 */
package org.cinexin.games.wumpus.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.cinexin.games.wumpus.constants.Directions;
import org.cinexin.games.wumpus.exception.QuiverEmptyException;
import org.cinexin.games.wumpus.exception.WallReachedException;
import org.cinexin.games.wumpus.model.Arrow;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Quiver;
import org.cinexin.games.wumpus.service.HunterService;
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
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#extractArrow()}.
	 * @throws QuiverEmptyException 
	 */
	@Test
	public void testExtractArrowNonEmptyQuiver() throws QuiverEmptyException {
		final Quiver quiver = new Quiver(Position.of(0, 0));
		final Arrow arrow = new Arrow();
		
		quiver.getArrows().add(arrow);
		System.out.println("[DEBUG] Quiver num of arrows: " + quiver.getArrows().size());
		
		when(hunter.getQuiver()).thenReturn(quiver);
		when(hunter.getNumOfArrows()).thenReturn(quiver.getArrows().size());
		when(hunter.getPosition()).thenReturn(Position.of(2, 4));
		when(hunter.getDirection()).thenReturn(Directions.LEFT);
		
		final Arrow extractedArrow = hunterService.extractArrow();
		
		assertEquals(extractedArrow.getPosition(), Position.of(2, 4));
		assertEquals(extractedArrow.getDirection(), Directions.LEFT);
	}

	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#extractArrow()}.
	 * @throws QuiverEmptyException 
	 */
	@Test(expected = QuiverEmptyException.class)
	public void testExtractArrowEmptyQuiver() throws QuiverEmptyException {
		when(hunter.getNumOfArrows()).thenReturn(0);
		hunterService.extractArrow();		
	}

	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#extractArrow()}.
	 * @throws QuiverEmptyException 
	 */
	@Test(expected = QuiverEmptyException.class)
	public void testExtractArrowsUntilEmptyQuiver() throws QuiverEmptyException {
		final Quiver quiver = new Quiver(Position.of(0, 0));
		
		final Arrow arrow1 = new Arrow();
		final Arrow arrow2 = new Arrow();
		final Arrow arrow3 = new Arrow();
		final Arrow arrow4 = new Arrow();
		quiver.getArrows().add(arrow1);
		quiver.getArrows().add(arrow2);
		quiver.getArrows().add(arrow3);
		quiver.getArrows().add(arrow4);
	
		
		final Directions hunterDirection = Directions.DOWN;
		final Position hunterPosiiton = Position.of(3, 4);
		
		when(hunter.getQuiver()).thenReturn(quiver);
		when(hunter.getDirection()).thenReturn(hunterDirection);
		when(hunter.getPosition()).thenReturn(hunterPosiiton);
		
		do {
			when(hunter.getNumOfArrows()).thenReturn(quiver.getArrows().size());
			Arrow extractedArrow = hunterService.extractArrow();
			assertEquals(extractedArrow.getPosition(), hunter.getPosition());
			assertEquals(extractedArrow.getDirection(), hunter.getDirection());
		} while(true);
	}
	
	 /** 
	  * Test method for {@link org.cinexin.games.wumpus.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromUpDirection() {
		when(hunter.getDirection()).thenReturn(Directions.UP);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.RIGHT);
	}
	
	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromRightDirection() {
		when(hunter.getDirection()).thenReturn(Directions.RIGHT);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.DOWN);
	}

	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#rotate()}.
	 */
	@Test
	public void testRotateFromDownDirection() {
		when(hunter.getDirection()).thenReturn(Directions.DOWN);
		final Directions newDirection = hunterService.rotate();
		assertEquals(newDirection, Directions.LEFT);
	}
	
	/**
	 * Test method for {@link org.cinexin.games.wumpus.service.HunterService#rotate()}.
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
