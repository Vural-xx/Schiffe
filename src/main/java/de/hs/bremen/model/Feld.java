package de.hs.bremen.model;

import de.hs.bremen.enums.Feldstatus;

public class Feld {
	private Position position;
	private boolean getroffen;
	private String inhalt;
	private Feldstatus feldstatus;
	
	public Feld(Position position){
		inhalt = "~";
		feldstatus = Feldstatus.WASSER;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isGetroffen() {
		return getroffen;
	}

	public void setGetroffen(boolean getroffen) {
		this.getroffen = getroffen;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public Feldstatus getFeldstatus() {
		return feldstatus;
	}

	public void setFeldstatus(Feldstatus feldstatus) {
		this.feldstatus = feldstatus;
	}
}
