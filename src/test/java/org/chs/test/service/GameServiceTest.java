package org.chs.test.service;

import static org.mockito.Mockito.*;

import org.chs.test.constants.GameStatus;
import org.chs.test.exception.GameOverException;
import org.chs.test.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameServiceTest {

	GameService gameService;
	Game game;
	
	@Before
	public void setUp() {
		game = Mockito.mock(Game.class);
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
		when(game.getGameStatus()).thenReturn(GameStatus.HUNTER_WAS_KILLED);
		gameService.nextRound();
	}
}
