package de.hs.bremen.model;

import java.awt.Color;
import java.io.Serializable;

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
	 * @param position: Position des Feldes
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
	 * @param position: Position, welche gesetzt werden soll.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	
	
	/**
	 * Farbe die auf dem Spielfeld angezeigt werden soll, wird anhand des Feldstatus "berechnet"
	 * @return: Inhalt des Feldes
	 */
	public Color getGuiInhalt() {
		if(getFeldstatus()== Feldstatus.GETROFFEN){
			return Color.RED;
		} else if (getFeldstatus()==Feldstatus.BESETZT){
			return Color.BLACK;
		} else if(getFeldstatus()==Feldstatus.VERSENKT){
			return Color.GREEN;
		} else if(getFeldstatus()==Feldstatus.VERFEHLT){
			return Color.BLUE;
		}
		return null;
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
