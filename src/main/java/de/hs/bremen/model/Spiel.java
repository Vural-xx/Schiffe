package de.hs.bremen.model;


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
		if(auswahlZahl <=4 && auswahlZahl >=1){
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
	 * Funktion, die Schiffe pro Spieler auf deren Spielfeldern platziert.
	 */
	public void schiffePlatzieren(){
		int spalte;
		int zeile;
		int anzahlSchiffe;
		int anzahlSchiffeGezeugt;
		Schiff schiff;
		
		do{
			System.out.println("Bitte geben Sie an mit vielen Schiffen Sie pro Person spielen möchten.");
			anzahlSchiffe = IO.readInt();
		}while(spieler[0].getSpielfeld().getMaximumAnzahlSchiffe() < anzahlSchiffe && anzahlSchiffe != 0);
		
		for (int i = 0 ; i< spieler.length; i++){		
			 anzahlSchiffeGezeugt = 0;
			do {				

				System.out.println(spieler[i].getName() +",Bitte wählen Sie ein Schiff, welches Sie auf dem Spielfeld platzieren wollen.");
				System.out.println("[1 für Zerstörer, 2 für Fregatte, 3 für Korvette, 4 für UBoot]");
				schiff = schiffeAuswahl(schiffeAuswahlGueltig(IO.readInt()));
				System.out.println(spieler[i].getName() +", Bitte geben Sie an in welcher Zeile ihr Schiff platziert werden soll");
				zeile = IO.readInt();
				System.out.println(spieler[i].getName() +", Bitte geben Sie an in welcher Spalte ihr Schiff platziert werden soll");
				spalte = IO.readInt();
				spieler[i].getSpielfeld().platziereSchiff(schiff, new Position(spalte, zeile), true);
				spieler[i].getSpielfeld().printSpielfeld();
				anzahlSchiffeGezeugt++;
			}while(anzahlSchiffeGezeugt != anzahlSchiffe && anzahlSchiffePassend(spieler[i],schiff));			
		}
	}
	
	/**
	 * NEU Schiffe setzen je Spieler, je nach Auswahl
	 */
	//public void SchiffSetzen(){
		
		
	//}
	
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
	public boolean anzahlSchiffePassend(Spieler spieler, Schiff schiff){
		boolean passend = spieler.getSpielfeld().getPlaetzeBelegt() + schiff.getPlaetzeBelegung() < (spieler.getSpielfeld().getSpielfeldgroesse() * spieler.getSpielfeld().getSpielfeldgroesse());
		if(!passend){
			System.out.println("Dieses Schiff passt leider nicht mehr auf ihr Spielfeld. Bitte wählen Sie ein anderes");
		}
		return passend;
	}
	
	public boolean innerhalbSpielfeld (Position position, Spieler spieler){
		if (position.getPositonX() <0|| position.getPositonX()> spieler.getSpielfeld().getSpielfeldgroesse() && position.getPositionY()<0 || position.getPositionY()> spieler.getSpielfeld().getSpielfeldgroesse()){
			System.out.println("Das Schiff liegt außerhalb des Spielfeldes und darf hier nicht plaziert werden!");
			return false;
		}else {
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
	}
	
	public static void main(String[] args){
		Spiel spiel = new Spiel();
		spiel.init();	
	}

}
