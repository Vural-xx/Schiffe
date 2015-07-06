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
	
	/**
	 * Position X für die Spalte
	 */
	private int positonX;
	
	/**
	 * Position Y für die Zeile
	 */
	private int positionY;
	
	/**
	 * Konstruktor
	 * @param x: Spalte
	 * @param y: Zeile
	 */
	public Position(int x, int y){
		positonX = x;
		positionY = y;
	}

	/**
	 * Getter der X Position
	 * @return
	 */
	public int getPositonX() {
		return positonX;
	}
	
	/**
	 * Setter der X Position
	 * @param positonX: Spalte
	 */
	public void setPositonX(int positonX) {
		this.positonX = positonX;
	}
	
	/**
	 * Getter der Y Position
	 * @return
	 */
	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * Setter der Y Position
	 * @param positionY: Zeile
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
