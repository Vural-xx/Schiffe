package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp UBoot.
 * @author vural
 *
 */
public class UBoot extends Schiff {
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = -6692294187953020269L;


	/**
	 * Leerer Konstruktor.
	 */
	public UBoot(){
		super();
	}


	/**
	 * Konstruktor.
	 * @param position: Position des Uboots
	 * @param horizontal: ob das Schiff horizontal ist
	 */
	public UBoot(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
