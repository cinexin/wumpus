/**
 * 
 */
package org.cinexin.games.wumpus.util;

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
	public static boolean areAdjacent(final Position pointA, final Position pointB) {
		
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

}
