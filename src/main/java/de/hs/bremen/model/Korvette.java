package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp Korvette.
 * @author vural
 *
 */
public class Korvette extends Schiff {
 
	/**
	 * Leerer Konstruktor.
	 */
	public Korvette(){
		super();
	}
		
	/**
	 * Konstruktor.
	 * @param position
	 * @param horizontal
	 */
	public Korvette(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
