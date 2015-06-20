package de.hs.bremen.model;

public class ComputerGegner {

	
	private String ComputerName= "KIGegner";
	
	private Spielfeld ComputerSpielfeld;
	private Spielfeld ComputerSpielfeldPublic;
	
	
	public Spielfeld getComputerSpielfeld() {
		return ComputerSpielfeld;
	}
	public void setComputerSpielfeld(Spielfeld computerSpielfeld) {
		ComputerSpielfeld = computerSpielfeld;
	}


	
	public Spielfeld getComputerSpielfeldPublic() {
		return ComputerSpielfeldPublic;
	}
	public void setComputerSpielfeldPublic(Spielfeld computerSpielfeldPublic) {
		ComputerSpielfeldPublic = computerSpielfeldPublic;
	}
	
	
	public ComputerGegner(String ComputerName){
		this.ComputerName= ComputerName;
	}
	
	public String getName() {
		return ComputerName;
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
