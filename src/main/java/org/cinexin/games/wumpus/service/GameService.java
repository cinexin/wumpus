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
import org.cinexin.games.wumpus.model.Arrow;
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
	private WumpusService wumpusService;
	private BoardService boardService;
	
	/**
	 * Constructor
	 * @param game the {@link Game} instance to set
	 * @param gameService instance of {@link GameService}
	 */
	public GameService(final Game game) {
		this.game = game;
		this.hunterService = new HunterService(game.getBoard().getHunter());
		this.wumpusService = new WumpusService(game.getBoard().getWumpus());
		this.boardService = new BoardService(game.getBoard());
	}

	
	/**
	 * @return the hunterService
	 */
	public HunterService getHunterService() {
		return hunterService;
	}


	/**
	 * @param hunterService the hunterService to set
	 */
	public void setHunterService(HunterService hunterService) {
		this.hunterService = hunterService;
	}


	/**
	 * @return the wumpusService
	 */
	public WumpusService getWumpusService() {
		return wumpusService;
	}


	/**
	 * @param wumpusService the wumpusService to set
	 */
	public void setWumpusService(WumpusService wumpusService) {
		this.wumpusService = wumpusService;
	}


	/**
	 * @return the boardService
	 */
	public BoardService getBoardService() {
		return boardService;
	}


	/**
	 * @param boardService the boardService to set
	 */
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}


	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
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
	public void manageHunterAction(final HunterActions action) 
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
			if (boardService.isWumpusNearHunter()) {
				System.out.println("Shhhhh...what's that?? puajjj...you feel a disgusting hedor...Be careful! Wumpus is near!!!");
			}
			if (boardService.isThereAPitNearHunter()) {
				System.out.println("Shhhh...what's that??? mmmm...you feel a light breeze coming from near...Be careful!! A Pit Hole is near!!!!");
			}
			if (boardService.isGoldNearHunter()) {
				System.out.println("Shhhhh...what's that??? Such a beautiful brightness coming from somewhere!! Gold should be near here...");
			}
			break;
		
		case ROTATE:
			hunterService.rotate();
			break;	
		
		case THROW_ARROW:
			Arrow extractedArrow;
			try {
				extractedArrow = hunterService.extractArrow();
			} catch(QuiverEmptyException e) {
				System.out.println("Your quiver is empty");
				break;
			}
			if (boardService.checkArrowKillsWumpus(extractedArrow)) {
				wumpusService.die();
				System.out.println("Waaaawwwww...You hear a terrible scream from the dungeon darkness...Congratulations!! You've killed the Wumpus!!!");				
			} else {
				System.out.println("The arrow disappears in the darkness. Sorry, Wumpus has not been reached by the shot");
			}
			break;	
		
		default:
			throw new InvalidHunterActionException("Action not contemplated: " + action.toString());
		}
		
		nextRound();
	}
}
