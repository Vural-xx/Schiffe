package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Feldstatus;

public class ComputerGegner extends Actor {
	private Actor[] gegner;
	private ArrayList<Position> shots;
	private int zeile;
	private int spalte;
	private int hori;
	
	public ComputerGegner(String name){
		super(name);
	}
	
	
	public int randomRechnerZeile(){
		int spalte= (int)(Math.random()*getSpielfeld().getSpielfeldgroesse())+1;
		return spalte;
	}
	
	public int randomRechnerSpalte(){
		int spalte= (int)(Math.random()*getSpielfeld().getSpielfeldgroesse())+1;
		return spalte;	
	}
	
	public int ausrichtung(){
		int ausrichtung=(int)(Math.random()*2)+1;
		return ausrichtung;
	}
	
	public void rechner(){

		zeile=randomRechnerZeile();
		spalte=randomRechnerSpalte();
		hori=ausrichtung();
		System.out.println("X"+spalte);
		System.out.println("Y"+zeile);
		System.out.println("hori"+hori);
	}
	public void schiffeSetzen(ArrayList <Schiff> schiffe){
		int zeile=0;
		int spalte=0;
		int hori=0;
		boolean horizontal=false;
			if(hori == 1){
				horizontal = true;
			}
		
		for(int i=0; i< schiffe.size(); i++){
			System.out.println(schiffe.get(i));
			if (getSpielfeld().schiffPlazierbar(schiffe.get(i), new Position(spalte, zeile), hori)){
				this.getSpielfeld().platziereSchiff(schiffe.get(i), new Position(spalte, zeile), horizontal);
					
			} else {
				rechner();
			}
		}
	}	
	
	public Schiff schiffZumSchießen(){
		Schiff schiff=getSpielfeld().getSchiffe().get(0);
		for(int i=0; i <getSpielfeld().getSchiffe().size();i++){
				if(getSpielfeld().getSchiffe().get(i).getFeuerstaerke()>schiff.getFeuerstaerke()){
					schiff=getSpielfeld().getSchiffe().get(i);
			}
		}
		System.out.println(schiff);
		return schiff;
	}
	
	
	public void intelligent(){
		Actor gegner= spielerAuswahl();
		int spalte=randomRechnerSpalte();
		int zeile= randomRechnerZeile();
		Position position= new Position(spalte, zeile);
		Schiff schiff= schiffZumSchießen();
		schiff.feuern(position, gegner.getSpielfeldPublic());
		kiFeuern(position, schiff);
		gegner.trefferUebertragung();
		
	}
	
	public void kiFeuern(Position position, Schiff schiff){
		int spalte=randomRechnerSpalte();
		int zeile=randomRechnerZeile();
		Actor spieler=spielerAuswahl();
		schiff.feuern(new Position(spalte,zeile), spieler.getSpielfeldPublic());
		spieler.trefferUebertragung();
		for(int i=0; i< schiff.getFeuerstaerke(); i++){
			shots.add(new Position(position.getPositonX()+i, position.getPositionY()));
			
		}
	}
	
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
	
	public Actor spielerAuswahl(){
		int anzahlSpieler = this.gegner.length;
		Actor gegner;
		do {
		gegner= this.gegner[(int)(Math.random()*anzahlSpieler)];
		}while (gegner.getName().equals(this.getName()));
		return gegner;
	}


	public Actor[] getGegner() {
		return gegner;
	}


	public void setGegner(Actor[] gegner) {
		this.gegner = gegner;
	}
	
	
	
	
}
