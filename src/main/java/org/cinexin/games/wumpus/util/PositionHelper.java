/**
 * 
 */
package org.cinexin.games.wumpus.util;

import org.cinexin.games.wumpus.model.Actor;
import org.cinexin.games.wumpus.model.Position;

/**
 * @author migui
 *
 * Helper class for {@link Position} util methods
 */
public class PositionHelper {

	/**
	 * Check if 2 positions in the board are adjacent 
	 * 
	 * @param pointA
	 * @param pointB
	 * @return true if they're adjacent, false if not
	 */
	public static boolean arePositionsAdjacent(final Position pointA, final Position pointB) {
		
		if (pointA.getX() == pointB.getX() - 1 && 
				pointA.getY() == pointB.getY() - 1 ) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() &&
				pointA.getY() == pointB.getY() -1 ) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() + 1 &&
				pointA.getY() == pointB.getY() -1 ) {
			return true;
		}

		if (pointA.getX() == pointB.getX() - 1 &&
				pointA.getY() == pointB.getY()) {
			return true;
		}

		/* let's say if positions are the same, positions are adjacent...*/
		if (pointA.getX() == pointB.getX() &&
				pointA.getY() == pointB.getY()) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() + 1 && 
			pointA.getY() == pointB.getY()) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() - 1 &&
				pointA.getY() == pointB.getY() + 1) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() &&
				pointA.getY() == pointB.getY() + 1) {
			return true;
		}
		
		if (pointA.getX() == pointB.getX() + 1 &&
				pointA.getY() == pointB.getX() + 1) {
			return true;
		}
		return false;
	}

	public static boolean areActorsNear(Actor actorA, Actor actorB) {
		if (arePositionsAdjacent(actorA.getPosition(), actorB.getPosition())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean areActorsAtSameSquare(Actor actorA, Actor actorB) {
		if (actorA.getPosition().equals(actorB.getPosition())) {
			return true;
		} else {
			return false;
		}
	}
}
