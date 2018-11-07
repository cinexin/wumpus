/**
 * 
 */
package org.chs.test.service;

import java.util.List;

import org.chs.test.constants.LivingActorStatus;
import org.chs.test.exception.InvalidHunterActionException;
import org.chs.test.model.Board;
import org.chs.test.model.Gold;
import org.chs.test.model.Hunter;
import org.chs.test.model.Pit;
import org.chs.test.model.Position;
import org.chs.test.model.Wumpus;

/**
 * @author migui
 *
 * Service with business logic for {@link Board}
 */
public class BoardService {

	private Board board;
	
	/**
	 * Constructor
	 * @param board instance of {@link Board}
	 */
	public BoardService(final Board board) {
		this.board = board;
	}

	/**
	 * Checks if hunter is in start position (can exit) 
	 * if not, it throws InvalidActionException...
	 * 
	 * @throws InvalidHunterActionException
	 */
	public void checkHunterCanExit() throws InvalidHunterActionException {
		final Hunter hunter = board.getHunter();
		if (!hunter.getPosition().equals(board.getStartPosition())) {
			final String errMsg = "Sorry mate, there's no exit in here. Hunter is not at start position: " + board.getStartPosition();
			throw new InvalidHunterActionException(errMsg);
		}
	}
	
	/**
	 * Returns true if hunter got the gold, false if not
	 */
	public boolean isGoldCaught() {
		final Gold gold = board.getGold();
		if (gold != null) {
			return gold.isCaught();
		}
		return false;
	}
	
	/**
	 * Get the upper and bottom bounds of the board ie: (0,0) to (7,7)
	 * being "7" the board size
	 * 
	 * @return an array containing the initial position v[0] and the final position v[1]
	 */
	public Position[] getBoardBounds() {
		final Position[] bounds = new Position[2];
		bounds[0] = board.getStartPosition();
		bounds[1] = new Position(board.getSize(), board.getSize());		
		return bounds;
	}
	
	/**
	 * Checks if {@link Hunter} has found the {@link Gold}
	 * If gold is found, it automatically sets the "caught" flag of {@link Gold} to "true"
	 * If gold is already caught, it returns false
	 * 
	 * @return true if {@link Hunter} and {@link Gold} are at the same square,
	 * and {@link Gold} is still not caught
	 * false otherwise 
	 */
	public boolean checkGoldFound() {
		final Gold gold = board.getGold();
		final Hunter hunter = board.getHunter();
		

		if (gold != null && !gold.isCaught()) {
			if (hunter.getPosition().equals(gold.getPosition()) ) {
				gold.setCaught(true);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Check if {@link Hunter} is in the same square as some {@link Pit}
	 * 
	 * @return true if {@link Hunter} falls into a {@link Pit} of the list, false otherwise  
	 */
	public boolean checkPitFall() {
		final List<Pit> pits = board.getPits();
		final Hunter hunter = board.getHunter();
		
		if (pits != null) {
			for (Pit pit:pits) {
				if (pit != null) {
					if (pit.getPosition().equals(hunter.getPosition())) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Check if {@link Hunter} is killed by {@link Wumpus} (true) or not (false) 
	 * 
	 * @return 
	 * true if {@link Wumpus} and {@link Hunter} are at the same square, and {@link Wumpus} is still alive
	 * false otherwise
	 * 
	 */
	public boolean checkWumpusKillsHunter() {
		final Hunter hunter = board.getHunter();
		final Wumpus wumpus = board.getWumpus();
		
		if (hunter.getPosition().equals(wumpus.getPosition())) {
			if (wumpus.getStatus().equals(LivingActorStatus.ALIVE)) {
				hunter.setStatus(LivingActorStatus.DEAD);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
