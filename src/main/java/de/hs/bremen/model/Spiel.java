package de.hs.bremen.model;

import java.io.Serializable;

public class Spiel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3646276051424926863L;
	
	private Actor[] spieler;
	
	public Actor[] getSpieler() {
		return spieler;
	}
	
	public void setSpieler(Actor[] spieler) {
		this.spieler = spieler;
	}
}
