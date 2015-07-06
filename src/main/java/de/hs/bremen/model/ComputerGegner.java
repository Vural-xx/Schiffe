package de.hs.bremen.model;

import java.awt.event.ItemEvent;
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
	private int tempZeile=0;
	private int tempSpalte=0;
	
	public ComputerGegner(String name){
		super(name);
		shots= new ArrayList<Position>();
	}
	
	
	public int randomRechnerZeile(){
		int spalte= (int)(Math.random()*5)+1;
		return spalte;
	}
	
	public int randomRechnerSpalte(){
		int spalte= (int)(Math.random()*5)+1;
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
			do {
				zeile= randomRechnerZeile();
				spalte= randomRechnerSpalte();
				hori=ausrichtung();

				
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
		int feuerstaerke=0;
		Schiff schiff= null;
		if(getSpielfeld().schiffeOhneWarteZeit()){
			for(int i=0; i <getSpielfeld().getSchiffe().size();i++){
				if(getSpielfeld().getSchiffe().get(i).getFeuerstaerke()>feuerstaerke && getSpielfeld().getSchiffe().get(i).getWartezeit()==0){
					feuerstaerke= getSpielfeld().getSchiffe().get(i).getFeuerstaerke();
					schiff=getSpielfeld().getSchiffe().get(i);
				}
			}
		}
		return schiff;
	}
	
	
	public void intelligent(){
		Schiff schiff= schiffZumSchießen();
		Schiff tempschiff;
		int spalte;
		int zeile;

		Position position;
			do{
				spalte=randomRechnerSpalte();
				zeile=randomRechnerZeile();
				position=new Position(spalte, zeile);
			}while(!gegner[0].getSpielfeldPublic().schussPlatzierbar(schiff, position)&& gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.WASSER && gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.BESETZT &&gegner[0].getSpielfeld().getFeld(zeile-1, spalte-1).getFeldstatus()==Feldstatus.GETROFFEN);
		if(schiff != null){
			if(wennGetroffen(schiff, spalte, zeile)){
				tempschiff=schiff;
				tempSpalte=tempSpalte+tempschiff.getFeuerstaerke();
				kiFeuern(new Position(tempSpalte, tempZeile), tempschiff);
			} else {
				kiFeuern(position, schiff);
			}
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
	
	public boolean wennGetroffen(Schiff schiff, int spalte, int zeile){
		System.out.println(spalte);
		System.out.println(zeile);
		int counter=0;
		int test=zeile+schiff.getFeuerstaerke();
		int feuerstaerke= schiff.getFeuerstaerke();
		for(int i=0; i< schiff.getFeuerstaerke(); i++){
			System.out.println(((zeile)+i) +"blabla");

			System.out.println((zeile+schiff.getFeuerstaerke()));

			if(((test< getSpielfeld().getSpielfeldgroesse()) && gegner[0].getSpielfeld().getFeld(((spalte-1)+i),(zeile-1)).getFeldstatus()==Feldstatus.BESETZT)){
	
				
				System.out.println("AHAHAHAHAHH");
				counter++;
				tempSpalte=spalte;
				tempZeile=zeile;
				if(counter<= schiff.getFeuerstaerke()){
					return true;

				}
			}	
		}
		return false;	
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
