package de.hs.bremen.model;

import java.io.Serializable;

import de.hs.bremen.enums.Feldstatus;
import helper.IO;

/**
 * 
 * Klasse für Spieler.
 * @author vural
 *
 */
public class Spieler implements Serializable {

	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3960254885515461884L;


	/**
	 * Name des Spielers.
	 */
	private String name;
	
	
	/**
	 * Jeder Spieler hat ein Spielfeld.
	 */
	private Spielfeld spielfeld;
	
	/**
	 * Öffentliches Spielfeld jedes Spielers
	 */
	private Spielfeld spielfeldPublic;
	
	public Spielfeld getSpielfeldPublic() {
		return spielfeldPublic;
	}

	public void setSpielfeldPublic(Spielfeld spielfeldPublic) {
		this.spielfeldPublic = spielfeldPublic;
	}

	/**
	 * Indikator ob der Benutzer noch im Spiel ist.
	 */
	private boolean ausgeschieden;
	
	/**
	 * Konstruktor
	 * @param name: Name des Spielers.
	 */
	public Spieler(String name){
		this.name = name;
	}
	
	/**
	 * Getter Name.
	 * @return Name des Spielers.
	 */
	public String getName() {
		return name;
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
	public Spielfeld getSpielfeld() {
		return spielfeld;
	}
	
	/**
	 * Setter Spielfeld.
	 * @param spielfeld: Spielfeld des Spielers, welches gesetzt werden soll.
	 */
	public void setSpielfeld(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}
	
	/**
	 * Getter ausgeschieden.
	 * @return: ausgeschieden.
	 */
	public boolean isAusgeschieden() {
		return ausgeschieden;
	}
	
	/**
	 * Setter ausgeschieden
	 * @param ausgeschieden: ausgeschieden.
	 */
	public void setAusgeschieden(boolean ausgeschieden) {
		this.ausgeschieden = ausgeschieden;
	}
	
	public boolean schiffeOhneWartezeit(){
		for(int i=0; i< getSpielfeld().getSchiffe().size(); i++){
			if(getSpielfeld().getSchiffe().get(i).getWartezeit() == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Quadratisches Spielfeld, wird für diesen Spieler initiliasiert.
	 * @param groesse: Größe, die das quadratische Spielfeld sein soll.
	 */
	public void createSpielfeld(int groesse){
		spielfeld = new Spielfeld(groesse);
		spielfeldPublic = new Spielfeld(groesse);
	}
	
	public void trefferUebertragung(){
		for(int i = 0; i<spielfeld.getFelder().length; i++){
			for(int j = 0; j<spielfeld.getFelder()[i].length; j++){
				if(spielfeld.getFelder()[i][j].getFeldstatus() != Feldstatus.WASSER 
					&& spielfeld.getFelder()[i][j].getFeldstatus() != Feldstatus.BESETZT 
					&& spielfeld.getFelder()[i][j].getFeldstatus() != spielfeldPublic.getFelder()[i][j].getFeldstatus()){
						spielfeld.getFelder()[i][j].setFeldstatus(spielfeldPublic.getFelder()[i][j].getFeldstatus());
				}else if(spielfeldPublic.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT && spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.BESETZT){
					spielfeldPublic.getFelder()[i][j].setFeldstatus(Feldstatus.GETROFFEN);
					System.out.println("Das Ziel wurde getroffen");
				}else if (spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT){
					System.out.println ("Das Ziel wurde VERFEHLT");
				}
			}
		}
	}

}
