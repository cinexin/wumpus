package org.cinexin.games.wumpus.util;

import static org.junit.Assert.*;

import org.cinexin.games.wumpus.exception.InvalidGameParamsException;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.GameParams;
import org.junit.Before;
import org.junit.Test;

public class GameInitializationUtilsTest {

	GameInitializationUtils gameInitUtils;
	
	@Before
	public void setUp() throws Exception {
		gameInitUtils = new GameInitializationUtils();
	}

	@Test (expected = InvalidGameParamsException.class)
	public void testInitializeBoardWithInvalidInputParams() throws InvalidGameParamsException {
		final GameParams gameParams = new GameParams(2, 5, -5);
		gameInitUtils.initializeBoard(gameParams);
	}

	@Test(expected = InvalidGameParamsException.class)
	public void testValidateInputParametersBoardSizeTooSmall() throws InvalidGameParamsException{
		final GameParams gameParams = new GameParams(2, 3, 5);
		gameInitUtils.validateInputParameters(gameParams);
	}

	@Test(expected = InvalidGameParamsException.class)
	public void testValidateInputParametersBoardTooManyPitsForBoardSize() throws InvalidGameParamsException{
		final GameParams gameParams = new GameParams(3, 6, 5);
		gameInitUtils.validateInputParameters(gameParams);
	}
	
	@Test
	public void testInitializeSmallSizeBoard() throws InvalidGameParamsException {
		final GameParams gameParams = new GameParams(5, 5, -5);
		final Board board = gameInitUtils.initializeBoard(gameParams);
		System.out.println("================ BOARD ================ ");
		System.out.println(board);
	}
	
	@Test
	public void testInitializeMediumSizeBoard() throws InvalidGameParamsException {
		final GameParams gameParams = new GameParams(15, 20, 15);
		final Board board = gameInitUtils.initializeBoard(gameParams);
		System.out.println("================ BOARD ================ ");
		System.out.println(board);
	}
	
	@Test
	public void testInitializeLargeSizeBoard() throws InvalidGameParamsException {
		final GameParams gameParams = new GameParams(30, 50, 80);
		final Board board = gameInitUtils.initializeBoard(gameParams);
		System.out.println("================ BOARD ================ ");
		System.out.println(board);
	}
}
