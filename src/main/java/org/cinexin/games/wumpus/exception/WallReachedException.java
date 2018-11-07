/**
 * 
 */
package org.cinexin.games.wumpus.exception;

/**
 * @author migui
 * 
 * This exception is thrown when actor (hunter) tries to move forward to a wall
 *
 */
public class WallReachedException extends Exception {

	/**
	 * 
	 */
	public WallReachedException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public WallReachedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public WallReachedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WallReachedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public WallReachedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
