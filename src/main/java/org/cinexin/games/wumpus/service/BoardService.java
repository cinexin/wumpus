/**
 * 
 */
package org.cinexin.games.wumpus.service;

import java.util.List;

import org.cinexin.games.wumpus.constants.LivingActorStatus;
import org.cinexin.games.wumpus.exception.InvalidHunterActionException;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.Gold;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Pit;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Wumpus;
import org.cinexin.games.wumpus.util.PositionHelper;

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
	public boolean isGoldAlreadyCaught() {
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
			if (PositionHelper.areActorsAtSameSquare(gold, hunter) ) {
				gold.setCaught(true);
				hunter.setOwnsTheGold(true);
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
					if (PositionHelper.areActorsAtSameSquare(pit, hunter)) {
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
		
		if (PositionHelper.areActorsAtSameSquare(hunter, wumpus)) {
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
	
	
	/**
	 * Checks if wumpus is near the hunter
	 * 
	 * @return true if wumpus and hunter are at adjacent squares
	 */
	public boolean isWumpusNearHunter() {
		final Wumpus wumpus = board.getWumpus();
		final Hunter hunter = board.getHunter();
		
		if (PositionHelper.areActorsNear(wumpus, hunter)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if there's a pit near the hunter
	 * 
	 * @return true if there's a pit in the board near the hunter, false otherwise
	 */
	public boolean isThereAPitNearHunter() {
		final List<Pit> pits = board.getPits();
		final Hunter hunter = board.getHunter();
		
		for (Pit pit: pits) {
			if (PositionHelper.areActorsNear(pit, hunter)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the gold is near the hunter
	 * 
	 * @return true if there's a pit in the board near the hunter, false otherwise
	 */
	public boolean isGoldNearHunter() {
		final Gold gold = board.getGold();
		final Hunter hunter = board.getHunter();
		
		if (PositionHelper.areActorsNear(hunter, gold)) {
			return true;
		} else {
			return false;
		}
	}

}
