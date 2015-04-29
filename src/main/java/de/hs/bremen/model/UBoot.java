package de.hs.bremen.model;

/**
 * Klasse für den Schifftyp UBoot.
 * @author vural
 *
 */
public class UBoot extends Schiff {
	
	/**
	 * Leerer Konstruktor.
	 */
	public UBoot(){
		super();
	}


	/**
	 * Konstruktor.
	 * @param position
	 * @param horizontal
	 */
	public UBoot(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
