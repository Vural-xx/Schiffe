package de.hs.bremen.model;

import java.io.Serializable;
import java.util.ArrayList;

import de.hs.bremen.enums.Feldstatus;

public class Feld implements Serializable{
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3500625255490168961L;
	private Position position;
	private Feldstatus feldstatus;
	
	public Feld(Position position){
		feldstatus = Feldstatus.WASSER;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getInhalt() {
		if(getFeldstatus()==Feldstatus.WASSER){
			return Spiel.ANSI_BLUE+"~" + Spiel.ANSI_RESET;
		} else if(getFeldstatus()== Feldstatus.GETROFFEN){
			return Spiel.ANSI_RED+"x" + Spiel.ANSI_RESET;
		} else if (getFeldstatus()==Feldstatus.BESETZT){
			return Spiel.ANSI_CYAN+"s" + Spiel.ANSI_RESET;
		} else{
			return Spiel.ANSI_BLACK+"x" + Spiel.ANSI_RESET;
		}
	}
	public Feldstatus getFeldstatus() {
		return feldstatus;
	}

	public void setFeldstatus(Feldstatus feldstatus) {
		this.feldstatus = feldstatus;
	}
}
