package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Feldstatus;

public class ComputerGegner extends Actor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717880889204496799L;
	private Actor[] gegner;
	private ArrayList<Position> shots;
	
	public ComputerGegner(String name){
		super(name);
		shots= new ArrayList<Position>();
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
	

	public void schiffeSetzen(ArrayList <Schiff> schiffe){
		int zeile=0;
		int spalte=0;
		int hori=0;
		boolean horizontal=false;

		
		for(int i=0; i< schiffe.size(); i++){
			System.out.println(schiffe.get(i).getName());
			do {
				zeile= randomRechnerZeile();
				spalte= randomRechnerSpalte();
				hori=ausrichtung();
				System.out.println("zeile " +zeile);
				System.out.println("spalte " +spalte);
				System.out.println("Ausrichtung" +hori);
				
			}while(!getSpielfeld().schiffPlazierbar(schiffe.get(i), new Position(spalte, zeile), hori));
			if(hori == 1){
				horizontal = true;
			}else{
				horizontal = false;
			}
			this.getSpielfeld().platziereSchiff(schiffe.get(i), new Position(spalte, zeile), horizontal);
					
		}
	}
		
	
	
	public Schiff schiffZumSchießen(){
		Schiff schiff= null;
		if(getSpielfeld().schiffeOhneWarteZeit()){
			schiff = getSpielfeld().getSchiffe().get(0);
			for(int i=0; i <getSpielfeld().getSchiffe().size();i++){
				if(getSpielfeld().getSchiffe().get(i).getFeuerstaerke()>schiff.getFeuerstaerke() && schiff.getWartezeit()==0){
					schiff=getSpielfeld().getSchiffe().get(i);
				}
			}
		}
		return schiff;
	}
	
	
	public void intelligent(){
		int spalte=randomRechnerSpalte();
		int zeile= randomRechnerZeile();
		Position position= new Position(spalte, zeile);
		Schiff schiff= schiffZumSchießen();
		if(schiff != null){
			kiFeuern(position, schiff);
		}
	}
	
	public void kiFeuern(Position position, Schiff schiff){
		Actor gegner=spielerAuswahl();
		Position[] positionsarray= new Position[schiff.getFeuerstaerke()];
		for (int i=0; i< schiff.getFeuerstaerke(); i++ ){
			positionsarray[i]= new Position(position.getPositonX()+i, position.getPositionY());
		}
		schiff.feuern(positionsarray, gegner.getSpielfeldPublic());
		gegner.trefferUebertragung();
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
