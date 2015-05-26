package de.hs.bremen.model;

import de.hs.bremen.helper.IO;

public class ComputerGegner {
	Spielfeld test = new Spielfeld();
	public int SpielfeldGroesse=0;
	
	public int getSpielfeldGroesse() {
		return SpielfeldGroesse;
	}

	public void setSpielfeldGroesse(int spielfeldGroesse) {
		SpielfeldGroesse = spielfeldGroesse;
	}

	public ComputerGegner(){
		
	}
	
	// Berechnet Random die Zeile
	public int zeilenBerechner(){
		int zeile=0;
		zeile= (int)(Math.random()*5);
		return zeile;
	}
	// Berechnet Random die Spalte
	public int spaltenBerechner(){
		int spalte=0;
		spalte= (int)(Math.random()*SpielfeldGroesse);
		return spalte;
	}
	
	public int ausrichtungBerechner(){
		int ausrichtung=0;
		ausrichtung= (int)(Math.random()*2);
		return ausrichtung;
	}
	
	

}
