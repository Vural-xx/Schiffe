package de.hs.bremen.model;



import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.jws.Oneway;

import de.hs.bremen.persistence.ObjectPersistenceManager;
import helper.ConsoleColor;
import helper.IO;

/**
 * Klasse für Spiellogik.
 * @author vural
 *
 */
public class Spiel implements Serializable {
	
	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = -4596970661321389454L;	
	private final int maximaleSpieleranzahl= 6;
	private final int maximaleSpielfeldgroesse= 40;
	private final int minimaleSpielfeldgroesse= 20;
	private int schiffAuswahlUebergabe;
	
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
				System.out.println("----------------------------------------------------");
				System.out.println("Spieler Nummer " +i+" bitte geben Sie ihren Namen an:");
				System.out.println("----------------------------------------------------");
				name = IO.readString();
				spieler[i-1] = new Spieler(name);
			}
		} else {
			System.out.println(ConsoleColor.ANSI_RED+"============================================================================="+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Ihre Spieleranzahl ist nicht erlaubt oder ihre Eingabe wurde nicht erkannt."+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"============================================================================="+ConsoleColor.ANSI_RESET);
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
			System.out.println(ConsoleColor.ANSI_RED+"================================================================================="+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Ihre Spielfeldgröße ist zu groß zu niedrig oder ihre Eingabe wurde nicht erkannt."+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"================================================================================="+ConsoleColor.ANSI_RESET);
			System.out.println("Bitte geben Sie erneut die Spielergröße ein:");
			createSpielfelder(IO.readInt());
		}
	}
	

	
	public void schiffeAuswahlGueltig(int auswahlZahl){
		if(auswahlZahl >=1 && auswahlZahl <=5){		
			schiffAuswahlUebergabe = auswahlZahl;
		} else {
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Falsche Eingabe versuchen Sie es erneut:"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			schiffeAuswahlGueltig(IO.readInt());
		}
	}
	
	
	public void schiffAuswahlUebergabe(){
		System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_RED+"Falsche Eingabe versuchen Sie es erneut:"+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
		schiffeAuswahlGueltig(IO.readInt());
		
	}
	
	
	/**
	 * Funktion, ob Schiffe Horizontal oder Vertikal angeordnet werden sollen.
	 */
	public int vertikalHorizontal(int richtungsAbfrage){
		if(richtungsAbfrage== 1 || richtungsAbfrage == 2){
			return richtungsAbfrage;
		} else {
			System.out.println("Falsche Eingabe versuchen Sie es erneut:");
			return vertikalHorizontal(IO.readInt());
		}
	}
	
	/**
	 * Funktion, die Schiffe pro Spieler auf deren Spielfeldern platziert.
	 */
	public void schiffePlatzieren(){
		ArrayList<Schiff> schiffe = new ArrayList<Schiff>();
		menueSchiffartAuswahl(schiffe);
		schiffeAufFeldSetzen(schiffe);
	}
	
	public void spielen(){
		runde=1;
		ArrayList<Spieler> tempSpieler;
		while(spieler.length>1){
			tempSpieler=null;
			tempSpieler = new ArrayList<Spieler>();
			System.out.println("");
			System.out.println("");
			System.out.println("-------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------");
			System.out.println(ConsoleColor.ANSI_RED+"		||||  RUNDE BEGINNT: " + runde +   "  ||||"+ConsoleColor.ANSI_RESET);
			System.out.println("-------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------");
			System.out.println("");
			System.out.println("");
			runde=runde+1;
			rundeSpielen();
			
			for(int j=0; j< spieler.length; j++){
				
				if(!spieler[j].ausgeschieden()){
					tempSpieler.add(spieler[j]);
					
				}else {
					System.out.println(ConsoleColor.ANSI_RED+"==============================");
					System.out.println("   "+spieler[j].getName()+" IST AUSGESCHIEDEN!!");
					System.out.println(ConsoleColor.ANSI_RED+"=============================="+ConsoleColor.ANSI_RESET);
					System.out.println("");
					System.out.println("");
				}
			}
			if(tempSpieler.size() != spieler.length){
				spieler=new Spieler[tempSpieler.size()];
				spieler=tempSpieler.toArray(spieler);
				
			}
		}
		System.out.println(ConsoleColor.ANSI_CYAN+"======================================================="+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_GREEN+"|||||||||||||||||||||||||||||||||||||||||||||||||||||||"+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_PURPLE+"======================================================="+ConsoleColor.ANSI_RESET);
		System.out.println("		Spieler " + spieler[0].getName().toUpperCase() + " hat gewonnen!!!!!!");
		System.out.println(ConsoleColor.ANSI_RED+"======================================================="+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_YELLOW+"|||||||||||||||||||||||||||||||||||||||||||||||||||||||"+ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_BLUE+"======================================================="+ConsoleColor.ANSI_RESET);
	}
	
	public void rundeSpielen(){
		int auswahl;
		Schiff schiff;
		int zeile;
		int spalte;
		Spieler gegner = null;
		for(int i = 0; i < spieler.length; i++){
			schiff = null;
			System.out.println("===============================");
			System.out.println(ConsoleColor.ANSI_BLUE+ "|| Spieler "+ConsoleColor.ANSI_RESET  +  spieler[i].getName()+ConsoleColor.ANSI_BLUE+" ist dran: ||" + ConsoleColor.ANSI_RESET);
			System.out.println("===============================");
			System.out.println("");
			if(spieler[i].schiffeOhneWartezeit()){
				System.out.println("Spieler " +spieler[i].getName()+". Bitte wählen Sie ein Schiff mit dem Sie feuern wollen.");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println(spieler[i].getSpielfeld().printSchiffeMenu());
				System.out.println("--------------------------------------------------------------------------");
				auswahl = IO.readInt();
				schiff = spieler[i].getSpielfeld().getZustaendigesSchiff(auswahl);
				if(spieler.length > 2){
					gegner = spielerAuswahlMenu(spieler[i]);
				}else if (i == 0){
					gegner = spieler[1];
				}else{
					gegner = spieler[0];
				}
				System.out.println("--------------------------------------");
				System.out.println(ConsoleColor.ANSI_PURPLE+"ÜBERSICHTS FELD VON SPIELER: " + spieler[i].getName() + " AUF SPIELER: " + gegner.getName()+ConsoleColor.ANSI_RESET);
				System.out.println("--------------------------------------");
				System.out.println("");
				System.out.println("");
				gegner.getSpielfeldPublic().printSpielfeld();
				System.out.println("");
				System.out.println("--------------------------");
				System.out.println("  || "+spieler[i].getName() +" SCHIEßEN !!! ||");
				System.out.println("--------------------------");
				System.out.println("");
				System.out.println("----------------------------------------------------------------");
				System.out.println(spieler[i].getName() +", , in welcher ZEILE soll geschossen werden? (Y-Achse)");
				System.out.println("----------------------------------------------------------------");
				zeile = IO.readInt();
				System.out.println("----------------------------------------------------------------");
				System.out.println(spieler[i].getName() +" , in welcher SPALTE soll geschossen werden? (X-Achse)");
				System.out.println("----------------------------------------------------------------");
				spalte = IO.readInt();
				System.out.println("");
				schiff.feuern(new Position(spalte, zeile), gegner.getSpielfeldPublic());
				gegner.trefferUebertragung();
				System.out.println("");
						
			} else {
				System.out.println("");
				System.out.println("");
				System.out.println(ConsoleColor.ANSI_RED+"---------------------------------------------------------------"+ConsoleColor.ANSI_RESET);
				System.out.println(ConsoleColor.ANSI_RED+"		DU HAST KEIN SCHIFF ZUR VERFÜGUNG		"+ConsoleColor.ANSI_RESET);
				System.out.println(ConsoleColor.ANSI_RED+"---------------------------------------------------------------"+ConsoleColor.ANSI_RESET);
				System.out.println("");
				System.out.println("");
			}
			// Wartezeit
			for(int j=0; j< spieler[i].getSpielfeld().getSchiffe().size();j++){
				Schiff wartezeitResetSchiff=spieler[i].getSpielfeld().getSchiffe().get(j);
				if(wartezeitResetSchiff != null){
					if(wartezeitResetSchiff.getWartezeit()!= 0){
						wartezeitResetSchiff.setWartezeit(wartezeitResetSchiff.getWartezeit()-1);
					}
				}
			}
			if(schiff != null){
				schiff.setWartezeit(schiff.getFeuerstaerke());
			}	
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
			System.out.println("");
		}
		return passend;
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
	
	public void menueSchiffartAuswahl(ArrayList<Schiff> schiffe){
		int anzahlSchiffe;
		int schiffTyp;
		do{
			System.out.println("--------------");
			System.out.println("|| SCHIFFE: ||");
			System.out.println("--------------");
			System.out.println("Bitte geben Sie an was für eine Art von Schiff auf dem Spielfeld platziert werden soll.");
			System.out.println("[1 für "+ ConsoleColor.ANSI_PURPLE + "Zerstörer" +ConsoleColor.ANSI_RESET+ ", 2 für "+ ConsoleColor.ANSI_YELLOW + "Fregatte" +ConsoleColor.ANSI_RESET+", 3 für "+ ConsoleColor.ANSI_RED + "Korvette" +ConsoleColor.ANSI_RESET+  ", 4 für "+ ConsoleColor.ANSI_GREEN + "Uboot" +ConsoleColor.ANSI_RESET+ ", 5 keine weiteren]");
			schiffeAuswahlGueltig(IO.readInt());
			schiffTyp = schiffAuswahlUebergabe;
			if(schiffTyp !=5){
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("Wieviele Schiffe von dem Schifftyp " + schiffeAuswahl(schiffTyp).getName()+" sollen gesetzt werden?");
				System.out.println("---------------------------------------------------------------------------------------");
				anzahlSchiffe = IO.readInt();
				if (anzahlSchiffePassend(spieler[0],schiffeAuswahl(schiffTyp), anzahlSchiffe)){
					for(int i= 0 ; i < anzahlSchiffe; i++){
						schiffe.add(schiffeAuswahl(schiffTyp));
					}
				}else{
					System.out.println(ConsoleColor.ANSI_RED+"============================================================================="+ConsoleColor.ANSI_RESET);
					System.out.println(ConsoleColor.ANSI_RED+"Soviele Schiffe vom Schifftyp " +schiffeAuswahl(schiffTyp).getName()+ " passen nicht auf ihr Spielfeld!!!!!!"+ConsoleColor.ANSI_RESET);
					System.out.println(ConsoleColor.ANSI_RED+"============================================================================="+ConsoleColor.ANSI_RESET);
					System.out.println("");
				}	
			}
		}while(schiffTyp != 5 && spieler[0].getSpielfeld().getMaximumAnzahlSchiffe() > schiffe.size());
		
	}
	
	public void ladeMenu(){
		int spielstand;
		String ladeMenu = "";
		System.out.println("Bitte geben Sie an welchen Spielstand Sie laden möchten.");
		ObjectPersistenceManager opm = new ObjectPersistenceManager();
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			ladeMenu = ladeMenu +"Stand " +(i+1)+ ": " + listOfFiles[i].getName()+ " \t";
		}
		System.out.println(ladeMenu);
		spielstand = IO.readInt();
		
		Spiel spiel = opm.spielstandLaden(listOfFiles[spielstand-1].getName());
	
	}
	
	public void speicherMenu(){
		int spielstand;
		String spielstandName;
		String saveMenu = "";
		System.out.println("Bitte geben Sie an welchen Spielstand sie überschreiben oder neu schreiben möchten");
		ObjectPersistenceManager opm = new ObjectPersistenceManager();
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			saveMenu = saveMenu +"Stand " +(i+1)+ ": " + listOfFiles[i].getName()+ " \t";
		}
		
		if(listOfFiles.length< 4){
			for(int i = listOfFiles.length; i < 4; i++){
				saveMenu = saveMenu +"Stand " +(i+1)+ ": LEER \t";
			}
		}
		System.out.println(saveMenu);
		spielstand = IO.readInt();
		
		System.out.println("Bitte geben Sie den Namen des Speicherstandes an");
		spielstandName = IO.readString();
		if(spielstand >= listOfFiles.length){
			opm.spielstandSpeichern(this, spielstandName);
		}else{
			if(listOfFiles[spielstand-1].getName().equals(spielstandName)){
				opm.spielstandSpeichern(this, spielstandName);
			}else{
				listOfFiles[spielstand-1].delete();
				opm.spielstandSpeichern(this, spielstandName);
			}
		}
		System.out.println("Ihr Spielstand wurde erfolgreich gespeichert");
	}

	
	public void schiffeAufFeldSetzen(ArrayList<Schiff> schiffe){
		int ausrichtung;
		int zeile;
		int spalte;
		int anzahlSchiffeGezeugt;
		for (int i = 0 ; i< spieler.length; i++){		
			anzahlSchiffeGezeugt = 0;		 
			do {
				System.out.println("");
				System.out.println("");
				System.out.println(ConsoleColor.ANSI_PURPLE+"--------------------------------------");
				System.out.println("--------------------------------------");
				System.out.println("   SPIELER: " +ConsoleColor.ANSI_RESET+ spieler[i].getName().toUpperCase()+ConsoleColor.ANSI_PURPLE+" SCHIFFE PLATZIEREN:");
				System.out.println("--------------------------------------");
				System.out.println("--------------------------------------"+ConsoleColor.ANSI_RESET);
				System.out.println("");
				System.out.println("");
				System.out.println("----------------------------");
				System.out.println("|| "+schiffe.get(anzahlSchiffeGezeugt).getName().toUpperCase()+ConsoleColor.ANSI_GREEN+ " platzieren: "+ConsoleColor.ANSI_RESET+ "||" );
				System.out.println("----------------------------");
				System.out.println("");
				System.out.println(spieler[i].getName() +", in welcher ZEILE soll das Schiff platziert werden? (Y-Achse)");
				System.out.println(ConsoleColor.ANSI_GREEN+"--------------------------------------------------------------------------"+ConsoleColor.ANSI_RESET);
				zeile = IO.readInt();
				System.out.println(ConsoleColor.ANSI_GREEN+"--------------------------------------------------------------------------"+ConsoleColor.ANSI_RESET);
				System.out.println(spieler[i].getName() +", in welcher SPALTE soll das Schiff plaziert werden? (X-Achse)");
				System.out.println(ConsoleColor.ANSI_GREEN+"--------------------------------------------------------------------------"+ConsoleColor.ANSI_RESET);
				spalte = IO.readInt();
				System.out.println(spieler[i].getName() +", bitte geben Sie an ob ihr Schiff 1. Horizontal oder 2. Vertikal angeorndet werden soll");
				ausrichtung = vertikalHorizontal(IO.readInt());
				System.out.println("--------------------------------------------------------------");
				System.out.println("|||| SPIELFELD VON SPIELER: " + spieler[i].getName() + " ||||");
				System.out.println("--------------------------------------------------------------");
				if (spieler[i].getSpielfeld().schiffPlazierbar(schiffe.get(anzahlSchiffeGezeugt), new Position(spalte, zeile), ausrichtung)){
					spieler[i].getSpielfeld().platziereSchiff(schiffe.get(anzahlSchiffeGezeugt), new Position(spalte, zeile), ausrichtung);
					spieler[i].getSpielfeld().printSpielfeld();
					anzahlSchiffeGezeugt++;
				}else{
					System.out.println(ConsoleColor.ANSI_RED+"==================================================================================="+ConsoleColor.ANSI_RESET);
					System.out.println(ConsoleColor.ANSI_RED+"Das Schiff darf hier nicht plaziert werden, bitte wählen Sie einen neuen Platz aus."+ConsoleColor.ANSI_RESET);
					System.out.println(ConsoleColor.ANSI_RED+"==================================================================================="+ConsoleColor.ANSI_RESET);
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
	
	/**
	 * Willkommensmenü
	 */
	public void init(){
		int groesse;
		System.out.println("---------------------------------------");
		System.out.println("|| Willkommen bei Schiffe versenken! ||");
		System.out.println("---------------------------------------");
		System.out.println("Info:");
		System.out.println("Die Schiffe haben folgende Werte:");
		System.out.println("Zerstörer : Länge: 5 | Feuerstärke: 3");
		System.out.println("Fregatte :  Länge: 4 | Feuerstärke: 2");
		System.out.println("Korvette :  Länge: 3 | Feuerstärke: 1");
		System.out.println("Uboot :     Länge: 2 | Feuerstärke: 1");
		System.out.println("");
		System.out.println("MERKE: Die Schiffe schießen nur HORIZONTAL");
		System.out.println("");
		System.out.println("");
		System.out.println("Gewonnen hat der Spieler der als letztes noch ein Schiff auf dem Spielfeld hat.");
		System.out.println("");
		System.out.println("");
		System.out.println("VIEL SPAß!");
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		System.out.println(ConsoleColor.ANSI_GREEN+"		-------------------------------------");
		System.out.println("			   DAS SPIEL BEGINNT		");
		System.out.println("		-------------------------------------"+ConsoleColor.ANSI_RESET);
		System.out.println("");
		System.out.println("");
		System.out.println("--------------------");
		System.out.println("|| SPIELERANZAHL: ||");
		System.out.println("--------------------");
		System.out.println("");
		System.out.println("Bitte geben Sie zunächst die Anzahl der Spieler an (2-6 Spieler):");
		createSpieler(IO.readInt());
		System.out.println("-----------------");
		System.out.println("|| SPIELFELD: ||");
		System.out.println("-----------------");
		System.out.println("Einigen Sie sich nun bitte auf eine Größe ihres quadratischen Spielfelder (Mindestens 20x20 Felder groß)");
		System.out.println("Wie groß soll ihr Spielfeld sein?");
		System.out.println("---------------------------------");
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
