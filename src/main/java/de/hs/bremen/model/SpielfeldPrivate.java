package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.abstracts.AbstractSpielfeld;
import de.hs.bremen.abstracts.Schiff;
import de.hs.bremen.enums.Feldstatus;

public class SpielfeldPrivate extends AbstractSpielfeld {
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3801850117939689350L;
	
	/**
	 * Schiffe auf dem Spielfeld
	 */
	private ArrayList<Schiff> schiffe;

	/**
	 * Konstruktor
	 * @param groesse
	 */
	public SpielfeldPrivate(int groesse) {
		super(groesse);
		schiffe = new ArrayList<Schiff>();
	}
	
	/**
	 * Getter Schiffe
	 * @return schiffeArray
	 */
	public ArrayList<Schiff> getSchiffe() {
		return schiffe;
	}
	
	/**
	 * Setter Schiffe
	 * @param schiffe: Setzt das Schiff
	 */
	public void setSchiffe(ArrayList<Schiff> schiffe) {
		this.schiffe = schiffe;
	}

	
	
	
	/**
	 * Schiff wird auf dem Spielfeld platziert und bekommt diese Felder auch noch sich selber zugewiesen.
	 * @param schiff: Schiff welches platziert werden soll.
	 * @param position: Position auf die das Schiff platziert werden soll.
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
	 * Gibt Anzahl der Plätze wieder, die auf dem Spielfeld von Schiffen belegt sind
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
	 * @return: maximale Anzahl an Schiffe
	 */
	public int getMaximumAnzahlSchiffe(){
		return (getSpielfeldgroesse() * getSpielfeldgroesse()) / 12;
	}
	
	
	
	/**
	 * Gibt Schiff anhand einer Position wieder
	 * @param position: Position die auf ein Schiff überprüft werden soll.
	 * @return: Welches Schiff sich an der Position befindet, oder null wenn kein Schiff vorhanden.
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
	 * @return: Welches Schiff sich an den Positionen befindet, oder null wenn kein Schiff vorhanden.
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
	 * Gibt an, ob ein Schiff an einer Position auf dem Spielfeld platziert werden kann.
	 * @param schiff: Schiff welches platziert soll.
	 * @param position: Position auf dem das Schiff platziert werden soll.
	 * @param horizontal: Angabe ob das Schiff horizontal oder vertikal platziert werden soll.
	 * @return: Angabe ob Schiff platziert werden darf.
	 */
	public boolean schiffPlazierbar(Schiff schiff, Position position, int horizontal){		
		if((position.getPositionY() <=0 || position.getPositonX() <=0)
			|| (horizontal == 1 && (position.getPositonX() + schiff.getLaenge() > getSpielfeldgroesse()))
			|| (horizontal == 2 && (position.getPositionY() + schiff.getLaenge() > getSpielfeldgroesse()))){
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

	/**
	 * Gibt an ob ein Schiff eine Wartezeit hat
	 * @return
	 */
	public boolean schiffeOhneWarteZeit(){
		boolean ohneWartezeit = false;
		for(int i = 0; i <getSchiffe().size(); i++){
			ohneWartezeit = schiffe.get(i).getWartezeit() == 0;
			if(schiffe.get(i).getWartezeit() == 0){
				ohneWartezeit= true;
			}
		}
		return ohneWartezeit;
	}
}