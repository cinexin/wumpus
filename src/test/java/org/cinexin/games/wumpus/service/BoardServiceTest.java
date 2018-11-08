package org.cinexin.games.wumpus.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.cinexin.games.wumpus.constants.Directions;
import org.cinexin.games.wumpus.constants.LivingActorStatus;
import org.cinexin.games.wumpus.model.Arrow;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.Gold;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Pit;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Wumpus;
import org.cinexin.games.wumpus.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardServiceTest {

	BoardService boardService;
	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = Mockito.mock(Board.class);
		boardService = new BoardService(board);
	}

	@Test
	public void testCheckHunterCanExit_HunterInInitialPosition() {
		final Position hunterPosition = new Position(0,0); 
		final Hunter hunter = new Hunter(hunterPosition);
		when(board.getHunter()).thenReturn(hunter);
		when(board.getStartPosition()).thenReturn(Position.of(0, 0));
	}

	@Test
	public void testCheckHunterCanExit_HunterNotInInitialPosition() {
		final Position hunterPosition = new Position(2,3); 
		final Hunter hunter = new Hunter(hunterPosition);
		when(board.getHunter()).thenReturn(hunter);
		when(board.getStartPosition()).thenReturn(Position.of(0, 0));
	}

	
	@Test
	public void testIsGoldCaughtWhenGoldCaught() {
		final Gold gold = new Gold();
		gold.setCaught(true);
		when(board.getGold()).thenReturn(gold);
		assertTrue("AssertionError", boardService.isGoldAlreadyCaught());
	}

	@Test
	public void testIsGoldCaughtWhenGoldNotCaught() {
		final Gold gold = new Gold();
		gold.setCaught(false);
		when(board.getGold()).thenReturn(gold);
		assertFalse("AssertionError", boardService.isGoldAlreadyCaught());
	}
	
	@Test
	public void testGetBoardBounds() {
		final int size = 7;
		final Position startPosition = new Position(0,0);
		final Position endPosition = new Position(size, size);
		final Position[] expectedBounds = new Position[] {startPosition, endPosition};
		
		when(board.getStartPosition()).thenReturn(startPosition);
		when(board.getSize()).thenReturn(size);
		
		
		final Position[] boardBounds = boardService.getBoardBounds();
		
		assertArrayEquals(expectedBounds, boardBounds);
	}
	
	@Test 
	public void checkGoldFoundNotCaught() {
		final Position position = new Position(3,4);
		final Gold gold = new Gold();
		gold.setPosition(position);
		gold.setCaught(false);
		final Hunter hunter = new Hunter(position);
		
		when(board.getGold()).thenReturn(gold);
		when(board.getHunter()).thenReturn(hunter);
		
		assertTrue(boardService.checkGoldFound());	
	}
	
	@Test
	public void checkGoldNotFoundNotCaught() {		
		final Gold gold = new Gold(Position.of(3, 4));
		gold.setCaught(false);
		final Hunter hunter = new Hunter(Position.of(4, 4));
		
		when(board.getGold()).thenReturn(gold);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkGoldFound());	
	}
	
	@Test
	public void checkGoldFoundAlreadyCaught() {
		final Position position = new Position(3,4);
		final Gold gold = new Gold();
		gold.setPosition(position);
		gold.setCaught(true);
		final Hunter hunter = new Hunter(position);
		hunter.setPosition(position);
		
		when(board.getGold()).thenReturn(gold);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkGoldFound());
	}
	
	@Test
	public void checkGoldNotFoundAlreadyCaught() {
		
		final Gold gold = new Gold(Position.of(3, 4));
		gold.setCaught(true);
		final Hunter hunter = new Hunter(Position.of(4, 4));
		
		
		when(board.getGold()).thenReturn(gold);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkGoldFound());			
	}

	@Test
	public void checkPitFallEmptyPitsList() {
		final List<Pit> pits = Collections.emptyList();
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkPitFall());
	}
	
	@Test
	public void checkPitFallHunterNotInAnyPit() {
		final Pit pit1 = new Pit(Position.of(1, 2));
		final Pit pit2 = new Pit(Position.of(2, 3));
		final Pit pit3 = new Pit(Position.of(0, 1));
		final List<Pit> pits = Arrays.asList(pit1,pit2,pit3);
		
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkPitFall());
	}
	
	@Test
	public void checkPitFallHunterInAPit() {
		final Pit pit1 = new Pit(Position.of(1, 2));
		final Pit pit2 = new Pit(Position.of(2, 3));
		final Pit pit3 = new Pit(Position.of(0, 1));
		final List<Pit> pits = Arrays.asList(pit1,pit2,pit3);
		
		final Hunter hunter = new Hunter(Position.of(1, 2));
		
		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);
		
		assertTrue(boardService.checkPitFall());
	}
 
	@Test
	public void checkWumpusKillsHunterNotInTheSameSquare() {
		final Wumpus wumpus = new Wumpus(Position.of(2, 2));
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkWumpusKillsHunter());
	}
	
	@Test
	public void checkWumpusKillsHunterInTheSameSquare_WumpusAlreadyDead() {
		final Wumpus wumpus = new Wumpus(Position.of(2, 2));
		wumpus.setStatus(LivingActorStatus.DEAD);
		final Hunter hunter = new Hunter(Position.of(2, 2));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.checkWumpusKillsHunter());
	}
	
	@Test
	public void checkWumpusKillsHunterInTheSameSquare_WumpusNotDead() {
		final Wumpus wumpus = new Wumpus(Position.of(2, 2));
		final Hunter hunter = new Hunter(Position.of(2, 2));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertTrue(boardService.checkWumpusKillsHunter());
	}
	
	@Test
	public void isWumpusNearHunter_WumpusNotNearHunter() {
		final Wumpus wumpus = new Wumpus(Position.of(2, 3));
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.isWumpusNearHunter());
	}
	
	@Test
	public void isWumpusNearHunter_WumpusNearHunter() {
		final Wumpus wumpus = new Wumpus(Position.of(1, 0));
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertTrue(boardService.isWumpusNearHunter());		
	}
	
	@Test
	public void isGoldNearHunter_GoldNotNearHunter() {
		final Wumpus wumpus = new Wumpus(Position.of(2, 3));
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getHunter()).thenReturn(hunter);
		
		assertFalse(boardService.isWumpusNearHunter());
	}
	
	@Test
	public void isGoldNearHunter_GoldNearHunter() {
		final Gold gold = new Gold(Position.of(1, 0));
		final Hunter hunter = new Hunter(Position.of(0, 0));
		
		when(board.getGold()).thenReturn(gold);
		when(board.getHunter()).thenReturn(hunter);
		
		assertTrue(boardService.isGoldNearHunter());		
	}
	
	@Test
	public void isThereAPitNearHunter_NoPitsAtAll() {
		final List<Pit> pits = Collections.emptyList();
		final Hunter hunter = new Hunter(Position.of(0,0));

		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);

		assertFalse(boardService.isThereAPitNearHunter());
	}
	
	
	@Test
	public void isThereAPitNearHunter_NoPitsNearHunter() {
		final Pit pit1 = new Pit(Position.of(4, 5));
		final Pit pit2 = new Pit(Position.of(4, 4));
		final Pit pit3 = new Pit(Position.of(7, 10));
		final List<Pit> pits = Arrays.asList(pit1, pit2, pit3);
		final Hunter hunter = new Hunter(Position.of(1, 2));
		
		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);

		assertFalse(boardService.isThereAPitNearHunter());
	}
	
	@Test
	public void isThereAPitNearHunter_PitsNearHunter() {
		final Pit pit1 = new Pit(Position.of(4, 5));
		final Pit pit2 = new Pit(Position.of(4, 4));
		final Pit pit3 = new Pit(Position.of(7, 10));
		final List<Pit> pits = Arrays.asList(pit1, pit2, pit3);
		final Hunter hunter = new Hunter(Position.of(3, 3));
		
		when(board.getPits()).thenReturn(pits);
		when(board.getHunter()).thenReturn(hunter);

		assertTrue(boardService.isThereAPitNearHunter());
	}
	
	
	@Test
	public void checkArrowReachesWumpus_WumpusNotInArrowWay() {
		final Arrow arrow = new Arrow(Position.of(0, 0));
		arrow.setDirection(Directions.RIGHT);
		final Wumpus wumpus = new Wumpus(Position.of(3, 2));
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getStartPosition()).thenReturn(Position.of(0, 0));
		when(board.getSize()).thenReturn(7);
		assertFalse(boardService.checkArrowKillsWumpus(arrow));
	}
	
	@Test
	public void checkArrowReachesWumpus_WumpusInArrowWay_butAlreadyKilled() {
		final Arrow arrow = new Arrow(Position.of(1, 1));
		arrow.setDirection(Directions.RIGHT);
		final Wumpus wumpus = new Wumpus(Position.of(7, 1));
		wumpus.setStatus(LivingActorStatus.DEAD);
		
		when(board.getWumpus()).thenReturn(wumpus);
		when(board.getStartPosition()).thenReturn(Position.of(0, 0));
		when(board.getSize()).thenReturn(7);
		assertFalse(boardService.checkArrowKillsWumpus(arrow));
	}
	
	@Test
	public void checkArrowReachesWumpus_WumpusInArrowWay_wumpusAlive() {
		
		when(board.getStartPosition()).thenReturn(Position.of(0, 0));
		when(board.getSize()).thenReturn(7);
		
		// Direction: RIGHT
		final Arrow arrow = new Arrow(Position.of(1, 1));
		arrow.setDirection(Directions.RIGHT);
		final Wumpus wumpus = new Wumpus(Position.of(7, 1));

		
		when(board.getWumpus()).thenReturn(wumpus);
		assertTrue(boardService.checkArrowKillsWumpus(arrow));
		
		// Direction: DOWN
		arrow.setDirection(Directions.DOWN);
		arrow.setPosition(Position.of(1, 1));
		wumpus.setPosition(Position.of(1, 6));
		wumpus.setStatus(LivingActorStatus.ALIVE);
		when(board.getWumpus()).thenReturn(wumpus);

		assertTrue(boardService.checkArrowKillsWumpus(arrow));
		
		// Direction: LEFT
		arrow.setDirection(Directions.LEFT);
		arrow.setPosition(Position.of(1, 1));
		wumpus.setPosition(Position.of(0, 1));
		wumpus.setStatus(LivingActorStatus.ALIVE);
		when(board.getWumpus()).thenReturn(wumpus);
		assertTrue(boardService.checkArrowKillsWumpus(arrow));
		
		// Direction: UP
		arrow.setDirection(Directions.UP);
		arrow.setPosition(Position.of(1, 1));
		wumpus.setPosition(Position.of(1, 0));
		wumpus.setStatus(LivingActorStatus.ALIVE);
		when(board.getWumpus()).thenReturn(wumpus);
		assertTrue(boardService.checkArrowKillsWumpus(arrow));
	}
}
