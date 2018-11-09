/**
 * 
 */
package org.cinexin.games.wumpus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cinexin.games.wumpus.exception.InvalidGameParamsException;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.Game;
import org.cinexin.games.wumpus.model.GameParams;
import org.cinexin.games.wumpus.service.GameService;
import org.cinexin.games.wumpus.util.GameInitializationUtils;

/**
 * @author migui
 *
 */
public class WumpusGameMain {


	public static void showHelp(final HelpFormatter helpFormatter, final Options options) {
		helpFormatter.printHelp("Wumpus game", options);
	}
	
	/**
	 * @param args
	 * -b board size
	 * -p number of pits
	 * -a number of arrows
	 */
	public static void main(String[] args) {
		// create the command line parser
		final CommandLineParser parser = new DefaultParser();
		final Options options = new Options();		
		options.addOption("b", "board-size", true, "Size of the board (board will be sizexsize")
			.addOption("p", "pits", true, "Number of pits")
			.addOption("a","arrows", true, "Number of arrows hunter will have");
		
		final HelpFormatter formatter = new HelpFormatter();
		
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			formatter.printHelp("Wumpus Game", options);
			e.printStackTrace();
		}
		
		boolean parserErrors = false;
		int boardSize = 0;
		try {
			boardSize = Integer.parseInt(cmd.getOptionValue("b"));
		} catch(NumberFormatException ne) {
			parserErrors = true;
			System.err.println("[ERROR] Board size must be a numeric value");
		}
	
		int numOfPits = 0;
		try {
			numOfPits = Integer.parseInt(cmd.getOptionValue("p"));
		} catch(NumberFormatException ne) {
			parserErrors = true;
			System.err.println("[ERROR] Number of pits must be a numeric value");
		}


		int numOfArrows = 0;
		try {
			numOfArrows = Integer.parseInt(cmd.getOptionValue("a"));
		} catch(NumberFormatException ne) {
			parserErrors = true;
			System.err.println("[ERROR] Number of arrows must be a numeric value");
		}
		
		if (parserErrors) {
			showHelp(formatter, options);
			System.exit(0);
		}
		
		final GameParams gameParams = new GameParams(boardSize, numOfPits, numOfArrows);
		final GameInitializationUtils gameInitUtils = new GameInitializationUtils();
		
		/* Mount a new board given input params */ 
		Board board = null;
		try {
			board = gameInitUtils.initializeBoard(gameParams);
		} catch (InvalidGameParamsException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		/* Start the game... */
		if (board != null) {
			/* Instantiate a new game */
			final Game game = new Game(board);
			
			// final GameService gameService = new GameService(game, hunterService, wumpusService, boardService);
			
		}
	}

}
