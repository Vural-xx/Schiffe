package de.hs.bremen.abstracts;

import java.io.Serializable;

import de.hs.bremen.model.Feld;
import de.hs.bremen.model.Position;


/**
 * Abstrakte Klasse des Spielfelds
 * @author Christin
 *
 */
public abstract class AbstractSpielfeld implements Serializable {
	

	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = -3004612619721063907L;


	/**
	 * Felder des Spielfelds.
	 */
	protected Feld [][] felder;
	
	/**
	 * Konstruktor, welche die Felder auf dem Spielfeld initialisiert.
	 * @param groesse: Größe des Spielfelds
	 */
	public AbstractSpielfeld(int groesse){
		createFelder(groesse);
	}
	
	/**
	 * Getter der Felder
	 * @return felder
	 */
	public Feld[][] getFelder() {
		return felder;
	}
	
	/**
	 * Setter der Felder
	 * @param felder: Setzt die Felder
	 */
	public void setFelder(Feld[][] felder) {
		this.felder = felder;
	}
	
	/**
	 * Getter der Spielfelds
	 * @return: Größe des Spielfelds
	 */
	public int getSpielfeldgroesse(){
		return felder.length;
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
	 * Übergibt ein bestimmtes Feld
	 * @param x : X-Koordinate des Felds, welches gesucht werden soll.
	 * @param y : Y-Koordinate des Felds, welches gesucht werden soll.
	 * @return : Feld, welches mit den Parametern übereinstimmt.
	 */
	public Feld getFeld(int x, int y){
		return felder[y][x];
	}
	
	
	
}
