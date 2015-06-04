package de.hs.bremen.model;

import java.io.Serializable;
import java.util.ArrayList;

import de.hs.bremen.enums.Feldstatus;


/**
 * Klasse für Spielfelder des Spielers auf dem die Schiffe platziert werden.
 * @author vural
 *
 */
public class Spielfeld implements Serializable{
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 4330665548741900491L;


	/**
	 * Felder des Spielfelds.
	 */
	private Feld [][] felder;
	
	
	/**
	 * Schiffe auf dem Spielfeld
	 */
	private ArrayList<Schiff> schiffe;
	
	/**
	 * Konstruktor, welche die Felder auf dem Spielfeld initialisiert.
	 * @param groesse: Größe des Spielfelds
	 */
	public Spielfeld(int groesse){
		createFelder(groesse);
		schiffe = new ArrayList<Schiff>();
	}
	
	/**
	 * Getter.
	 * @return felder
	 */
	public Feld[][] getFelder() {
		return felder;
	}
	
	/**
	 * Setter.
	 * @param felder
	 */
	public void setFelder(Feld[][] felder) {
		this.felder = felder;
	}
	
	/**
	 * Getter. 
	 * @return: Größe des Spielfelds
	 */
	public int getSpielfeldgroesse(){
		return felder.length;
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
	 * Felder werden initialisiert.
	 * @param groesse: Anzahl der Felder auf dem quadratischen Spielfelds.
	 */
	public void createFelder(int groesse){
		felder = new Feld[groesse][groesse];
		for(int i = 0; i < felder.length; i++){
			for(int j= 0; j < felder[i].length; j++){
				felder[i][j] = new Feld(new Position(j+1, i+1));
			}
		}
	}
	
	/**
	 * Spezielles Feld wird zurückgegeben.
	 * @param x : X-Koordinate des Felds, welches gesucht werden soll.
	 * @param y : Y-Koordinate des Felds, welches gesucht werden soll.
	 * @return : Feld, welches mit den Parametern übereinstimmt.
	 */
	public Feld getFeld(int x, int y){
		return felder[y][x];
	}
	
	/**
	 * Schiff wird auf dem Spielfeld platziert und bekommt diese Felder auch noch sich selber zugewiesen.
	 * @param schiff: Schiff welches platziert werden soll.
	 * @param position: Position auf dem das Schiff platziert werden soll.
	 * @param horizontal: Angabe ob das Schiff horizontal platziert werden soll.
	 */
	public void platziereSchiff(Schiff schiff, Position position, int horizontal){
		Feld[] f = new Feld[schiff.getFelder().length];
		for (int i = 0; i < schiff.getFelder().length; i++){
			if(horizontal== 1){
				felder[position.getPositionY()-1][position.getPositonX()-1+i].setFeldstatus(Feldstatus.BESETZT);
				f[i] = felder[position.getPositionY()-1][position.getPositonX()-1+i];
			}else if (horizontal==2){
				felder[position.getPositionY()-1+i][position.getPositonX()-1].setFeldstatus(Feldstatus.BESETZT);
				f[i] = felder[position.getPositionY()-1+i][position.getPositonX()-1];
			}
		}
		schiff.setFelder(f);
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
	 * Spielfeld wird ausgedruckt.
	 */
	public void printSpielfeld(){
		for (int g = 0; g <= getSpielfeldgroesse(); g++){
			System.out.print(g + "\t");	
			
		}
		System.out.println(" ");
		int g = 0;
		for (Feld[] f: felder) {
			if(g <= getSpielfeldgroesse()){
				g++;
				System.out.print(g + "\t");
				}
			for (Feld ff: f) {
				System.out.print(ff.getInhalt());
	            System.out.print("\t");
	         }
	         System.out.println("");
	         
	    }
	}	
		
	/**
	 * Schiffauswahlmenü wird gebaut.
	 * @return String
	 */
	public String getSchiffeMenuAsString(ArrayList<Integer> moeglicheAuswahl){
		String menu = "Wählen Sie:    ";				
		for(Integer i: moeglicheAuswahl){
			if(i != 5){
				menu  = menu + " "+ i +" für "+ getSchifftypNameByNumber(i) +" || ";
			}
		
		}
		menu = menu + "5 um das Spiel abzuspeichern || ";
		// Die letzten Zeichen werden vom menü entfernt. In diesem Fall 4 Stück.
		return menu.substring(0, menu.length()-4);
	}
	
	public ArrayList<Integer> getMoeglicheAuswahlSchiffMenu(){
		ArrayList<Integer> moeglicheAuswahl = new ArrayList<Integer>();		
		for(Schiff s: schiffe){
			if(!moeglicheAuswahl.contains(getAuswahlNummerByName(s.getName())) && !s.rundeAussetzen()){
				moeglicheAuswahl.add(getAuswahlNummerByName(s.getName()));
			}
		}
		moeglicheAuswahl.add(5);
		return moeglicheAuswahl;
	}
	
	/**
	 * Gint zudtändige Auswahlnummer zu dem jeweiligen Schiffsnamen.
	 * @param name: Name dessen Auswahlnummern wiedergegeben werden soll
	 * @return: Auswahlnummer zum dazugehörigen Schiffstyp.
	 */
	public int getAuswahlNummerByName(String name){
		switch (name) {
		case "Zerstoerer":
			return 1;
		case "Fregatte":
			return 2;
		case "Korvette":
			return 3;
		default:
			return 4;
		}
	}
	
	/**
	 * Gibt das erste zuständige Schiff, welches dem richtigen Schifftyp entspricht
	 * @param auswahl: Auswahlnummer des Schifftyps
	 * @return: Zuständiges Schiff aus Array der Schiffe.
	 */
	public Schiff getZustaendigesSchiff(int auswahl){
		for(int i=0; i < schiffe.size(); i++){
			if(getSchifftypNameByNumber(auswahl).equals(schiffe.get(i).getName())){
				return schiffe.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gibt den Schifftypnamen zu der dazugehörigen Nummer im Auswahlmenü.
	 * @param auswahl: Auswahl die eingegeben wurde.
	 * @return: Schifftypnamen.
	 */
	public String getSchifftypNameByNumber(int auswahl){
		switch (auswahl) {
		case 1:
			return "Zerstoerer";
		case 2:
			return "Fregatte";
		case 3:
			return "Korvette";
		default:
			return "UBoot";
		}
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
