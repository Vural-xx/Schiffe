package de.hs.bremen.model;

import java.awt.Color;
import java.io.Serializable;

import de.hs.bremen.enums.Feldstatus;


/**
 * Abstrakte Klasse für Schiff
 * @author vural
 *
 */
public abstract class Schiff implements Serializable {
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 2764743705897860936L;

	/**
	 * Felder auf denen sich das Schiff befindet
	 */
	private Feld[] felder;
	
	/**
	 * Länge des Schiffs.
	 */
	private int laenge;
	
	/**
	 * Wartezeit des Schiffs. 
	 */
	private int wartezeit;
	
	/**
	 * Feuerstärke des Schiffs. 
	 */
	private int feuerstaerke;
	
	private boolean platziert;

	
	/**
	 * Leerer Konstruktor.
	 * Je nach dem, welche Unterklasse gebaut wird, werden die Eigenschaften gesetzt.
	 */
	public Schiff(){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			this.laenge = 4;
			this.feuerstaerke = 2;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			this.laenge = 3;
			this.feuerstaerke = 1;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			this.laenge = 2;
			this.feuerstaerke =1;
		}else{
			this.laenge = 5;
			this.feuerstaerke = 3;
		}
		felder = new Feld[this.laenge];
	}
	
		
	/**
	 * Konstruktor.
	 * @param position: Position auf dem das Schiff positioniert werden soll.
	 * @param horizontal: Angabe, ob das Schiff horizontal oder vertikal positioniert werden soll.
	 */
	public Schiff(Position position, boolean horizontal){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			this.laenge = 4;
			this.feuerstaerke = 2;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			this.laenge = 3;
			this.feuerstaerke = 1;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			this.laenge = 2;
			this.feuerstaerke =1;
		}else{
			this.laenge = 5;
			this.feuerstaerke = 3;
		}
		felder = new Feld[this.laenge];
	}
	
	public Schiff(Feld[] felder){
		this.felder = felder;
	}
	

	/**
	 * Übergibt, ob ein Feld mit einem Schiff platziert ist
	 * @return
	 */
	public boolean isPlatziert() {
		return platziert;
	}


	/**
	 * Setzt ein Feld auf platziert
	 * @param platziert: true oder false
	 */
	public void setPlatziert(boolean platziert) {
		this.platziert = platziert;
	}
	/**
	 * Methode, die wiedergibt, wieviele Felder von dieser Schiffunterklasse belegt werden,
	 * @return: platzbelegung
	 */
	public int getPlaetzeBelegung(){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			return 18;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			return 15;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			return 12;
		}else{
			return 21;
		}
	}
	
	/**
	 * Angbabe ob, das Schff versenkt wurde.
	 * @return: Schiff versenkt: true oder false
	 */
	public boolean versenkt(){
		int counter=0;
		for(int i=0; i< felder.length; i++){
			if(felder[i].getFeldstatus()== Feldstatus.GETROFFEN){
				counter++;
			}
		}
		return counter == felder.length;
	}
	
	/**
	 * Getter Größe.
	 * @return: Länge des Schiffs.
	 */
	public int getGroeße(){
		return felder.length;
	}
	
	/**
	 * Getter felder.
	 * @return felder.
	 */
	public Feld[] getFelder() {
		return felder;
	}

	/**
	 * Setter felder.
	 * @param felder: Setzt die Felder
	 */
	public void setFelder(Feld[] felder) {
		this.felder = felder;
	}

	/**
	 * Getter Länge.
	 * @return länge
	 */
	public int getLaenge() {
		return laenge;
	}

	/**
	 * Setter länge.
	 * @param laenge: Setzt die Länge
	 */
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	/**
	 * Getter wartezeit.
	 * @return wartezeit
	 */
	public int getWartezeit() {
		return wartezeit;
	}

	/**
	 * Setter Wartezeit.
	 * @param wartezeit: Setzt die Wartezeit
	 */
	public void setWartezeit(int wartezeit) {
		this.wartezeit = wartezeit;
	}

	/**
	 * Getter Feuerstärke.
	 * @return
	 */
	public int getFeuerstaerke() {
		return feuerstaerke;
	}

	/**
	 * Setter Feuerstärke.
	 * @param feuerstaerke: Setzt die Feuerstärke
	 */
	public void setFeuerstaerke(int feuerstaerke) {
		this.feuerstaerke = feuerstaerke;
	}
	
	/**
	 * Getter Farbe 
	 * @return die Farbe
	 */
	public Color getFarbe(){
		return Color.BLACK;
		
	}

	/**
	 * Zeigt an, ob das Schiff eine Runde aussetzten muss
	 * @return: rundeAussetzen
	 */
	public boolean rundeAussetzen(){
		return wartezeit > 0;
	}
	
	
	/**
	 * Feuert auf das SpielfeldPublic für ein Feld
	 * @param position: Das Feld, wo drauf gefeuert wird
	 * @param spielfeld: das Spielfeld worauf gefeuert wird
	 */
	public void feuern(Position position, SpielfeldPublic spielfeld){
		setWartezeit(feuerstaerke);
		if(feuerstaerke > 1){
			spielfeld.feuerPlatzieren(feuerPositionenBerechnen(position, spielfeld.getSpielfeldgroesse()));
		}
		spielfeld.feuerPlatzieren(position);
	}
	
	
	/**
	 * Berechnet die Positionen der Felder des Feuers anhand der unterschiedlichen Feuerstärke
	 * @param position: die Positionen die mit der Feuerstärke belegt werden
	 * @param groesseSpielfeld: wie groß das Spielfeld ist
	 * @return
	 */
	public Position[] feuerPositionenBerechnen(Position position, int groesseSpielfeld){
		Position[] positionen = new Position[feuerstaerke];
		positionen[0] = position;
		for(int i = 1; i <positionen.length; i++){
			if(positionen[i-1].getPositonX()+1 <= groesseSpielfeld){
				positionen[i] = new Position(positionen[i-1].getPositonX()+1, position.getPositionY());
			}
		}
		return positionen;
	}
	
	/**
	 * Feuert auf das SpielfeldPublic für mehrere Felder
	 * @param position: Die Felder, wo drauf gefeuert wird
	 * @param spielfeld: das Spielfeld worauf gefeuert wird
	 */
	public void feuern(Position[] position, SpielfeldPublic spielfeld){
		setWartezeit(feuerstaerke);
		spielfeld.feuerPlatzieren(position);
	}
	
	
	/**
	 * Name des  Schiffs
	 * @return Name des Schiffs
	 */
	public String getName(){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			return "Fregatte";
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			return "Korvette";
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			return "UBoot";
		}else{
			return "Zerstoerer";
		}
	}

	

}
