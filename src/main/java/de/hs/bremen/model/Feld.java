package de.hs.bremen.model;

import de.hs.bremen.enums.Feldstatus;

public class Feld {
	private Position position;
	private boolean getroffen;
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

	public boolean isGetroffen() {
		return getroffen;
		
	}

	public void setGetroffen(boolean getroffen) {
		this.getroffen = getroffen;
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
