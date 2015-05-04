package de.hs.bremen.model;

import helper.IO;

/**
 * Klasse für Spielfelder des Spielers auf dem die Schiffe platziert werden.
 * @author vural
 *
 */
public class Spielfeld {
	
	/**
	 * Felder des Spielfelds.
	 */
	private Feld [][] felder;
	
	
	/**
	 * Schiffe auf dem Spielfeld
	 */
	private Schiff [] schiffe;
	
	/**
	 * Konstruktor, welche die Felder auf dem Spielfeld initialisiert.
	 * @param groesse: Größe des Spielfelds
	 */
	public Spielfeld(int groesse){
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
	 * Getter Schiffe.
	 * @return schiffeArray
	 */
	public Schiff[] getSchiffe() {
		return schiffe;
	}
	
	/**
	 * Setter schiffe
	 * @param schiffe
	 */
	public void setSchiffe(Schiff[] schiffe) {
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
				felder[position.getPositionY()-1][position.getPositonX()-1+i].setInhalt(Spiel.ANSI_RED+"s" + Spiel.ANSI_RESET);
				f[i] = felder[position.getPositionY()-1][position.getPositonX()-1+i];
			}else if (horizontal==2){
				felder[position.getPositionY()-1+i][position.getPositonX()-1].setInhalt(Spiel.ANSI_RED+"s" + Spiel.ANSI_RESET);
				f[i] = felder[position.getPositionY()-1+i][position.getPositonX()-1];
			}
		}
		schiff.setFelder(f);
	}
	
	/**
	 * Treffer wird auf dem Spielfeld vermerkt.
	 * @param positionen: Position auf dem der Treffer vermerkt werden soll.
	 */
	public void trefferPlatzieren(Position[] positionen){
		for(Position p: positionen){
			getFeld(p.getPositonX(), p.getPositionY()).setGetroffen(true);
		}
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
		public boolean besetzt(Position position){
			boolean feldBesetzt = false;
			for (Feld[] f: felder) {
				for (Feld ff: f) {
					feldBesetzt =  ff.getPosition().equals(position);
				}
			}
			return feldBesetzt;
		}
	
}
