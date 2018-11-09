/**
 * 
 */
package org.cinexin.games.wumpus.ui;

import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.service.GameService;

/**
 * @author migui
 *
 */
public class CommandLineUI {

	GameService gameService;
	
	public CommandLineUI(final GameService gameService) {
		this.gameService = gameService;
	}

	public void welcome() {
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("<<<<<<<<<<<<<<<<<<< WELCOME TO WUMPUS DUNGEON >>>>>>>>>>>>>>>>>>>");
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println();
		showGameStatus();
	}
	
	public void showGameStatus() {
		System.out.println("------------------------------------------");
		System.out.println("ROUND: " + gameService.getRound());
		System.out.println("GAME STATUS: " + gameService.getGame().getGameStatus());
		final Board board = gameService.getGame().getBoard();
		System.out.println("HUNTER POSITION: " + board.getHunter().getPosition());
		System.out.println("ARROWS LEFT: " + board.getHunter().getQuiver().getArrows().size());
		System.out.println("IS GOLD CAUGHT? " + board.getGold().isCaught());
		System.out.println("IS WUMPUS ALIVE? " + board.getWumpus().getStatus());
		System.out.println("------------------------------------------");
	}
	
	public void showMenu() {
		
	}
}
