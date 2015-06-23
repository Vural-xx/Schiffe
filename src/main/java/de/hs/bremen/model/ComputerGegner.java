package de.hs.bremen.model;

public class ComputerGegner extends actor {
	
	
	public ComputerGegner(String name){
		super(name);
	}
	
	
	
	public int randomRechnerZeile(){
		int spalte= (int)(Math.random()*2/*getSpielfeldgröße*/);
		return spalte;
	}
	
	public int randomRechnerSpalte(){
		int spalte= (int)(Math.random()*2/*getSpielfeldgröße*/);
		return spalte;	
	}
	
	public void intelligent(){
		/* If getroffen x y Position +2 jeweils abschießen*/
		
	}
	
	
	
	
}
