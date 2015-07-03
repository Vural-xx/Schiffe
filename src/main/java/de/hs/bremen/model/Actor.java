package de.hs.bremen.model;

import de.hs.bremen.enums.Feldstatus;
import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.helper.ConsoleColor;

public abstract class Actor {
	/**
	 * Name des Spielers.
	 */
	private String name;
	
	private boolean istDran;
	
	/**
	 * Jeder Spieler hat ein Spielfeld.
	 */
	private SpielfeldPrivate spielfeld;
	
	private Spielerart spielerart;
	
	/**
	 * Öffentliches Spielfeld jedes Spielers
	 */
	private SpielfeldPublic spielfeldPublic;
	
	
	/**
	 * Konstruktor
	 * @param name: Name des Spielers.
	 */
	public Actor(){
	}
	public Actor(String name){
		this.name = name;
	}
	
	public SpielfeldPublic getSpielfeldPublic() {
		return spielfeldPublic;
	}

	public void setSpielfeldPublic(SpielfeldPublic spielfeldPublic) {
		this.spielfeldPublic = spielfeldPublic;
	}

	public Spielerart getSpielerart() {
		return spielerart;
	}

	public void setSpielerart(Spielerart spielerart) {
		this.spielerart = spielerart;
	}
	

	
	/**
	 * Getter Name.
	 * @return Name des Spielers.
	 */
	public String getName() {
		return name;
	}
	
	public boolean isIstDran() {
		return istDran;
	}

	public void setIstDran(boolean istDran) {
		this.istDran = istDran;
	}
	
	/**
	 * Setter Name.
	 * @param name: Name des Spielers, welcher gesetzt werden soll.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter Spielfeld.
	 * @return: Spielfeld des Spielers.
	 */
	public SpielfeldPrivate getSpielfeld() {
		return spielfeld;
	}
	
	/**
	 * Setter Spielfeld.
	 * @param spielfeld: Spielfeld des Spielers, welches gesetzt werden soll.
	 */
	public void setSpielfeld(SpielfeldPrivate spielfeld) {
		this.spielfeld = spielfeld;
	}
		
	public boolean schiffeOhneWartezeit(){
		for(int i=0; i< getSpielfeld().getSchiffe().size(); i++){
			if(getSpielfeld().getSchiffe().get(i).getWartezeit() == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Quadratisches Spielfeld, wird für diesen Spieler initiliasiert.
	 * @param groesse: Größe, die das quadratische Spielfeld sein soll.
	 */
	public void createSpielfeld(int groesse){
		spielfeld = new SpielfeldPrivate(groesse);
		spielfeldPublic = new SpielfeldPublic(groesse);
	}
	
	
	public void schiffVersenkt(Position position){
		Schiff schiff= getSpielfeld().getSchiffByPosition(position);
		if(schiff != null && schiff.versenkt()){
			for(int i=0; i < schiff.getFelder().length; i++){
				schiff.getFelder()[i].setFeldstatus(Feldstatus.VERSENKT);
				this.getSpielfeldPublic().getFeld(schiff.getFelder()[i].getPosition().getPositonX()-1, schiff.getFelder()[i].getPosition().getPositionY()-1).setFeldstatus(Feldstatus.VERSENKT);
			}
			getSpielfeld().getSchiffe().remove(schiff);
			
			System.out.println("");
			System.out.println(ConsoleColor.ANSI_PURPLE +"=====================================================================");
			System.out.println("||||   Sie haben das Schiff "+ schiff.getName().toUpperCase() + " vom Spieler " + getName() + " versenkt!!!!   ||||");
			System.out.println("====================================================================="+ConsoleColor.ANSI_RESET);
		}	
	}
	
	public boolean ausgeschieden(){
		return spielfeld.getSchiffe().size()==0;
	}
	
	
	public void trefferUebertragung(){
		for(int i = 0; i<spielfeld.getFelder().length; i++){
			for(int j = 0; j<spielfeld.getFelder()[i].length; j++){
				if(spielfeldPublic.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT && spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.BESETZT){
					spielfeldPublic.getFelder()[i][j].setFeldstatus(Feldstatus.GETROFFEN);
					spielfeld.getFelder()[i][j].setFeldstatus(Feldstatus.GETROFFEN);
					System.out.println(ConsoleColor.ANSI_GREEN+"--------------------------");
					System.out.println("DAS ZIEL WURDE GETROFFEN!!");
					System.out.println("--------------------------"+ConsoleColor.ANSI_RESET);
					schiffVersenkt(new Position(j+1, i+1));	
				}else if (spielfeldPublic.getFelder()[i][j].getFeldstatus() == Feldstatus.VERFEHLT && spielfeld.getFelder()[i][j].getFeldstatus() == Feldstatus.WASSER){
					spielfeld.getFelder()[i][j].setFeldstatus(Feldstatus.VERFEHLT);
					System.out.println(ConsoleColor.ANSI_RED+"--------------------------"+ConsoleColor.ANSI_RESET);
					System.out.println (ConsoleColor.ANSI_RED+"DAS ZIEL WURDE VERFEHLT!!"+ConsoleColor.ANSI_RESET);
					System.out.println(ConsoleColor.ANSI_RED+"--------------------------"+ConsoleColor.ANSI_RESET);
				}
			}
		}
	}
}


