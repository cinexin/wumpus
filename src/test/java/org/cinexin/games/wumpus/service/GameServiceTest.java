package org.cinexin.games.wumpus.service;

import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.cinexin.games.wumpus.constants.GameStatus;
import org.cinexin.games.wumpus.constants.HunterActions;
import org.cinexin.games.wumpus.exception.GameOverException;
import org.cinexin.games.wumpus.exception.InvalidHunterActionException;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.Game;
import org.cinexin.games.wumpus.model.Gold;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Pit;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Wumpus;
import org.cinexin.games.wumpus.service.BoardService;
import org.cinexin.games.wumpus.service.GameService;
import org.cinexin.games.wumpus.service.HunterService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameServiceTest {

	GameService gameService;
	HunterService hunterService;
	BoardService boardService;
	WumpusService wumpusService;
	Game game;
	
	@Before
	public void setUp() {
		game = Mockito.mock(Game.class);
		Gold gold = new Gold(Position.of(0, 2));
		Wumpus wumpus = new Wumpus(Position.of(1, 0));
		Hunter hunter = new Hunter(Position.of(0, 0));
		List<Pit> pits = Collections.emptyList();
		Board board = new Board(8, gold, wumpus, hunter, pits);
		
		when(game.getBoard()).thenReturn(board);
		
		gameService = new GameService(game);
	}
	
	@Test
	public void testNextRoundWhenGameJustStarted() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.START);
		gameService.nextRound();
	}

	@Test
	public void testNextRoundWhenGameIsInProgress() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.IN_PROGRESS);
		gameService.nextRound();
	}

	@Test(expected = GameOverException.class)
	public void testNextRoundWhenHunterExitedWithoutGold() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_EXITED_WITHOUT_GOLD);
		gameService.nextRound();
	}
	
	@Test(expected = GameOverException.class)
	public void testNextRoundWhenHunterExitedWithGold() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_EXITED_WITH_GOLD);
		gameService.nextRound();
	}
	
	@Test(expected = GameOverException.class)
	public void testNextRoundWhenHunterFell() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_FELL);
		gameService.nextRound();		
	}
	
	@Test(expected = GameOverException.class)
	public void testNextRoundWhenHunterWasKilled() throws GameOverException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_KILLED);
		gameService.nextRound();
	}
	
	@Test(expected = GameOverException.class)
	public void testManageHunterAction_exitWithGold() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_EXITED_WITH_GOLD);
		gameService.manageHunterAction(HunterActions.EXIT);
	}
	
	@Test(expected = GameOverException.class)
	public void testManageHunterAction_exitWithoutGold() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_EXITED_WITHOUT_GOLD);
		gameService.manageHunterAction(HunterActions.EXIT);
	}
	
	@Test
	public void testManageHunterAction_moveForward_noInteractions() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.IN_PROGRESS);
		gameService.manageHunterAction(HunterActions.MOVE_FORWARD);
	}
	
	@Test (expected = GameOverException.class)
	public void testManageHunterAction_moveForward_hunterFalls() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_FELL);
		gameService.manageHunterAction(HunterActions.MOVE_FORWARD);
	}
	
	@Test (expected = GameOverException.class)
	public void testManageHunterAction_moveForward_hunterIsKilled() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_KILLED);
		gameService.manageHunterAction(HunterActions.MOVE_FORWARD);
	}
	
	@Test
	public void testManageHunterAction_rotate() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.IN_PROGRESS);
		gameService.manageHunterAction(HunterActions.ROTATE);
	}
	
	@Test
	public void testManageHunterAction_throwArrow() throws GameOverException, InvalidHunterActionException {
		when(game.getGameStatus()).thenReturn(GameStatus.IN_PROGRESS);
		gameService.manageHunterAction(HunterActions.THROW_ARROW);
	}
}
