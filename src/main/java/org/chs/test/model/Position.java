/**
 * 
 */
package org.chs.test.model;

/**
 * @author migui
 * 
 * X and Y coordinates define the position of an element in the game board
 *
 */
public class Position {

	private int X;
	private int Y;
	
	
	/**
	 * default constructor with no params
	 */
	public Position() {
		
	}

	/**
	 * Constructor with initial coordinates
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Position(final int x, final int y) {
		X = x;
		Y = y;
	}
	public int getX() {
		return X;
	}
	public void setX(final int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(final int y) {
		Y = y;
	}

	/**
	 * Static method to get an instance of position with the given coordinates
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return
	 */
	public static Position of (final int x, final int y) {
		return new Position(x,y);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		return true;
	}
	
	
}
