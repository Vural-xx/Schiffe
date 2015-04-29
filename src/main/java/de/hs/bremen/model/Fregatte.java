package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp Fregatte.
 * @author vural
 *
 */
public class Fregatte extends Schiff {

	
	/**
	 * Leerer Konstruktor.
	 */
	public Fregatte(){
		super();
	}
		
	/**
	 * Konstruktor.
	 * @param position
	 * @param horizontal
	 */
	public Fregatte(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
