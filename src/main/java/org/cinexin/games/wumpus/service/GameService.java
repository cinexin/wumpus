/**
 * 
 */
package org.cinexin.games.wumpus.service;

import org.cinexin.games.wumpus.constants.GameStatus;
import org.cinexin.games.wumpus.constants.HunterActions;
import org.cinexin.games.wumpus.exception.GameOverException;
import org.cinexin.games.wumpus.exception.InvalidHunterActionException;
import org.cinexin.games.wumpus.exception.QuiverEmptyException;
import org.cinexin.games.wumpus.exception.WallReachedException;
import org.cinexin.games.wumpus.model.Game;


/**
 * @author migui
 * Main service for Game
 * It manages the game main events
 *
 */
public class GameService {

	private Game game;
	private HunterService hunterService;
	private BoardService boardService;
	
	/**
	 * Constructor
	 * @param game the {@link Game} instance to set
	 * @param gameService instance of {@link GameService}
	 */
	public GameService(final Game game, final HunterService hunterService, final BoardService boardService) {
		this.game = game;
		this.hunterService = hunterService;
		this.boardService = boardService;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return game.getRound();
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * This method is in charge of managing the game progress
	 * It increases the round number and control if the game ends
	 * @throws GameOverException if game over is detected
	 */
	public void nextRound() throws GameOverException {
		game.nextRound();
		manageGameStatus();
	}
	
	/**
	 * This method is in charge of controlling the game status and act accordingly
	 * @throws GameOverException if game is over
	 */
	public void manageGameStatus() throws GameOverException {
		final GameStatus status = game.getGameStatus();
		
		switch (status) {
		case HUNTER_EXITED_WITHOUT_GOLD:
			System.out.println("Game Over! You exited the dungeon with live, but without getting the Gold!");
			break;
		case HUNTER_EXITED_WITH_GOLD:
			System.out.println("Game Over! You exited the dungeon with live, and you GOT the GOLD!!!");
			break;
		case HUNTER_FELL:
			System.out.println("Game Over! You fell in a pit");
			break;
		case HUNTER_KILLED:
			System.out.println("Game Over! You were caught and killed");
			break;
		case START:
			game.setGameStatus(GameStatus.IN_PROGRESS);
			break;
		default:
			break;
		}
		
		if (status.isOver()) {
			if (status.hunterWins()) {
				System.out.println("Congratulations!! You WON the game!!!!");
			} else {
				System.out.println("Sorry to say...You LOST the game!");
			}
			throw new GameOverException("Game Over");
		}
	}
	
	/**
	 * 
	 * 
	 * @param action the action (see possible actions in enum {@link HunterActions}
	 * 
	 * @throws {@link GameOverException} if game ends
	 * @throws {@link InvalidHunterActionException} 
	 */
	public void manageHunterActions(final HunterActions action) 
			throws GameOverException, InvalidHunterActionException{
		
		switch (action) {
		case EXIT:
			boardService.checkHunterCanExit();
			if (boardService.isGoldAlreadyCaught()) {
				game.setGameStatus(GameStatus.HUNTER_EXITED_WITH_GOLD);
			} else {
				game.setGameStatus(GameStatus.HUNTER_EXITED_WITHOUT_GOLD);				
			}
			break;
		
		case MOVE_FORWARD:
			try {
				hunterService.moveForward(boardService.getBoardBounds());
			} catch (WallReachedException e) {
				System.out.println("Hunter can't move forward, there's a wall in its direction!");
			}
			/* Instant consequences... */
			if (boardService.checkGoldFound()) {
				System.out.println("Congrats!! You've found the Gold! now return to start position ALIVE!!");
			}
			if (boardService.checkPitFall()) {
				game.setGameStatus(GameStatus.HUNTER_FELL);
				System.out.println("Ohhhh!! You've fallen into an infinite pit! Be careful next time ;-) !!!");
			}
			if (boardService.checkWumpusKillsHunter()) {
				game.setGameStatus(GameStatus.HUNTER_KILLED);
				System.out.println("Ohhhh!! You've been swallowed by the terrifying humpus! Be careful next time ;-) !!!");
			}
			
			/* Perceptions....*/
			// boardService.isWumpusNearHunter();
			// boardService.isThereAPitNearHunter();
			// boardService.isGoldNearHunter();
			break;
		
		case ROTATE:
			hunterService.rotate();
			break;
			
		
		case THROW_ARROW:
			try {
				hunterService.extractArrow();
			} catch(QuiverEmptyException e) {
				System.out.println("Your quiver is empty");
				return;
			}
			// boardService.checkArrowReachesWumpus();
			break;
						
		
		default:
			throw new InvalidHunterActionException("Action not contemplated: " + action.toString());
		}
		
		nextRound();
	}
}
