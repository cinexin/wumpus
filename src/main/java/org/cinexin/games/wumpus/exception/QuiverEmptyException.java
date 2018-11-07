/**
 * 
 */
package org.cinexin.games.wumpus.exception;

/**
 * @author migui
 * This exception is thrown when hunter's quiver is empty and it tries to throw an arrow
 */
public class QuiverEmptyException extends Exception {

	/**
	 * 
	 */
	public QuiverEmptyException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public QuiverEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public QuiverEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QuiverEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public QuiverEmptyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
