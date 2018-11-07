/**
 * 
 */
package org.chs.test.exception;

import org.chs.test.constants.HunterActions;
import org.chs.test.model.Hunter;

/**
 * @author migui
 * This custom exception is meant to be raised when {@link Hunter} tries to do an invalid {@link HunterActions}
 * (ie: it tries to exit game in a non-exit position, or it tries to perform an action not in {@link HunterActions})
 */
public class InvalidHunterActionException extends Exception {

	/**
	 * 
	 */
	public InvalidHunterActionException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidHunterActionException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidHunterActionException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvalidHunterActionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InvalidHunterActionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
