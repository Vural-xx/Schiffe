package de.hs.bremen.model;

/**
 * 
 * Klasse für Spieler.
 * @author vural
 *
 */
public class Spieler {

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
	
	/**
	 * Quadratisches Spielfeld, wird für diesen Spieler initiliasiert.
	 * @param groesse: Größe, die das quadratische Spielfeld sein soll.
	 */
	public void createSpielfeld(int groesse){
		spielfeld = new Spielfeld(groesse);
		spielfeldPublic = new Spielfeld(groesse);
	}

}
