/**
 * 
 */
package org.cinexin.games.wumpus.util;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.cinexin.games.wumpus.exception.InvalidGameParamsException;
import org.cinexin.games.wumpus.model.Actor;
import org.cinexin.games.wumpus.model.Board;
import org.cinexin.games.wumpus.model.GameParams;
import org.cinexin.games.wumpus.model.Gold;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Pit;
import org.cinexin.games.wumpus.model.Position;
import org.cinexin.games.wumpus.model.Wumpus;

/**
 * @author migui
 *
 * Util class for initializing a game, a board, and allocate the actors in the board 
 */
public class GameInitializationUtils {

	private ArrayList<Position> availableSpaces ;
	
	/**
	 * no-args constructor
	 */
	public GameInitializationUtils() {
		availableSpaces = new ArrayList<>();
	}

	/**
	 * Validate input game parameters
	 * 
	 * @param gameParams
	 * @throws InvalidGameParamsException
	 */
	public void validateInputParameters(final GameParams gameParams) 
			throws InvalidGameParamsException {
		
		if (gameParams.getBoardSize() < 3) {
			System.err.println("Sorry, board should be at least 3 square-size to allocate all elements");
			throw new InvalidGameParamsException("Board size too low: " + gameParams.getBoardSize());
		}
		/*
		 * check input number of pits (we should be able to allocate at least the other 3 actors in the board)
		 */
		if (gameParams.getNumOfPits() >= (Math.pow(gameParams.getBoardSize(), 2) - 3)) {
			System.err.println("Sorry, you're trying to put too many pits in the board...");
			throw new InvalidGameParamsException("Too many pits (" + gameParams.getNumOfPits() + ") for board size: " + gameParams.getBoardSize());
		}
		
		// set the default number of arrows to zero if user has wrongly entered a negative amount
		if (gameParams.getNumOfArrows() < 0) {
			gameParams.setNumOfArrows(0);
		}
	}
	
	/**
	 * Creates a new {@link Board} and allocates the different {@link Actor}s on it
	 * 
	 * @param boardSize the size of the board (board will be NxN)
	 * 
	 * @return instance of created {@link Board}
	 * @throws InvalidGameParamsException
	 */
	public Board initializeBoard(final GameParams gameParams) throws InvalidGameParamsException {
		
		validateInputParameters(gameParams);
		
		final int boardSize = gameParams.getBoardSize();
		final Position startPosition = Position.of(0, 0);
		
		fillAvailableSpacesArray(startPosition, boardSize);
		for (Position position: availableSpaces) {
			System.out.println("[DEBUG] POSITION: " + position);
		}
		
		
		// hunter starts at initialPosition typically: (0,0)
		final Hunter hunter = new Hunter(startPosition, gameParams.getNumOfArrows());
		
		// Allocate and create the Gold
		Position nextRandomAvailablePosition = allocateElement(startPosition, boardSize);
		final Gold gold = new Gold(nextRandomAvailablePosition);
		
		// Allocate and create the Wumpus
		nextRandomAvailablePosition = allocateElement(startPosition, boardSize);
		final Wumpus wumpus = new Wumpus(nextRandomAvailablePosition);
		
		// Allocate and create the list of Pits
		ArrayList<Pit> pits = new ArrayList<>();
		for (int i = 0; i<gameParams.getNumOfPits(); i++) {
			nextRandomAvailablePosition = allocateElement(startPosition, boardSize);
			Pit pit = new Pit(nextRandomAvailablePosition);
			pits.add(pit);
		}
		
		final Board board = new Board(boardSize, gold, wumpus, hunter, pits);
		return board;
	}
	
	private void fillAvailableSpacesArray(final Position startPosition, final int size) {
		for (int x = startPosition.getX(); x < size; x++) {
			for (int y = startPosition.getY(); y < size; y++) {
				// we skip start position as it's reserved for the hunter
				if ((x != startPosition.getX()) || (y != startPosition.getY())) {
					Position position = Position.of(x, y);
					availableSpaces.add(position);
				}
			}
		}
	}
	
	/**
	 * Randomly allocates  an element in the board
	 * @return the allocated Position
	 */
	private Position allocateElement(final Position startPosition, final int size) throws InvalidGameParamsException{
		Position position = null ;
		
		boolean freeSpace = false;
		while (!freeSpace) {
			int randomX = ThreadLocalRandom.current().nextInt(startPosition.getX(), size);
			int randomY = ThreadLocalRandom.current().nextInt(startPosition.getY(), size);
			position = new Position(randomX, randomY);
			
			if (availableSpaces.remove(position)) {
				freeSpace = true;
			}
		}
		
		if (freeSpace) {
			return position;
		} else {
			throw new InvalidGameParamsException("No squares available in the board to allocate board elements!!");
		}
	}

}
