package de.hs.bremen.model;

import helper.ConsoleColor;

import java.io.Serializable;
import java.util.ArrayList;

import de.hs.bremen.enums.Feldstatus;

/**
 * Feldklasse.
 * @author vural
 *
 */
public class Feld implements Serializable{
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3500625255490168961L;
	
	/**
	 * Position des Feldes auf einem Spielfeld.
	 */
	private Position position;
	
	/**
	 * Feldstatus des Feldes.
	 */
	private Feldstatus feldstatus;
	
	/**
	 * Konstruktor
	 * @param position
	 */
	public Feld(Position position){
		feldstatus = Feldstatus.WASSER;
		this.position = position;
	}
	
	/**
	 * Getter Position.
	 * @return: position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Setter Position.
	 * @param position: Position welche gesetzt werden soll.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Inhalt welches aus dem Spielfeld angezeigt werden soll, wird anhand des Feldstatus "berechnet"
	 * @return: Inhalt des Feldes
	 */
	public String getInhalt() {
		if(getFeldstatus()==Feldstatus.WASSER){
			return ConsoleColor.ANSI_BLUE+"~" + ConsoleColor.ANSI_RESET;
		} else if(getFeldstatus()== Feldstatus.GETROFFEN){
			return ConsoleColor.ANSI_RED+"T" + ConsoleColor.ANSI_RESET;
		} else if (getFeldstatus()==Feldstatus.BESETZT){
			return ConsoleColor.ANSI_CYAN+"S" + ConsoleColor.ANSI_RESET;
		} else {
			return ConsoleColor.ANSI_BLACK+"X" + ConsoleColor.ANSI_RESET;
		}
	}
	
	/**
	 * Getter feldstatus
	 * @return: feldstatus.
	 */
	public Feldstatus getFeldstatus() {
		return feldstatus;
	}

	/**
	 * Setter feldstatus
	 * @param feldstatus: Neuer Feldstatus der gesetzt werden soll.
	 */
	public void setFeldstatus(Feldstatus feldstatus) {
		this.feldstatus = feldstatus;
	}
}
