package de.hs.bremen.model;

import java.io.Serializable;

import de.hs.bremen.abstracts.Actor;

public class Spiel implements Serializable{
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = -3646276051424926863L;
	
	/**
	 * Array für Spieler
	 */
	private Actor[] spieler;
	
	/**
	 * SpielfeldGröße
	 */
	private int spielfeldGroesse;
	
	/**
	 * Getter Spieler
	 * @return
	 */
	public Actor[] getSpieler() {
		return spieler;
	}
	
	/**
	 * Setter Spieler
	 * @param spieler: Setzt den Spieler
	 */
	public void setSpieler(Actor[] spieler) {
		this.spieler = spieler;
	}
	
	/**
	 * Setter Spielfeldgröße
	 * @param spielfeldGroesse: Setzt die Spielfeldgröße
	 */
	public void setSpielfeldGroesse(int spielfeldGroesse){
		this.spielfeldGroesse = spielfeldGroesse;
	}

	/**
	 * Getter Spielfeldgröße
	 * @return
	 */
	public int getSpielfeldGroesse() {
		return spielfeldGroesse;
	}
	
}

	
