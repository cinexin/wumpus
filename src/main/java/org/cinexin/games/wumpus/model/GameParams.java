/**
 * 
 */
package org.cinexin.games.wumpus.model;

/**
 * @author migui
 * 
 * Class for modelling game initialization parameters
 */
public class GameParams {

	private int boardSize;
	private int numOfPits;
	private int numOfArrows;
	
	/**
	 * Constructor
	 * 
	 * @param boardSize size of the board
	 * @param numOfPits number of pits to allocate
	 * @param numOfArrows number of arrows to allocate
	 */
	public GameParams(final int boardSize, final int numOfPits, final int numOfArrows) {
		 this.boardSize = boardSize;
		 this.numOfPits = numOfPits;
		 this.numOfArrows = numOfArrows;
	}

	/**
	 * @return the boardSize
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * @param boardSize the boardSize to set
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	/**
	 * @return the numOfPits
	 */
	public int getNumOfPits() {
		return numOfPits;
	}

	/**
	 * @param numOfPits the numOfPits to set
	 */
	public void setNumOfPits(int numOfPits) {
		this.numOfPits = numOfPits;
	}

	/**
	 * @return the numOfArrows
	 */
	public int getNumOfArrows() {
		return numOfArrows;
	}

	/**
	 * @param numOfArrows the numOfArrows to set
	 */
	public void setNumOfArrows(int numOfArrows) {
		this.numOfArrows = numOfArrows;
	}
	
	

}
