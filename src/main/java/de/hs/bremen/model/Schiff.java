package de.hs.bremen.model;

import de.hs.bremen.enums.Feldstatus;


/**
 * Abstrakte Klasse für Schiff
 * @author vural
 *
 */
public abstract class Schiff {
	
	/**
	 * Felder auf denen sich das Schiff befindet.
	 */
	private Feld[] felder;
	
	/**
	 * Länge des Schiffs.
	 */
	private int laenge;
	
	/**
	 * Wartezeit des Schiffes. 
	 */
	private int wartezeit;
	
	/**
	 * Feuerstärke des Schiffs. 
	 */
	private int feuerstaerke;
	
	/**
	 * Wie oft das Schiff getroffen wurde.
	 */
	private int treffer;
	
	
	/**
	 * Leerer Konstruktor.
	 * Je nach dem, welche Unterklasse gebaut wird, werden die eigenschaften gesetzt.
	 */
	public Schiff(){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			this.laenge = 4;
			this.feuerstaerke = 2;
			//ladezeit 2 Runden
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			this.laenge = 3;
			this.feuerstaerke = 1;
			//ladezeit 1 Runde
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			this.laenge = 2;
			this.feuerstaerke =1;
			//ladezeit 1 Runde
		}else{
			this.laenge = 5;
			this.feuerstaerke = 3;
			//ladezeit 3 Runden
		}
		felder = new Feld[this.laenge];
	}
	
		
	/**
	 * Konstruktor.
	 * @param position: Position auf dem das Schiff positioniert werden soll.
	 * @param horizontal: Angabe ob das Schiff horizontal positioniert oder vertikal positioniert werden soll.
	 */
	public Schiff(Position position, boolean horizontal){
		if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Fregatte")){
			this.laenge = 4;
			this.feuerstaerke = 2;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.Korvette")){
			this.laenge = 3;
			this.feuerstaerke = 1;
		}else if(this.getClass().getCanonicalName().equals("de.hs.bremen.model.UBoot")){
			this.laenge = 1;
			this.feuerstaerke =1;
		}else{
			this.laenge = 5;
			this.feuerstaerke = 3;
		}
		felder = new Feld[this.laenge];
	}
	
	public Schiff(Feld[] felder){
		this.felder = felder;
		this.felder = felder;
	}
	
	/**
	 * @param position
	 * @return
	 */
	public boolean getroffen(Position position){	
		for(int i = 0; i < felder.length; i++){
			if(felder[i].getPosition().equals(position)){
				felder[i].setGetroffen(true);
				treffer = treffer + 1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode, die wiedergibt ob, das Schiff getroffen wurde, und die jeweiligen Felder auf getroffen setzt.
	 * @param positionen: Positionen der Felder, die auf Treffer überprüft werden sollen. 
	 * @return: Boolean ob das Schiff getroffen wurde.
	 */
	public boolean getroffen(Position[] positionen){
		for(int i = 0; i < felder.length; i++){
			for(Position p : positionen){
				if(!felder[i].isGetroffen() && felder[i].getPosition().equals(p)){
					felder[i].setGetroffen(true);
					felder[i].setFeldstatus(Feldstatus.GETROFFEN);
					felder[i].setInhalt("x");
					treffer = treffer +1;
					return true;
				}
			}
		}
		return false;
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
	 * Angbabe ob, das Schoff versenkt wurde.
	 * @return: Schiff versenkt
	 */
	public boolean versenkt(){
		return treffer == felder.length;
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
	 * @param felder
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
	 * @param laenge
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
	 * @param wartezeit
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
	 * @param feuerstaerke
	 */
	public void setFeuerstaerke(int feuerstaerke) {
		this.feuerstaerke = feuerstaerke;
	}

	/**
	 * Zeigt an ob, das Schiff eine Runde aussetzten muss
	 * @return: rundeAussetzen
	 */
	public boolean rundeAussetzen(){
		return wartezeit > 0;
	}
	
	/**
	 * 
	 * @param position
	 */
	public void feuern(Position position){
		setWartezeit(feuerstaerke);
	}
}
