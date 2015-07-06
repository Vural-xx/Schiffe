package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp Fregatte.
 * @author vural
 *
 */
public class Fregatte extends Schiff {

	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3896465535974049401L;

	/**
	 * Leerer Konstruktor.
	 */
	public Fregatte(){
		super();
	}
		
	/**
	 * Konstruktor.
	 * @param position: Position der Fregatte
	 * @param horizontal: ob das Schiff horizontal ist
	 */
	public Fregatte(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
