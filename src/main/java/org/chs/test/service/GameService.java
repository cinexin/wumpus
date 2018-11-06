/**
 * 
 */
package org.chs.test.service;

import org.chs.test.constants.GameStatus;
import org.chs.test.exception.GameOverException;
import org.chs.test.model.Game;

/**
 * @author migui
 * Main service for Game
 * It manages the game main events
 *
 */
public class GameService {

	private Game game;
	
	/**
	 * Constructor
	 * @param game the Game instance to set
	 */
	public GameService(Game game) {
		this.game = game;
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
		case HUNTER_WAS_KILLED:
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
}
