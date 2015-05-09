package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.enums.Feldstatus;

public class Feld {
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
	
	public Position[] pufferZoneBerechnen(){
		Position[] nichtPlazierbar = new Position[9];
		nichtPlazierbar[0] = position;
		nichtPlazierbar[1] = new Position(position.getPositonX()-1, position.getPositionY()-1);
		nichtPlazierbar[2] = new Position(position.getPositonX(), position.getPositionY()-1);
		nichtPlazierbar[3] = new Position(position.getPositonX()+1, position.getPositionY()-1);
		nichtPlazierbar[4] = new Position(position.getPositonX()+1, position.getPositionY());
		nichtPlazierbar[5] = new Position(position.getPositonX()+1, position.getPositionY()+1);
		nichtPlazierbar[6] = new Position(position.getPositonX(), position.getPositionY()+1);
		nichtPlazierbar[7] = new Position(position.getPositonX()-1, position.getPositionY()+1);
		nichtPlazierbar[8] = new Position(position.getPositonX()-1, position.getPositionY());
		return nichtPlazierbar;
		
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
