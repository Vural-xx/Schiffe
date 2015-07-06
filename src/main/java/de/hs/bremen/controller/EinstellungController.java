package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import de.hs.bremen.abstracts.Actor;
import de.hs.bremen.abstracts.Schiff;
import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.gui.KiAuswahlGUI;
import de.hs.bremen.gui.SchiffeAuswahlGUI;
import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.SpielerNameGUI;
import de.hs.bremen.gui.SpielfeldGroesseGUI;
import de.hs.bremen.model.ComputerGegner;
import de.hs.bremen.model.Fregatte;
import de.hs.bremen.model.Korvette;
import de.hs.bremen.model.Spieler;
import de.hs.bremen.model.UBoot;
import de.hs.bremen.model.Zerstoerer;

/**
 * Klasse EinstellungsController zum Erstellen der Spielerauswahl, 
 * KI-Gegnerauswahl, Spielername, Schiffauswahl,Spielfeldgröße
 * @author Christin
 *
 */
public class EinstellungController {
	
	/**
	 * HauptController
	 */
	public MainController mainController;
	
	/**
	 * GUI für die Auswahl der Spieler
	 */
	private SpielerAuswahlGUI spielerAuswahlGUI;
	
	/**
	 * GUI für den Namen des Spielers
	 */
	private SpielerNameGUI spielerNameGUI;
	
	/**
	 * GUI für die Auswahl die Spielfeldgröße
	 */
	private SpielfeldGroesseGUI spielfeldGroeßeGUI;
	
	/**
	 * GUI für die Auswahl der Schiffe
	 */
	private SchiffeAuswahlGUI schiffeAuswahlGUI;
	
	/**
	 * GUI für die Auswahl der KI Gegner
	 */
	private KiAuswahlGUI kiAuswahlGUI;
	
	/**
	 * Anzahl der Spieler
	 */
	private Actor[] spieler;
	
	/**
	 * Größe des Spielfelds
	 */
	private int spielfeldGroesse;
	
	/**
	 * Schiffe für jeden Spieler der gesetzt werden muss
	 */
	private HashMap<String, ArrayList<Schiff>> schiffe;
	
	/**
	 * Konstruktor
	 * @param mainController: Hauptcontroller
	 */
	public EinstellungController(MainController mainController){
		this.mainController= mainController;
		spielerAuswahlGUI= new SpielerAuswahlGUI();
		spielerNameGUI= new SpielerNameGUI();
		spielfeldGroeßeGUI= new SpielfeldGroesseGUI();
		schiffeAuswahlGUI= new SchiffeAuswahlGUI();
		kiAuswahlGUI = new KiAuswahlGUI();
		spielerAuswahlGUI.setActionListener(new SpielerAnzahlListener());
		this.mainController.getMainFrame().add(spielerAuswahlGUI);
		this.mainController.getMainFrame().revalidate();
	}
	
	/**
	 * Übergibt die Schiffe für jeden Spieler der gesetzt werden muss
	 * @return
	 */
	public HashMap<String, ArrayList<Schiff>> getSchiffe() {
		return schiffe;
	}
	
	/**
	 * Klasse SpieleranzahlListener um die Anszahl der Spieler auszuwählen
	 * @author Christin
	 *
	 */
	class SpielerAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			spielerNameGUI.createNameFenster(spielerAuswahlGUI.getSpielerAnzahl());
			mainController.getMainFrame().remove(spielerAuswahlGUI);
			kiAuswahlGUI.setActionListener(new KiAnzahlListener());
			mainController.getMainFrame().add(kiAuswahlGUI);
			if(spielerAuswahlGUI.getSpielerAnzahl()==1){
				kiAuswahlGUI.buttonBeiEinemSpieler();
				kiAuswahlGUI.revalidate();
			}
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	/**
	 * Klasse KiAnzahlListener um die Anzahl der KI Gegner auszuwählen
	 * @author Christin
	 *
	 */
	class KiAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.getMainFrame().remove(kiAuswahlGUI);
			spielerNameGUI.setActionListener(new SpielerNameListener());
			mainController.getMainFrame().add(spielerNameGUI);
			mainController.getMainFrame().revalidate();
		}
	}
	
	/**
	 * Klasse SpielerNameListener um die Namen der Spieler auszuwählen
	 * @author Christin
	 *
	 */
	class SpielerNameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			tempSpieler();
			mainController.setSpieler(spieler);
			mainController.getMainFrame().remove(spielerNameGUI);
			spielfeldGroeßeGUI.setActionListener(new SpielfeldGroeßeListener());
			mainController.getMainFrame().add(spielfeldGroeßeGUI);
			mainController.getMainFrame().revalidate();
			
		}
		
	}
	/**
	 * Klasse SchiffAnszahlListener um die Anzahl der Schiffe auszuwählen
	 * @author Christin
	 *
	 */
	class SchiffAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			tempArraySchiff();
			mainController.getMainFrame().remove(schiffeAuswahlGUI);
			mainController.startSchiffeSetzen();
		}
		
	}
	
	/**
	 * Klasse SpielfeldGroeßeListener um die Größe des Spielfeldes auszuwählen
	 * @author Christin
	 *
	 */
	class SpielfeldGroeßeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.getMainFrame().remove(spielfeldGroeßeGUI);
			mainController.setSpielfeldgroesse(tempSpielfeldgroesse());
			mainController.getSpiel().setSpielfeldGroesse(tempSpielfeldgroesse());
			schiffeAuswahlGUI.setActionListener(new SchiffAnzahlListener());
			mainController.getMainFrame().add(schiffeAuswahlGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	/**
	 * Temporäre Spieler erzeugen
	 */
	private void tempSpieler(){
		spieler = new Actor[spielerAuswahlGUI.getSpielerAnzahl()+kiAuswahlGUI.getSpielerAnzahl()];
		for(int k=0; k< spieler.length; k++){
			for (int i=0; i< spielerAuswahlGUI.getSpielerAnzahl(); i++){	
				spieler[i]= new Spieler(spielerNameGUI.spielerEingabe[i].getText());
				spieler[i].setSpielerart(Spielerart.MENSCH);
			}
			for(int j=0; j <kiAuswahlGUI.getSpielerAnzahl(); j++ ){
				spieler[j+spielerAuswahlGUI.getSpielerAnzahl()]= new ComputerGegner("Computer"+(j+1));
				spieler[j+spielerAuswahlGUI.getSpielerAnzahl()].setSpielerart(Spielerart.KI);
			}
		}
		
		spieler[0].setIstDran(true);
		gibGegnerAnKi();
		
	}
	
	/**
	 * Setzt die KI Gegner 
	 */
	public void gibGegnerAnKi(){
		for(int i=0; i< spieler.length; i++ ){
		 if(spieler[i].getSpielerart()== Spielerart.KI) {
			 ComputerGegner computerGegner = (ComputerGegner) spieler[i];
			 computerGegner.setGegner(spieler);
			 computerGegner.spielerAuswahl();
		 }
		}
		
	}
	
	/**
	 * Temporäre Spielfeldgröße erzeugen
	 * @return die temporäre Spielfeldgröße
	 */
	public int tempSpielfeldgroesse(){
		spielfeldGroesse=Integer.parseInt(spielfeldGroeßeGUI.spielfeldEingabe.getText());
		return spielfeldGroesse;
	}
	
	/**
	 * Temporäre Schiffe
	 */
	public void tempArraySchiff(){
		int anzahlZerstoerer= Integer.parseInt(schiffeAuswahlGUI.zerstoererEingabe.getText());
		int anzahlFregatte= Integer.parseInt(schiffeAuswahlGUI.fregatteEingabe.getText());
		int anzahlKorvette= Integer.parseInt(schiffeAuswahlGUI.korvetteEingabe.getText());
		int anzahlUboot= Integer.parseInt(schiffeAuswahlGUI.ubootEingabe.getText());

		ArrayList<Schiff> tempSchiffe;
		schiffe = new HashMap<String, ArrayList<Schiff>>();
		for(int i =0; i<mainController.getSpieler().length;i++){
			mainController.getSpieler()[i].createSpielfeld(mainController.getSpielfeldgroesse());
			tempSchiffe = new ArrayList<Schiff>();
			for(int j=0;j<anzahlZerstoerer;j++){
				tempSchiffe.add(new Zerstoerer());
			}
			for(int j=0;j<anzahlFregatte;j++){
				tempSchiffe.add(new Fregatte());
			}
			for(int j=0;j<anzahlKorvette;j++){
				tempSchiffe.add(new Korvette());
			}
			for(int j=0;j<anzahlUboot;j++){
				tempSchiffe.add(new UBoot());
			}
			schiffe.put(mainController.getSpieler()[i].getName(), tempSchiffe);
			tempSchiffe = null;
		}
	}
	
}



