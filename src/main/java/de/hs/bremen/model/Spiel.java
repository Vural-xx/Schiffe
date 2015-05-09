package de.hs.bremen.model;


import java.lang.reflect.Array;
import java.util.ArrayList;








import java.util.Arrays;

import de.hs.bremen.enums.Feldstatus;
import helper.IO;

/**
 * Klasse für Spiellogik.
 * @author vural
 *
 */
public class Spiel {
	/**
	 * Farben um die Konsolenausgabe farblich zu gestalten.
	 */
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private int maximaleSpieleranzahl= 6;
	private int maximaleSpielfeldgroesse= 40;
	private int minimaleSpielfeldgroesse= 20;
	private int auswahlZahl;
	boolean vertikalHorizontal= false;
	private int ausrichtung;
	private int spalte;
	private int zeile;
	private int anzahlSchiffe;
	private int anzahlSchiffeGezeugt;
	private int schiffTyp;
	private ArrayList<Schiff> schiffe = new ArrayList<Schiff>();
	
	/**
	 * Spieler die an dem Spiel beteiligt sind.
	 */
	private Spieler[] spieler;
	
	/**
	 * Runden die gespielt wurden.
	 */
	private int runde;
	
	/**
	 * Getter.
	 * @return spieler Array
	 */
	public Spieler[] getSpieler() {
		return spieler;
	}
	
	/**
	 * Setter.
	 * @param spieler: spieler Array
	 */
	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}
	
	/**
	 * Getter
	 * @return runde
	 */
	public int getRunde() {
		return runde;
	}
	
	/**
	 * Setter
	 * @param runde
	 */
	public void setRunde(int runde) {
		this.runde = runde;
	}
	
	/**
	 * Spieler werden initialisert und deren Namen werden gesetzt.
	 * @param anzahl: Anzahl der Spieler die initialisiert werden sollen.
	 */
	public void createSpieler(int anzahl){
		String name;
		if(anzahl <= maximaleSpieleranzahl && anzahl > 1){
		spieler = new Spieler[anzahl];
			for(int i = 1; i <= anzahl; i++){
				System.out.println("Spieler Nummer " +i+" bitte geben Sie ihren Namen an");
				name = IO.readString();
				spieler[i-1] = new Spieler(name);
			}
		} else {
			System.out.println("Ihre Spieleranzahl ist zu hoch oder ihre Eingabe wurde nicht erkannt.");
			System.out.println("Bitte geben Sie erneut die Spieleranzahl ein:");
			createSpieler(IO.readInt());
		}
	}
	
	/**
	 * Spielfelder für die einzelnen Spieler werden erstellt
	 * @param groesse: Größe der Spielfelder
	 */
	public void createSpielfelder(int groesse){
		if(groesse >= minimaleSpielfeldgroesse && groesse <= maximaleSpielfeldgroesse){
		for(Spieler sp : spieler){
			sp.createSpielfeld(groesse);
		}
		} else {
			System.out.println("Ihre Spielfeldgröße ist zu groß zu niedrig oder ihre Eingabe wurde nicht erkannt.");
			System.out.println("Bitte geben Sie erneut die Spielergröße ein:");
			createSpielfelder(IO.readInt());
		}
	}
	

	
	public int schiffeAuswahlGueltig(int auswahlZahl){
		if(auswahlZahl <=5 && auswahlZahl >=1){		
			return auswahlZahl;
		} else {
			System.out.println("Falsche Einngabe versuchen Sie es erneut:");
			schiffeAuswahlGueltig(IO.readInt());
		}
		return auswahlZahl;
	}
	
	public int getAuswahlZahl() {
		return auswahlZahl;
	}

	public void setAuswahlZahl(int auswahlZahl) {
		this.auswahlZahl = auswahlZahl;
	}
	
	
	/**
	 * Funktion, ob Schiffe Horizontal oder Vertikal angeordnet werden sollen.
	 */
	
	public int vertikalHorizontal(int richtungsAbfrage){
		if(richtungsAbfrage== 1){
			ausrichtung = 1;
		} else if (richtungsAbfrage == 2){
		 	ausrichtung = 2;
		} else {
			System.out.println("Falsche Eingabe versuchen Sie es erneut:");
			vertikalHorizontal(IO.readInt());
		}
		return ausrichtung;

	}
	
	/**
	 * Funktion, die Schiffe pro Spieler auf deren Spielfeldern platziert.
	 */
	public void schiffePlatzieren(){
		menueSchiffartAuswahl();
		schiffeAufFeldSetzen();
	}
	
	public void spielen(){
		// ToDo: richtige Bedingung implementieren
		while(1<10){
			rundeSpielen();
		}
	}
	
	public void rundeSpielen(){
		int auswahl;
		Schiff schiff;
		int zeile;
		int spalte;
		Spieler gegner = null;
		for(int i = 0; i < spieler.length; i++){
			schiff = null;
			System.out.println("Spieler " +spieler[i].getName()+". Bitte wählen Sie ein Schiff mit dem Sie feuern wollen.");
			System.out.println(spieler[i].getSpielfeld().printSchiffeMenu());
			auswahl = IO.readInt();
			schiff = spieler[i].getSpielfeld().getZustaendigesSchiff(auswahl);
			if(spieler.length > 2){
				gegner = spielerAuswahlMenu(spieler[i]);
			}else if (i == 0){
				gegner = spieler[1];
			}else{
				gegner = spieler[0];
			}
			gegner.getSpielfeld().printSpielfeld();
			System.out.println(spieler[i].getName() +",Bitte geben Sie an wo Sie einen Schuß platzieren wollen");
			System.out.println(spieler[i].getName() +", Bitte geben Sie an in welche Zeile sie schießen wollen");
			zeile = IO.readInt();
			System.out.println(spieler[i].getName() +", Bitte geben Sie an in welche Spalte Sie schießen wollen");
			spalte = IO.readInt();
			schiff.feuern(new Position(zeile, spalte), spieler[i].getSpielfeld());
		}
	}
	
	
	/**
	 * Gibt je nach Auswahl des Spielers, die schiffsunterklasse wieder.
	 * @param auswahl: Auswahl des Spielers.
	 * @return: Passende Schiffsunterklasse
	 */
	public Schiff schiffeAuswahl(int auswahlZahl){
		switch (auswahlZahl) {
		case 1:
			return new Zerstoerer();
		case 2:
			return new Fregatte();		
		case 3: 
			return new Korvette();
		default:
			return new UBoot();
		}	
	}
	
	/**
	 * Gibt wieder, ob das im Parameter übergebene Schiff, noch auf das Spielfeld des Spielers passt.
	 * @param spieler: Spieler welches Spielfeld überprüft werden soll
	 * @param schiff: Schiff, dessen Länge überprüft werden soll
	 * @return: Passend oder nicht
	 */
	public boolean anzahlSchiffePassend(Spieler spieler, Schiff schiff, int anzahlSchiffe){
		boolean passend = spieler.getSpielfeld().getPlaetzeBelegt() + schiff.getPlaetzeBelegung()* anzahlSchiffe < (spieler.getSpielfeld().getSpielfeldgroesse() * spieler.getSpielfeld().getSpielfeldgroesse());
		if(!passend){
			System.out.println("Dieses Schiff passt leider nicht mehr auf ihr Spielfeld. Bitte wählen Sie ein anderes");
		}
		return passend;
	}
	
	/**
	 * Überprüft ob das Schiff innerhalb des Spielfeld plaziert wird
	 * @param position
	 * @return
	 */
	public boolean innerhalbSpielfeld (Position position){
		if (position.getPositonX() <=0|| position.getPositonX()>= spieler[0].getSpielfeld().getSpielfeldgroesse() && position.getPositionY()<=0 || position.getPositionY()>= spieler[0].getSpielfeld().getSpielfeldgroesse()){
			System.out.println("Das Schiff liegt außerhalb des Spielfeldes und darf hier nicht plaziert werden!");
			return false;
		}else {
			return true;
		}
		
	}
	

	public Spieler spielerAuswahlMenu(Spieler s){
		System.out.println("Bitte wählen Sie den Spieler auf dessen Spielfeld Sie feuern möchten");
		String spielerMenu ="Drücken Sie die ";
		int auswahl;
		int auswahlInMenu;
		for(int i = 0 ; i < spieler.length; i++){
			if(!s.getName().equals(spieler[i].getName())){
				auswahlInMenu = i+1;
				spielerMenu =spielerMenu+ auswahlInMenu +" für Spieler " + spieler[i].getName()+", " ;
			}
		}
		System.out.println(spielerMenu.substring(0, spielerMenu.length()-2));
		auswahl = IO.readInt();
		return spieler[auswahl-1];
		
	}
	

	/**
	 * setzt die umliegenden felder auf besetzt, wo andere schiffe nicht plaziert werden dürfen
	 * @param feldstatus
	 * @param position
	 */
	
	public void menueSchiffartAuswahl(){
		do{
			System.out.println("Bitte geben Sie an was für eine Art von Schiff auf dem Spielfeld platziert werden soll.");
			System.out.println("[1 für "+ Spiel.ANSI_PURPLE + "Zerstörer" +Spiel.ANSI_RESET+ ", 2 für "+ Spiel.ANSI_YELLOW + "Fregatte" +Spiel.ANSI_RESET+", 3 für "+ Spiel.ANSI_RED + "Korvette" +Spiel.ANSI_RESET+  ", 4 für "+ Spiel.ANSI_GREEN + "Uboot" +Spiel.ANSI_RESET+ ", 5 keine weiteren]");
			schiffTyp = schiffeAuswahlGueltig(IO.readInt());
			if(schiffTyp !=5){
				System.out.println("Wieviele Schiffe von dem Schifftyp " + schiffeAuswahl(schiffTyp).getName()+" sollen gesetzt werden");
				anzahlSchiffe = IO.readInt();
				if (anzahlSchiffePassend(spieler[0],schiffeAuswahl(schiffTyp), anzahlSchiffe)){
					for(int i= 0 ; i < anzahlSchiffe; i++){
						schiffe.add(schiffeAuswahl(schiffTyp));
					}
				}else{
					System.out.println("Soviele Schiffe von dem Schifftyp " +schiffeAuswahl(schiffTyp).getName()+ "passen nicht auf ihr Spielfeld");
				}	
			}
		}while(schiffTyp != 5 && spieler[0].getSpielfeld().getMaximumAnzahlSchiffe() > schiffe.size());
		
	}
	
	
	public void schiffeAufFeldSetzen(){
		for (int i = 0 ; i< spieler.length; i++){		
			anzahlSchiffeGezeugt = 0;		 
			do {				
				System.out.println(spieler[i].getName() +",Bitte geben Sie an wo Sie " +schiffe.get(anzahlSchiffeGezeugt).getName()+ " auf ihrem Spielfeld platzieren wollen.");
				System.out.println(spieler[i].getName() +", Bitte geben Sie an in welcher Zeile ihr Schiff platziert werden soll");
				zeile = IO.readInt();
				System.out.println(spieler[i].getName() +", Bitte geben Sie an in welcher Spalte ihr Schiff platziert werden soll");
				spalte = IO.readInt();
				System.out.println(spieler[i].getName() +", Bitte geben Sie an ob ihr Schiff 1. Horizontal oder 2. Vertikal angeorndet werden soll");
				ausrichtung = vertikalHorizontal(IO.readInt());
				if (innerhalbSpielfeld(new Position(spalte, zeile)) && schiffPlazierbar(schiffe.get(anzahlSchiffeGezeugt), new Position(spalte, zeile), spieler[i], ausrichtung)){
					spieler[i].getSpielfeld().platziereSchiff(schiffe.get(anzahlSchiffeGezeugt), new Position(spalte, zeile), ausrichtung);
					spieler[i].getSpielfeld().printSpielfeld();
					anzahlSchiffeGezeugt++;
				}
			}while(anzahlSchiffeGezeugt != schiffe.size());	
			ArrayList<Schiff> tempArrayList = new ArrayList<Schiff>();
			
			for(int j = 0; j <schiffe.size(); j++){	
				switch (schiffe.get(j).getName()) {
					case "Fregatte":
						tempArrayList.add(new Fregatte());
						break;
					case "Korvette":
						tempArrayList.add(new Korvette());
						break;
					case "UBoot":
						tempArrayList.add(new UBoot());
						break;
					default:
						tempArrayList.add(new Zerstoerer());
						break;
					}
			}
			schiffe = tempArrayList;
		}
	}
	
	public boolean schiffPlazierbar(Schiff schiff, Position position, Spieler spieler, int horizontal){
		Position[] positionen = new Position[schiff.getLaenge()];
		positionen[0] = position;
		for(int i = 1; i< positionen.length; i++){
			if(horizontal ==1){
				positionen[i] = new Position(position.getPositonX()+i, position.getPositionY());
			}else{
				positionen[i] = new Position(position.getPositonX(), position.getPositionY()+i);
			}
		}
		ArrayList<Position> puffer = new ArrayList<Position>();
		for(int i = 0; i< positionen.length; i++){
			if(horizontal == 1){
				if(i==0){
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()+1));
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()));
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()+1));
				} else if (i == positionen.length-1){
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()+1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()+1));
				}else{
					puffer.add(new Position(position.getPositonX(), position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()+1));
				}
				
			}else{
				if(i==0){
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()-1));
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()));
				} else if (i == positionen.length-1){
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()));
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()+1));
					puffer.add(new Position(position.getPositonX(), position.getPositionY()+1));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()+1));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()));
				}else{
					puffer.add(new Position(position.getPositonX()-1, position.getPositionY()));
					puffer.add(new Position(position.getPositonX()+1, position.getPositionY()));
				}
			}
		}
		if(spieler.getSpielfeld().getSchiffByPosition(puffer) != null){
			System.out.println("Das Schiff darf hier nicht plaziert werden, bitte wählen Sie einen neuen Platz aus.");
			return false;
		}else{
			return true;
		}
	}
		/**
	 * Willkommensmenü
	 */
	public void init(){
		int groesse;
		System.out.println("Willkommen bei Schiffe versenken!");
		System.out.println("Bitte geben Sie zunächst die Anzahl der Spieler an (2-6 Spieler):");
		createSpieler(IO.readInt());
		System.out.println("Einigen Sie sich nun bitte auf eine Größe ihres quadratischen Spielfelder (Mindestens 20x20 Felder groß)");
		System.out.println("Wie groß soll ihr Spielfeld sein?");
		groesse = IO.readInt();
		createSpielfelder(groesse);
		schiffePlatzieren();
		spielen();
	}
	
	public static void main(String[] args){
		Spiel spiel = new Spiel();
		spiel.init();	
	}
	

}
