package de.hs.bremen.model;

import helper.ConsoleColor;

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
			return ConsoleColor.ANSI_BLUE+"~" + ConsoleColor.ANSI_RESET;
		} else if(getFeldstatus()== Feldstatus.GETROFFEN){
			return ConsoleColor.ANSI_RED+"T" + ConsoleColor.ANSI_RESET;
		} else if (getFeldstatus()==Feldstatus.BESETZT){
			return ConsoleColor.ANSI_CYAN+"S" + ConsoleColor.ANSI_RESET;
		} else {
			return ConsoleColor.ANSI_BLACK+"X" + ConsoleColor.ANSI_RESET;
		}
	}
	public Feldstatus getFeldstatus() {
		return feldstatus;
	}

	public void setFeldstatus(Feldstatus feldstatus) {
		this.feldstatus = feldstatus;
	}
}
