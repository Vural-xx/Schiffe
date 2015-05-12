package de.hs.bremen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hs.bremen.enums.Feldstatus;
import helper.IO;

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
	 * Treffer wird auf dem Spielfeld vermerkt.
	 * @param positionen: Position auf dem der Treffer vermerkt werden soll.
	 */
	/*public void trefferPlatzieren(Position[] positionen){
		for(Position p: positionen){
			getFeld(p.getPositonX(), p.getPositionY()).setFeldstatus(Feldstatus.GETROFFEN);
		}
	}*/
	
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
	 * Spielfeld wird ausgedruckt.
	 */
	public void printSpielfeld(){
		for (int g = 0; g <= getSpielfeldgroesse(); g++){
			System.out.print(g + "\t");	
			
		}
		IO.println(" ");
		int g = 0;
		for (Feld[] f: felder) {
			if(g <= getSpielfeldgroesse()){
				g++;
				System.out.print(g + "\t");
				}
			for (Feld ff: f) {
				IO.print(ff.getInhalt());
	            IO.print("\t");
	         }
	         IO.println("");
	         
	    }
	}	
		
	/**
	 * Schiffauswahlmenü wird gebaut.
	 * @return String
	 */
	public String printSchiffeMenu(){
		String menu = "Wählen Sie:    ";
		List<String> bereitsDrin = new ArrayList<String>();
				
		for(Schiff s: schiffe){
			if(!bereitsDrin.contains(s.getName()) && !s.rundeAussetzen()){
				bereitsDrin.add(s.getName());
				menu = menu + " "+ getAuswahlNummerByName(s.getName()) +" für "+ s.getName() +" || ";
			}
			
		}
		// Die letzten Zeichen werden vom menü entfernt. In diesem Fall 4 Stück.
		return menu.substring(0, menu.length()-4);
	}
	
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
	
	public Schiff getZustaendigesSchiff(int auswahl){
		for(int i=0; i < schiffe.size(); i++){
			if(getSchifftypByNumber(auswahl).equals(schiffe.get(i).getName()) && !schiffe.get(i).rundeAussetzen()){
				return schiffe.get(i);
			}
		}
		return null;
	}
	
	public String getSchifftypByNumber(int auswahl){
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
	
	public void feuerPlatzieren(Position position){
		Schiff schiff = getSchiffByPosition(position);
		if(schiff != null && schiff.getroffen(position)){
			System.out.println("Sie haben das Schiff " +schiff.getName()+ " getroffen");
		}else{
			for(int i = 0 ; i < felder.length; i++){
				for (int j = 0 ; j < felder[i].length; j++){
					if(felder[i][j].getPosition().equals(position) && felder[i][j].getFeldstatus() == Feldstatus.WASSER){
						felder[i][j].setFeldstatus(Feldstatus.VERFEHLT);
						System.out.println ("Das Ziel wurde VERFEHLT");
					}
		         }
		     }
		}
	}
	
	public void feuerPlatzieren(Position[] position){
		for(int i = 0; i <position.length; i++){
			if(position[i] != null){
				feuerPlatzieren(position[i]);
			}
		}
	}
	
	public Schiff getSchiffByPosition(Position position){
		for(int i = 0; i<schiffe.size(); i++){
			for(int j = 0; j<schiffe.get(i).getFelder().length; j++)
			if(schiffe.get(i).getFelder()[j].getPosition().equals(position)){
				return schiffe.get(i);
			}
		}
		return null;
	}
	
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
}
