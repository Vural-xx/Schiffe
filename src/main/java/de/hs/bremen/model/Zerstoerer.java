package de.hs.bremen.model;

import de.hs.bremen.abstracts.Schiff;

public class Zerstoerer extends Schiff {

	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 5304549716161218230L;

	public Zerstoerer(){
		super();
	}
		
	/**
	 * Konstruktor
	 * @param position: Position des Zerstörers
	 * @param horizontal: ob das Schiff horizontal ist
	 */
	public Zerstoerer(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
