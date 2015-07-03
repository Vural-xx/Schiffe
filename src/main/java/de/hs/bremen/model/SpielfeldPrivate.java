package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.abstracts.AbstractSpielfeld;
import de.hs.bremen.enums.Feldstatus;

public class SpielfeldPrivate extends AbstractSpielfeld {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3801850117939689350L;
	
	/**
	 * Schiffe auf dem Spielfeld
	 */
	private ArrayList<Schiff> schiffe;

	public SpielfeldPrivate(int groesse) {
		super(groesse);
		schiffe = new ArrayList<Schiff>();
	}
	
	/**
	 * Getter Schiffe.
	 * @return schiffeArray
	 */
	public void setSchiffe(ArrayList<Schiff> schiffe) {
		this.schiffe = schiffe;
	}

	
	public ArrayList<Schiff> getSchiffe() {
		return schiffe;
	}
	
	/**
	 * Schiff wird auf dem Spielfeld platziert und bekommt diese Felder auch noch sich selber zugewiesen.
	 * @param schiff: Schiff welches platziert werden soll.
	 * @param position: Position auf dem das Schiff platziert werden soll.
	 * @param horizontal: Angabe ob das Schiff horizontal platziert werden soll.
	 */
	public void platziereSchiff(Schiff schiff, Position position, boolean horizontal){
		Feld[] f = new Feld[schiff.getFelder().length];
		for (int i = 0; i < schiff.getFelder().length; i++){
			if(horizontal){
				felder[position.getPositionY()-1][position.getPositonX()-1+i].setFeldstatus(Feldstatus.BESETZT);
				f[i] = felder[position.getPositionY()-1][position.getPositonX()-1+i];
			}else{
				felder[position.getPositionY()-1+i][position.getPositonX()-1].setFeldstatus(Feldstatus.BESETZT);
				f[i] = felder[position.getPositionY()-1+i][position.getPositonX()-1];
			}
		}
		schiff.setFelder(f);
		schiff.setPlatziert(true);
		schiffe.add(schiff);
	}
	
	/**
	 * Gibt Anzahl der Plätze die auf dem Spielfeld von Schiffen belegt sind wieder.
	 * @return: Belegte Plätze auf dem Spielfeld
	 */
	public int getPlaetzeBelegt(){
		int plaetzeBelegt = 0;
		if(schiffe != null){
			for(Schiff s : schiffe){
				plaetzeBelegt = plaetzeBelegt + s.getPlaetzeBelegung();
			}
		}
		return plaetzeBelegt;	
	}
	
	/**
	 * Anzahl der maximalen Schiffe, die auf dem Spielfeld platziert werden können.
	 * @return
	 */
	public int getMaximumAnzahlSchiffe(){
		return (getSpielfeldgroesse() * getSpielfeldgroesse()) / 12;
	}
	
	/**
	 * Anzahl der maximalen Schiffe, die auf dem Spielfeld für das im Parameter übergebene
	 * Schiff platziert werden können.
	 * @return
	 */
	public int getMaximumAnzahlSchiffeForSchiff(Schiff schiff){
		return (getSpielfeldgroesse() * getSpielfeldgroesse()) / schiff.getPlaetzeBelegung();
	}
	
	/**
	 * Gibt Schiff anhand einer Position wieder
	 * @param position: Position die auf ein Schiff überprüft werden soll.
	 * @return: Schiff welches sich an der Position befindet, oder null wenn kein Schiff vorhanden.
	 */
	public Schiff getSchiffByPosition(Position position){
		for(int i = 0; i<schiffe.size(); i++){
			for(int j = 0; j<schiffe.get(i).getFelder().length; j++)
			if(schiffe.get(i).getFelder()[j].getPosition().equals(position)){
				return schiffe.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gibt Schiff anhand mehrerer Positionen wieder
	 * @param position: Positionen die auf ein Schiff überprüft werden sollen.
	 * @return: Schiff welches sich an den Positionen befindet, oder null wenn kein Schiff vorhanden.
	 */
	public Schiff getSchiffByPosition(ArrayList<Position>position){
		Schiff schiff = null;
		for(int i = 0; i<position.size(); i++){
			schiff = getSchiffByPosition(position.get(i));
			if(schiff != null){
				break;
			}
		}
		return schiff;
	}
	
	/**
	 * Gibt an ob ein schiff an einer Position auf dem Spielfeld platziert werden kann.
	 * @param schiff: Schiff welches platziert soll.
	 * @param position: Position auf dem das Schiff platziert werden soll.
	 * @param horizontal: Angabe ob das Schiff horizontal oder vertikal platziert werden soll.
	 * @return: Angabe ob Schiff platziert werden darf.
	 */
	public boolean schiffPlazierbar(Schiff schiff, Position position, int horizontal){		
		// Schiff würde außerhalb Spielfeld liegen
		if((position.getPositionY() <=0 || position.getPositonX() <=0)
			|| (horizontal == 1 && (position.getPositonX()-1 + schiff.getLaenge() > getSpielfeldgroesse()))
			|| (horizontal == 2 && (position.getPositionY()-1 + schiff.getLaenge() > getSpielfeldgroesse()))){
				return false;
		}

		Position[] positionen = new Position[schiff.getLaenge()];
		positionen[0] = position;
		for(int i = 1; i< positionen.length; i++){
			if(horizontal ==1){
				positionen[i] = new Position(position.getPositonX()+i, position.getPositionY());
			}else{
				positionen[i] = new Position(position.getPositonX(), position.getPositionY()+i);
			}
		}
		ArrayList<Position> puffer = new ArrayList<Position>();
		for(int i = 0; i< positionen.length; i++){
			if(horizontal == 1){
				if(i==0){
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()+1));
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()));
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()+1));
				} else if (i == positionen.length-1){
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX()+1, positionen[i].getPositionY()));
					puffer.add(new Position(positionen[i].getPositonX()+1, positionen[i].getPositionY()+1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()+1));
				}else{
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()+1));
				}
					
				
			}else{
				if(i==0){
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX()+1, positionen[i].getPositionY()-1));
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()));
				} else if (i == positionen.length-1){
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()));
					puffer.add(new Position(position.getPositonX()-1, positionen[i].getPositionY()+1));
					puffer.add(new Position(positionen[i].getPositonX(), positionen[i].getPositionY()+1));
					puffer.add(new Position(positionen[i].getPositonX()+1, position.getPositionY()+1));
					puffer.add(new Position(positionen[i].getPositonX()+1, positionen[i].getPositionY()));
				}else{
					puffer.add(new Position(positionen[i].getPositonX()-1, positionen[i].getPositionY()));
					puffer.add(new Position(positionen[i].getPositonX()+1, positionen[i].getPositionY()));
				}
				
			}
			puffer.add(positionen[i]);
		}
		if(getSchiffByPosition(puffer) != null){
			return false;
		}else{
			return true;
		}
	}

}
