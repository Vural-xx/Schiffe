package de.hs.bremen.model;

import java.util.ArrayList;

public class ComputerGegner extends Actor {
	
	
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
	
	
	public void intelligent(){
		/* If getroffen x y Position +2 jeweils abschießen*/
		
	}
	
	
	
	
}
