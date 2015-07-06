package de.hs.bremen.model;

import de.hs.bremen.abstracts.AbstractSpielfeld;
import de.hs.bremen.enums.Feldstatus;

public class SpielfeldPublic extends AbstractSpielfeld {

	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 7038045028562697796L;

	public SpielfeldPublic(int groesse) {
		super(groesse);
	}
	
	/**
	 * Platziert Feuer auf Feld, die der Position angehört.
	 * @param position: Position auf dessen Feld gefeuert werden soll.
	 */
	public void feuerPlatzieren(Position position){
		for(int i = 0 ; i < felder.length; i++){
			for (int j = 0 ; j < felder[i].length; j++){
				if(felder[i][j].getPosition().equals(position) && felder[i][j].getFeldstatus() == Feldstatus.WASSER){
						felder[i][j].setFeldstatus(Feldstatus.VERFEHLT);
				}
		    }
		}
	}
	
	/**
	 * Platziert Feuer auf Feldern, die den Positionen angehören.
	 * @param position: Position auf dessen Feld gefeuert werden soll.
	 */
	public void feuerPlatzieren(Position[] position){
		for(int i = 0; i <position.length; i++){
			if(position[i] != null){
				feuerPlatzieren(position[i]);
			}
		}
	}

}
