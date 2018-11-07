/**
 * 
 */
package org.chs.test.constants;

/**
 * @author migui
 * Enum for the status game can have: 
 * 	(game is over?, hunter won?)
 * 
 */
public enum GameStatus {
	
	START (false, false), 
	IN_PROGRESS(false, false),
	HUNTER_FELL(true, false),
	HUNTER_KILLED(true, false),
	HUNTER_EXITED_WITHOUT_GOLD(true, false),
	HUNTER_EXITED_WITH_GOLD(true, true);
	
	private boolean isOver;
	private boolean hunterWins; 
	
	GameStatus(boolean isOver, boolean hunterWins) {
		this.isOver = isOver;
		this.hunterWins = hunterWins;
	}

	/**
	 * @return the isOver
	 */
	public boolean isOver() {
		return isOver;
	}

	/**
	 * @return the hunterWins
	 */
	public boolean hunterWins() {
		return hunterWins;
	}
	
	
}
