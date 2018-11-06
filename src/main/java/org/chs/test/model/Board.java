/**
 * 
 */
package org.chs.test.model;

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
	
	/**
	 * Constructor
	 * @param size size of the board (NxN squares in the board)
	 */
	public Board(int size, Gold gold, Wumpus wumpus, Hunter hunter, List<Pit> pits) {
		this.size = size;
		this.gold = gold;
		this.wumpus = wumpus;
		this.hunter = hunter;
		this.pits = pits;
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
	 * @return the wumpus
	 */
	public Wumpus getHumpus() {
		return wumpus;
	}

	/**
	 * @param wumpus the wumpus to set
	 */
	public void setHumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
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

}
