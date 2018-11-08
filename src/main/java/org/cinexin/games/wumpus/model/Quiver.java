/**
 * 
 */
package org.cinexin.games.wumpus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author migui
 * Model for Quiver that {@link Hunter} can wear
 */
public class Quiver extends Actor {

	ArrayList<Arrow> arrows = new ArrayList<>();
	
	/**
	 * @param position
	 */
	public Quiver(final Position position) {
		super(position);
	}
	
	/**
	 * @param position
	 */
	public Quiver(final Position position, final int numOfArrows) {
		super(position);
		fillQuiver(numOfArrows, position);
	}

	private void fillQuiver(final int numOfArrows, final Position position) {
		for (int i = 0; i < numOfArrows; i++) {
			arrows.add(new Arrow(position));
		}
	}

	/**
	 * @return the arrows
	 */
	public List<Arrow> getArrows() {
		return (List<Arrow>) arrows;
	}

	/**
	 * @param arrows the arrows to set
	 */
	public void setArrows(ArrayList<Arrow> arrows) {
		this.arrows = arrows;
	}	
	
}
