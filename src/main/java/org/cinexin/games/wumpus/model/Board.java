/**
 * 
 */
package org.cinexin.games.wumpus.model;

import java.util.List;

/**
 * @author migui
 * Game Board
 * It has a variable size (N) 
 */
public class Board {

	private int size;
	private Gold gold;
	private Wumpus wumpus;
	private Hunter hunter;
	private List<Pit> pits;
	private Position startPosition = new Position(0,0);
	
	/**
	 * Constructor
	 * @param size size of the board (NxN squares in the board)
	 * @param gold instance of {@link Gold} class model
	 * @param wumpus instance of {@link Wumpus} class model
	 * @param hunter instance of {@link Hunter} class model
	 * @param pits list of {@link Pit} 
	 */
	public Board(final int size, final Gold gold, final Wumpus wumpus, final Hunter hunter, final List<Pit> pits) {
		this.size = size;
		this.gold = gold;
		this.wumpus = wumpus;
		this.hunter = hunter;
		this.pits = pits;
	}

	/**
	 * Constructor overload
	 * Addition param: non-default start position (if you don't want the start position to be (0,0))
	 * @param size size of the board (NxN squares in the board)
	 */
	public Board(final int size, final Gold gold, final Wumpus wumpus, final Hunter hunter, final List<Pit> pits, final Position startPosition) {
		this.size = size;
		this.gold = gold;
		this.wumpus = wumpus;
		this.hunter = hunter;
		this.pits = pits;
		this.startPosition = startPosition;
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the gold
	 */
	public Gold getGold() {
		return gold;
	}

	/**
	 * @param gold the gold to set
	 */
	public void setGold(Gold gold) {
		this.gold = gold;
	}

	/**
	 * @return the hunter
	 */
	public Hunter getHunter() {
		return hunter;
	}

	/**
	 * @param hunter the hunter to set
	 */
	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}

	/**
	 * @return the pits
	 */
	public List<Pit> getPits() {
		return pits;
	}

	/**
	 * @param pits the pits to set
	 */
	public void setPits(List<Pit> pits) {
		this.pits = pits;
	}

	/**
	 * @return the wumpus
	 */
	public Wumpus getWumpus() {
		return wumpus;
	}

	/**
	 * @param wumpus the wumpus to set
	 */
	public void setWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}

	/**
	 * @return the startPosition
	 */
	public Position getStartPosition() {
		return startPosition;
	}

	/**
	 * @param startPosition the startPosition to set
	 */
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
	    StringBuilder sb = new StringBuilder();
	    sb.append(hunter).append(System.getProperty("line.separator")); 
	    sb.append(gold).append(System.getProperty("line.separator")); 
	    sb.append(wumpus).append(System.getProperty("line.separator")); 
	    
	    for (Pit pit:pits) {
	    	sb.append(pit).append(System.getProperty("line.separator"));
	    }
	    return sb.toString();
	}

	
}
