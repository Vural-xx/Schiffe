package de.hs.bremen.model;

import java.io.Serializable;


/**
 * Klasse für die Position der einzelnen Teile eines Schiffes
 * @author vural
 *
 */
public class Position implements Serializable{
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3473569011150075985L;
	private int positonX;
	private int positionY;
	
	public Position(int x, int y){
		positonX = x;
		positionY = y;
	}

	/**
	 * @return
	 */
	public int getPositonX() {
		return positonX;
	}
	
	/**
	 * @param positonX
	 */
	public void setPositonX(int positonX) {
		this.positonX = positonX;
	}
	
	/**
	 * @return
	 */
	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * @param positionY
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	 /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	 public boolean equals(Object other) {
		 if (!(other instanceof Position)) {
			 return false;
	     }
	     Position otherPosition = (Position) other;
	     return this.positionY == otherPosition.positionY && this.positonX == otherPosition.positonX;
	 }

}
