package de.hs.bremen.model;

import java.awt.event.ItemEvent;
import java.util.ArrayList;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Feldstatus;

/**
 * Klasse des Computergegners
 * @author Christin
 *
 */
public class ComputerGegner extends Actor {
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 8717880889204496799L;
	
	/**
	 * Der Gegner Spieler
	 */
	private Actor[] gegner;

	/**
	 * Arraylist für die Schüsse
	 */
	private ArrayList<Position> shots;
	private int tempZeile=0;
	private int tempSpalte=0;
	
	/**
	 * Konstruktor
	 * @param name des Computergegners
	 */
	public ComputerGegner(String name){
		super(name);
		shots= new ArrayList<Position>();
	}
	
	
	/**
	 * Zufallsberechnung der Zeile
	 * @return zeile: X Achse
	 */
	public int randomRechnerZeile(){
		int zeile= (int)(Math.random()*getSpielfeld().getSpielfeldgroesse())+1;
		return zeile;
	}
	
	/**
	 * Zufallsberechnung der Spalte
	 * @return spalte: Y Achse
	 */
	public int randomRechnerSpalte(){
		int spalte= (int)(Math.random()*5)+1;
		return spalte;	
	}
	
	/**
	 * Zufallsberechnung der Ausrichung
	 * @return Ausrichtung: horizontal oder vertikal
	 */
	public int ausrichtung(){
		int ausrichtung=(int)(Math.random()*2)+1;
		return ausrichtung;
	}
	

	/**
	 * Setzt die Schiffe für den Computergegner
	 * @param schiffe: Schiffe die gesetzt werden können
	 */
	public void schiffeSetzen(ArrayList <Schiff> schiffe){
		int zeile=0;
		int spalte=0;
		int hori=0;
		boolean horizontal=false;

		for(int i=0; i< schiffe.size(); i++){
			do {
				zeile= randomRechnerZeile();
				spalte= randomRechnerSpalte();
				hori=ausrichtung();				
			}while(!getSpielfeld().schiffPlazierbar(schiffe.get(i), new Position(spalte, zeile), hori));
			if(hori == 1){
				horizontal = true;
			}else{
				horizontal = false;
			}
			this.getSpielfeld().platziereSchiff(schiffe.get(i), new Position(spalte, zeile), horizontal);
					
		}
	}
		
	
	
	/**
	 * Übergibt die Schiffe, mit denen geschossen werden kann
	 * @return schiff: Schiffe ohne Wartezeit
	 */
	public Schiff schiffZumSchießen(){
		int feuerstaerke=0;
		Schiff schiff= null;
		if(getSpielfeld().schiffeOhneWarteZeit()){
			for(int i=0; i <getSpielfeld().getSchiffe().size();i++){
				if(getSpielfeld().getSchiffe().get(i).getFeuerstaerke()>feuerstaerke && getSpielfeld().getSchiffe().get(i).getWartezeit()==0){
					feuerstaerke= getSpielfeld().getSchiffe().get(i).getFeuerstaerke();
					schiff=getSpielfeld().getSchiffe().get(i);
				}
			}
		}
		return schiff;
	}
	
	
	/**
	 * Setzt die Position des Feuers an einer zufälligen Stelle
	 */
	public void intelligent(){
		Schiff schiff= schiffZumSchießen();
		Schiff tempschiff;
		int spalte;
		int zeile;

		Position position;
			do{
				spalte=randomRechnerSpalte();
				zeile=randomRechnerZeile();
				position=new Position(spalte, zeile);
			}while(!gegner[0].getSpielfeldPublic().schussPlatzierbar(schiff, position)&& gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.WASSER && gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.BESETZT &&gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.GETROFFEN);
		if(schiff != null){
			if(wennGetroffen(schiff, spalte, zeile)){
				tempschiff=schiff;
				tempSpalte=tempSpalte+tempschiff.getFeuerstaerke();
				kiFeuern(new Position(tempSpalte, tempZeile), tempschiff);
			} else {
				kiFeuern(position, schiff);
			}
		}
	}
	
	/**
	 * Erzeugt das Feuer auf das gegnerische Spielfeld
	 * @param position: dort wird das Feuer gesetzt
	 * @param schiff: mit diesem Schiff wird geschossen
	 */
	public void kiFeuern(Position position, Schiff schiff){
		Actor gegner=spielerAuswahl();
		Position[] positionsarray= new Position[schiff.getFeuerstaerke()];
		for (int i=0; i< schiff.getFeuerstaerke(); i++ ){
			positionsarray[i]= new Position(position.getPositonX()+i, position.getPositionY());
		}
		schiff.feuern(positionsarray, gegner.getSpielfeldPublic());
		gegner.trefferUebertragung();
		for(int i=0; i< schiff.getFeuerstaerke(); i++){
			shots.add(new Position(position.getPositonX()+i, position.getPositionY()));
			
		}
	}
	
	public boolean wennGetroffen(Schiff schiff, int spalte, int zeile){
		System.out.println(spalte);
		System.out.println(zeile);
		int counter=0;
		int test=zeile+schiff.getFeuerstaerke();
		int feuerstaerke= schiff.getFeuerstaerke();
		for(int i=0; i< schiff.getFeuerstaerke(); i++){
			System.out.println(((zeile)+i) +"blabla");

			System.out.println((zeile+schiff.getFeuerstaerke()));

			if(((test< getSpielfeld().getSpielfeldgroesse()) && gegner[0].getSpielfeld().getFeld(((spalte-1)+i),(zeile-1)).getFeldstatus()==Feldstatus.BESETZT)){
	
				
				System.out.println("AHAHAHAHAHH");
				counter++;
				tempSpalte=spalte;
				tempZeile=zeile;
				if(counter<= schiff.getFeuerstaerke()){
					return true;

				}
			}	
		}
		return false;	
	}
	
	/**
	 * Übergibt die Treffer beim gegnerischen Spielfeld
	 * @param gegner:Der ausgewählte Gegner
	 */
	public void getGegnerTreffer(Actor gegner){
		shots = null;
		shots = new ArrayList<Position>();
		for (Feld[] f: gegner.getSpielfeldPublic().getFelder()) {
			for (Feld ff: f) {
				if(ff.getFeldstatus() == Feldstatus.GETROFFEN){
					shots.add(ff.getPosition());
				}
	         }    
	    }
	}
	
	/**
	 * Zufällige Auswahl auf welchen Spieler gefeuert werden soll
	 * @return den zufällig ausgewählten Gegner
	 */
	public Actor spielerAuswahl(){
		int anzahlSpieler = this.gegner.length;
		Actor gegner;
		do {
		gegner= this.gegner[(int)(Math.random()*anzahlSpieler)];
		}while (gegner.getName().equals(this.getName()));
		return gegner;
	}


	/**
	 * Getter vom Gegner
	 * @return den Gegner
	 */
	public Actor[] getGegner() {
		return gegner;
	}


	/**
	 * Setter vom Gegner
	 * @param gegner
	 */
	public void setGegner(Actor[] gegner) {
		this.gegner = gegner;
	}
	
	
	
	
}
