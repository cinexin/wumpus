/**
 * 
 */
package org.cinexin.games.wumpus.service;

import org.cinexin.games.wumpus.constants.Directions;
import org.cinexin.games.wumpus.exception.QuiverEmptyException;
import org.cinexin.games.wumpus.exception.WallReachedException;
import org.cinexin.games.wumpus.model.Hunter;
import org.cinexin.games.wumpus.model.Position;

/**
 * @author migui
 * Service for managing {@link Hunter}
 *
 */
public class HunterService {

	private Hunter hunter;
	
	/**
	 * Constructor
	 * @param hunter instance of {@link Hunter} to manage
	 */
	public HunterService(final Hunter hunter) {
		this.hunter = hunter;
	}

	/**
	 * This method withdraws the number of arrows (if left) when hunter throws an arrow
	 * 
	 * @return new number of arrows left for hunter
	 * @throws QuiverEmptyException if hunter has no arrows left
	 */
	public int extractArrow() throws QuiverEmptyException {
		final int initialNumberOfArrows = hunter.getNumOfArrows();
		
		if (initialNumberOfArrows > 0) {
			hunter.setNumOfArrows(initialNumberOfArrows-1);
		}
		else {
			throw new QuiverEmptyException("No arrows left!");
		}
		return hunter.getNumOfArrows();
	}
	
	/**
	 * This method rotates the hunter 90º clockwise
	 * 
	 * @return new direction to which hunter is heading now
	 */
	public Directions rotate() {
		final Directions hunterInitialDirection = hunter.getDirection();
		final Directions newDirection = calculateNewDirection(hunterInitialDirection);
		hunter.setDirection(newDirection);
		return newDirection;
	}
	
	/**
	 * This method movesForward the hunter in the direction it's aiming to
	 * 
	 * @param bounds: bounds[0] = upper bound (typically: (0,0)), bounds[1] = bottom bound (ie: (8,8))
	 * 	note that in the first version of the game, the board is square
	 * @return new Position of the hunter
	 * @throws WallReachedException if hunter has reached a wall
	 */
	public Position moveForward(Position[] bounds) throws WallReachedException {
		final Position initialPosition = hunter.getPosition();
		final Directions hunterDirection = hunter.getDirection();		
		final Position newPosition = calculateNewPosition(initialPosition, hunterDirection, bounds);
		hunter.setPosition(newPosition);
		
		return newPosition;
	}
	
	
	/**
	 * This method calculates the new position of hunter (+1 coordinate to given direction)
	 * 
	 * @param initialPosition start position
	 * @param hunterDirection direction to move forward
	 * @param bounds[]: bounds[0] = upper bound (typically: (0,0)), bounds[1] = bottom bound (ie: (8,8))
	 * 	note that in the first version of the game, the board is square
	 * @return new position end position
	 * 
	 * @throws WallReachedException if hunter reaches any wall
	 */
	private Position 
	calculateNewPosition(final Position initialPosition, final Directions hunterDirection, final Position[] bounds) 
			throws WallReachedException {
		
		Position finalPosition = new Position(initialPosition.getX(), initialPosition.getY());
		final Position upperBound = bounds[0];
		final Position bottomBound = bounds[1];
		
		switch (hunterDirection) {
		case UP:
			if (initialPosition.getY() == upperBound.getY()) {
				throw new WallReachedException("There's a wall in the UP direction from you");
			} else {
				finalPosition.setY(initialPosition.getY() - 1);
			}
			break;
			
		case DOWN:
			if (initialPosition.getY() == bottomBound.getY()) {
				throw new WallReachedException("There's a wall in the DOWN direction from you");
			} else {
				finalPosition.setY(initialPosition.getY() + 1);
			}
			break;
			
		case LEFT:
			if (initialPosition.getX() == upperBound.getX()) {
				throw new WallReachedException("There's a wall in the LEFT direction from you");
			} else {
				finalPosition.setX(initialPosition.getX() - 1);
			}
			break;
			
		case RIGHT:
			if (initialPosition.getX() == bottomBound.getX()) {
				throw new WallReachedException("There's a wall in the RIGHT direction from you");
			} else {
				finalPosition.setX(initialPosition.getX() + 1);
			}
			break;
			
		default:
			break;
		}

		return finalPosition;
	}
	
	/**
	 * This method calculates the new direction after rotating 90º clockwise
	 * 
	 * @param initialDirection initial direction
	 * @param direction direction of rotation
	 * 
	 * @return direction after the rotation
	 */
	private Directions calculateNewDirection(final Directions initialDirection) {
		Directions newDirection = initialDirection;

		switch(initialDirection) {
		case DOWN:
			newDirection = Directions.LEFT;
			break;
		case LEFT:
			newDirection = Directions.UP;
			break;
		case RIGHT:
			newDirection = Directions.DOWN;
			break;
		case UP:
			newDirection = Directions.RIGHT;
			break;
		default:
			break;
		
		}
		
		return newDirection;
	}
}
