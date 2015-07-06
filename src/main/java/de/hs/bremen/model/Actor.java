package de.hs.bremen.model;

import java.io.Serializable;

import de.hs.bremen.enums.Feldstatus;
import de.hs.bremen.enums.Spielerart;

/**
 * Abstrakte Klasse für Spieler
 * @author Christin
 *
 */
public abstract class Actor implements Serializable{
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 932678145597812910L;

	/**
	 * Name des Spielers
	 */
	private String name;
	
	/**
	 * Übergibt den Spieler, der an der Reihe ist
	 */
	private boolean istDran;
	
	/**
	 * Privates Spielfeld jedes Spielers
	 */
	private SpielfeldPrivate spielfeld;
	
	/**
	 * Spielerart: Computergegner oder Mensch
	 */
	private Spielerart spielerart;
	
	/**
	 * Öffentliches Spielfeld jedes Spielers
	 */
	private SpielfeldPublic spielfeldPublic;
	
	
	/**
	 * Konstruktor
	 * @param name: Name des Spielers.
	 */
	public Actor(){
	}
	public Actor(String name){
		this.name = name;
	}
	
	/**
	 * Getter SpielfeldPublic
	 * @return spielfeldPublic: öffentliches Spielfeld
	 */
	public SpielfeldPublic getSpielfeldPublic() {
		return spielfeldPublic;
	}

	/**
	 * Setter SpielfeldPublic
	 * @param spielfeldPublic: öffentliches Spielfeld
	 */
	public void setSpielfeldPublic(SpielfeldPublic spielfeldPublic) {
		this.spielfeldPublic = spielfeldPublic;
	}

	/**
	 * Getter Spielerart
	 * @return spielerart: Mensch oder Computer
	 */
	public Spielerart getSpielerart() {
		return spielerart;
	}

	/**
	 * Setter Spielerart
	 * @param spielerart: Mensch oder Computer
	 */
	public void setSpielerart(Spielerart spielerart) {
		this.spielerart = spielerart;
	}
	
	/**
	 * Getter Name.
	 * @return Name des Spielers.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Übergibt den aktuellen Spieler
	 * @return boolean: Welcher Spieler an der Reihe ist
	 */
	public boolean isIstDran() {
		return istDran;
	}

	/**
	 * Setz den aktuellen Spieler
	 * @param istDran: Welcher Spieler an der Reihe ist
	 */
	public void setIstDran(boolean istDran) {
		this.istDran = istDran;
	}
	
	/**
	 * Setter Name.
	 * @param name: Name des Spielers, welcher gesetzt werden soll.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter Spielfeld.
	 * @return: Spielfeld des Spielers.
	 */
	public SpielfeldPrivate getSpielfeld() {
		return spielfeld;
	}
	
	/**
	 * Setter Spielfeld.
	 * @param spielfeld: Spielfeld des Spielers, welches gesetzt werden soll.
	 */
	public void setSpielfeld(SpielfeldPrivate spielfeld) {
		this.spielfeld = spielfeld;
	}
		
	/**
	 * Übergibt, welches Schiff keine Wartezeit hat
	 * @return true oder false
	 */
	public boolean schiffeOhneWartezeit(){
		for(int i=0; i< getSpielfeld().getSchiffe().size(); i++){
			if(getSpielfeld().getSchiffe().get(i).getWartezeit() == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Quadratisches Spielfeld, wird für den Spieler initiliasiert.
	 * @param groesse: Größe, die das quadratische Spielfeld haben soll.
	 */
	public void createSpielfeld(int groesse){
		spielfeld = new SpielfeldPrivate(groesse);
		spielfeldPublic = new SpielfeldPublic(groesse);
	}
	
	
	/**
	 * Entfernt das getroffene Schiff, setzt Feldstatus auf versenkt
	 * @param position: Position, wo das schiff versenkt wurde
	 */
	public void schiffVersenkt(Position position){
		Schiff schiff= getSpielfeld().getSchiffByPosition(position);
		if(schiff != null && schiff.versenkt()){
			for(int i=0; i < schiff.getFelder().length; i++){
				schiff.getFelder()[i].setFeldstatus(Feldstatus.VERSENKT);
				this.getSpielfeldPublic().getFeld(schiff.getFelder()[i].getPosition().getPositonX()-1, schiff.getFelder()[i].getPosition().getPositionY()-1).setFeldstatus(Feldstatus.VERSENKT);
			}
			getSpielfeld().getSchiffe().remove(schiff);
		}	
	}
	
	/**
	 * Überprüft, ob ein schiff ausgeschieden ist
	 * @return ausgeschiedenes Schiff
	 */
	public boolean ausgeschieden(){
		return spielfeld.getSchiffe().size()==0;
	}
	
	
	/**
	 * Übertragung der getroffenen Schiffe vom Spielfeld auf das SpielfeldPublic
	 */
	public void trefferUebertragung(){
		for(int i = 0; i<spielfeld.getFelder().length; i++){
			for(int j = 0; j<spielfeld.getFelder()[i].length; j++){
				if(spielfeldPublic.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT && spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.BESETZT){
					spielfeldPublic.getFelder()[i][j].setFeldstatus(Feldstatus.GETROFFEN);
					spielfeld.getFelder()[i][j].setFeldstatus(Feldstatus.GETROFFEN);

					schiffVersenkt(new Position(j+1, i+1));	
				}else if (spielfeldPublic.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT && spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.WASSER){
					spielfeld.getFelder()[i][j].setFeldstatus(Feldstatus.VERFEHLT);
				}
			}
		}
	}
}


