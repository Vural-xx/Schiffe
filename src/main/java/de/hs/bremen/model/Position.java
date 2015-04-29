package de.hs.bremen.model;


/**
 * Klasse für die Position der einzelnen Teile eines Schiffes
 * @author vural
 *
 */
public class Position {
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
