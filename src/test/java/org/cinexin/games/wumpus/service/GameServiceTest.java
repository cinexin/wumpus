package org.cinexin.games.wumpus.service;

import static org.mockito.Mockito.*;

import org.cinexin.games.wumpus.constants.GameStatus;
import org.cinexin.games.wumpus.exception.GameOverException;
import org.cinexin.games.wumpus.model.Game;
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
		hunterService = Mockito.mock(HunterService.class);
		boardService = Mockito.mock(BoardService.class);
		wumpusService = Mockito.mock(WumpusService.class);
		gameService = new GameService(game, hunterService, wumpusService, boardService);
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
}
