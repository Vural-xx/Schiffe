package de.hs.bremen.model;

import java.io.Serializable;

/**
 * 
 * Klasse für Spieler.
 * @author vural
 *
 */

public class Spieler extends Actor implements Serializable{
	
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 2678006261357557482L;

	
	/**
	 * Konstruktor von Spieler
	 */
	public Spieler(String name){
	super(name);
	
	}
	
}