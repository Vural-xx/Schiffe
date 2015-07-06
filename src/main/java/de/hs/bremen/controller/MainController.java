package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import de.hs.bremen.abstracts.AbstractController;
import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Schiff;
import de.hs.bremen.model.Spiel;
import de.hs.bremen.persistence.ObjectPersistenceManager;

/**
 * HauptController zur Verknüpfung zwischen GUI und Model
 * @author Christin
 *
 */
public class MainController extends AbstractController {
	
	/**
	 * 
	 */
	private EinstellungController einstellungController;
	
	/**
	 * 
	 */
	private RundenController rundenController;
	
	/**
	 * 
	 */
	private SchiffeSetzenController schiffeSetzenController;
	
	/**
	 * 
	 */
	private Schiff ausgewähltesSchiff;
	
	/**
	 * 
	 */
	private int spielfeldgroesse;
	
	/**
	 * 
	 */
	private boolean rundeSpielt;
	
	/**
	 * Konstruktor
	 */
	public MainController() {
		super();
		setMainFrame(new MainFrame(this));
		getMainFrame().setActionListener(new SpeichernItemKlick(), new LadeItemKlick());
		startEinstellungController();
	}
	
	/**
	 * Getter Spielfeldgröße
	 * @return
	 */
	public int getSpielfeldgroesse() {
		return spielfeldgroesse;
	}

	/**
	 * Setter Spielfeldgröße
	 * @param spielfeldgroesse: wie groß das Spielfeld ist
	 */
	public void setSpielfeldgroesse(int spielfeldgroesse) {
		this.spielfeldgroesse = spielfeldgroesse;
	}

	/**
	 * Getter für das ausgewählte Schiff
	 * @return
	 */
	public Schiff getAusgewähltesSchiff() {
		return ausgewähltesSchiff;
	}

	/**
	 * Setter für das ausgewählte Schiff
	 * @param ausgewähltesSchiff: welches Schiff gesetzt werden soll
	 */
	public void setAusgewähltesSchiff(Schiff ausgewähltesSchiff) {
		this.ausgewähltesSchiff = ausgewähltesSchiff;
	}
	
	/**
	 * Getter SchiffSetzenController
	 * @return
	 */
	public SchiffeSetzenController getSchiffeSetzenController() {
		return schiffeSetzenController;
	}
	/**
	 * Setter SchiffSetzenController
	 * @param schiffeSetzenController: Controller für das Setzen der Schiffe
	 */
	public void setSchiffeSetzenController(
			SchiffeSetzenController schiffeSetzenController) {
		this.schiffeSetzenController = schiffeSetzenController;
	}

	/**
	 * Getter RundenController
	 * @return
	 */
	public RundenController getRundenController() {
		return rundenController;
	}

	/**
	 * Setter RundenController
	 * @param rundenController: Controller für die Runde
	 */
	public void setRundenController(RundenController rundenController) {
		this.rundenController = rundenController;
	}
	
	/**
	 * Getter MainController
	 * @return
	 */
	public MainController getMainController(){
		return this;
		
	}
	
	
	/**
	 * Getter EinstellungsController
	 * @return
	 */
	public EinstellungController getEinstellungController() {
		return einstellungController;
	}

	/**
	 * Setter EinstellungController
	 * @param einstellungController: Controller für die Voreinstellung des Spiels
	 */
	public void setEinstellungController(EinstellungController einstellungController) {
		this.einstellungController = einstellungController;
	}

	
	/**
	 * Getter für die aktuellen Spieler
	 * @return
	 */
	public ArrayList<Schiff> getCurrentSpielerSchiffe(){
		return getCurrentSpieler().getSpielfeld().getSchiffe();
	}
	
	/**
	 * Startet das Spiel und die GUI
	 */
	public void startEinstellungController(){
		einstellungController= new EinstellungController(this);
	}
	
	/**
	 * Entfernt den Einstellungskontroller und geht zum SchiffsetztenController über
	 */
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this, einstellungController.getSchiffe());
	}

	/**
	 * Entfernt den SchiffSetzenController und geht zum RundenController
	 */
	public void startRunden(){
		rundenController = new RundenController(this);	
		rundeSpielt = true;
	}
	
	/**
	 * Spielerwechsel
	 */
	public void nextSpieler(){
		int nextSpieler = 0;
		for (int i = 0 ; i< getSpieler().length;i++){
			if (getSpieler()[i].isIstDran() ){
				if(i+1 == getSpieler().length){
					nextSpieler = 0;
				}else{
					nextSpieler = i+1;
				}
				getSpieler()[i].setIstDran(false);
			}
		}
		getSpieler()[nextSpieler].setIstDran(true);
	}
	
	/**
	 * Übergibt, ob das Schiff eine Wartezeit hat
	 * @param name: Name des Schiffs
	 * @return
	 */
	public boolean schiffOhneWartezeit(String name){
		boolean ohneWartezeit = false;
		for(Schiff s: getCurrentSpieler().getSpielfeld().getSchiffe()){
			if(s.getName().equals(name) && s.getWartezeit() == 0){
				ohneWartezeit = true;
			}
		}
		return ohneWartezeit;
	}
	
	
	/**
	 * Übergibt den aktuellen Spieler
	 * @return: der aktuelle Spieler
	 */
	public Actor getCurrentSpieler(){
		Actor spieler = null;
		for(int i = 0 ; i < getSpieler().length; i++){
			if(getSpieler()[i].isIstDran()){
				spieler = getSpieler()[i];
			}
		}
		return spieler;
	}
	
	
	/**
	 * Übergibt den Index des aktuellen Spielers
	 * @return Index des Spielers
	 */
	public int getCurrentSpielerIndex(){
		int spieler = 0;
		for(int i = 0 ; i < getSpieler().length; i++){
			if(getSpieler()[i].isIstDran()){
				spieler = i;
			}
		}
		return spieler;
	}
	
	/**
	 * Übergibt den Index  des nächsten Spielers
	 * @return nächster Spieler
	 */
	public int getNextSpielerIndex(){
		int nextSpieler = 0;
		for (int i = 0 ; i< getSpieler().length;i++){
			if (getSpieler()[i].isIstDran() ){
				if(i+1 != getSpieler().length){
					nextSpieler = i+1;
				}
			}
		}
		return nextSpieler;
	}
	
	/**
	 * reduziert die Wartezeit, wenn das Schiff genutzt wurde
	 */
	public void wartezeitVerringern(){
		Schiff wartezeitResetSchiff = null;
		for(int i=0; i< getSpieler().length;i++){
			for(int j=0; j< getSpieler()[i].getSpielfeld().getSchiffe().size();j++){
				wartezeitResetSchiff=getSpieler()[i].getSpielfeld().getSchiffe().get(j);
				if(wartezeitResetSchiff != null){
					if(wartezeitResetSchiff.getWartezeit()!= 0){
						wartezeitResetSchiff.setWartezeit(wartezeitResetSchiff.getWartezeit()-1);
					}
				}
				wartezeitResetSchiff = null;
			}
		}
	}
	
	
	
	/**
	 * Überprüft, ob es der letzte Spieler der Runde ist
	 * @return
	 */
	public boolean lastRundenSpieler(){
		return getCurrentSpielerIndex() +1== getSpieler().length;
		
	}
	
	
	/**
	 * Klasse zum Laden eine Spielstandes
	 * @author Christin
	 *
	 */
	class LadeItemKlick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ObjectPersistenceManager objectPersistenceManager = new ObjectPersistenceManager();
			JMenuItem jMenuItem = (JMenuItem) e.getSource();
			Spiel spiel = objectPersistenceManager.spielstandLaden(jMenuItem.getName());
			setSpiel(spiel);
			getMainController().setSpielfeldgroesse(spiel.getSpielfeldGroesse());
			getMainFrame().dispose();
			setMainFrame(new MainFrame(getMainController()));
			getMainFrame().revalidate();
			setRundenController(null);
			startRunden();
		}
		
	}
	
	/**
	 * Klasse zum Speichern eines Spielstandes
	 * @author Christin
	 *
	 */
	class SpeichernItemKlick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(rundeSpielt){
			    ObjectPersistenceManager opm = new ObjectPersistenceManager();
			    String speicherName = JOptionPane.showInputDialog(null, "Wie wollen Sie den Spielstand benennen?");
			    File folder = new File("src/temp");
				File[] listOfFiles = folder.listFiles();
				boolean speicherExistiert = false;
			    for (int i = 0; i< listOfFiles.length; i++){
			    	if(listOfFiles[i].getName().equals(speicherName)){
			    		JOptionPane.showMessageDialog(null, "Diese Speichername existiert bereits, bitte wählen Sie einen anderen!");
			    		speicherExistiert = true;
					}
			    }
			    if(!speicherExistiert){
				    opm.spielstandSpeichern(getSpiel(), speicherName);
				    JOptionPane.showMessageDialog(null, "Das Spiel wurde erfolgreich gespeichert");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Erst beim Runden spielen kann gespeichert werden!");
			}
			
		}
		
	}

}
