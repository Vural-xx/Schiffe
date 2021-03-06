package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp Korvette.
 * @author vural
 *
 */
public class Korvette extends Schiff {
 
	/**
	 * SerialVersionUID zum Speichern und Lesens
	 */
	private static final long serialVersionUID = 2772161333091514134L;

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
