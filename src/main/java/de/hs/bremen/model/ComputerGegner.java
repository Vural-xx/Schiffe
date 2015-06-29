package de.hs.bremen.model;

import java.util.ArrayList;

import de.hs.bremen.controller.MainController;

public class ComputerGegner extends Actor {
	private Actor[] gegner;
	
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
	public void schiffeSetzen(ArrayList <Schiff> schiffe){
		int zeile=0;
		int spalte=0;
		boolean horizontal;
		for(int i=0; i< schiffe.size(); i++){
			do{ 
			
				zeile=randomRechnerZeile();
				spalte=randomRechnerSpalte();
				System.out.println(zeile);
				System.out.println(spalte);
				System.out.println(ausrichtung());
				
				
			} while(!getSpielfeld().schiffPlazierbar(schiffe.get(i), new Position(spalte, zeile), ausrichtung()));
			if(ausrichtung() == 1){
				horizontal = true;
			}else{
				horizontal = false;
			}
			getSpielfeld().platziereSchiff(schiffe.get(i), new Position(spalte, zeile), horizontal);
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
		schiffZumSchießen().feuern(new Position(randomRechnerSpalte(),randomRechnerZeile()), gegner.getSpielfeldPublic());
		gegner.trefferUebertragung();
		
	}
	
	public void kiFeuern(Position position, Schiff schiff){
		
		
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
