package de.hs.bremen.abstracts;

import java.io.Serializable;

import de.hs.bremen.model.Feld;
import de.hs.bremen.model.Position;


public abstract class AbstractSpielfeld implements Serializable {
	

	/**
	 * 
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
}
