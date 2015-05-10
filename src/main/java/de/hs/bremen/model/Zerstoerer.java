package de.hs.bremen.model;

public class Zerstoerer extends Schiff {

	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 5304549716161218230L;

	public Zerstoerer(){
		super();
	}
		
	public Zerstoerer(Position position, boolean horizontal){
		super(position, horizontal);
	}
}
