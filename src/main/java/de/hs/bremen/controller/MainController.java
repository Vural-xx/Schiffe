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

public class MainController extends AbstractController {
	private EinstellungController einstellungController;
	private RundenController rundenController;
	private SchiffeSetzenController schiffeSetzenController;
	private Schiff ausgewähltesSchiff;
	private int spielfeldgroesse;
	private boolean rundeSpielt;

	
	public int getSpielfeldgroesse() {
		return spielfeldgroesse;
	}

	public void setSpielfeldgroesse(int spielfeldgroesse) {
		this.spielfeldgroesse = spielfeldgroesse;
	}

	public MainController() {
		super();
		setMainFrame(new MainFrame(this));
		getMainFrame().setActionListener(new SpeichernItemKlick(), new LadeItemKlick());
		startEinstellungController();
	}
	
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this, einstellungController.getSchiffe());
	}
	
	public void startRunden(){
		rundenController = new RundenController(this);	
		rundeSpielt = true;
	}
	
	public void startEinstellungController(){
		einstellungController= new EinstellungController(this);
	}
	
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
	
	public boolean schiffOhneWartezeit(String name){
		boolean ohneWartezeit = false;
		for(Schiff s: getCurrentSpieler().getSpielfeld().getSchiffe()){
			if(s.getName().equals(name) && s.getWartezeit() == 0){
				ohneWartezeit = true;
			}
		}
		return ohneWartezeit;
	}
	
	
	public Actor getCurrentSpieler(){
		Actor spieler = null;
		for(int i = 0 ; i < getSpieler().length; i++){
			if(getSpieler()[i].isIstDran()){
				spieler = getSpieler()[i];
			}
		}
		return spieler;
	}
	
	public EinstellungController getEinstellungController() {
		return einstellungController;
	}

	public void setEinstellungController(EinstellungController einstellungController) {
		this.einstellungController = einstellungController;
	}

	public void setSchiffeSetzenController(
			SchiffeSetzenController schiffeSetzenController) {
		this.schiffeSetzenController = schiffeSetzenController;
	}

	public int getCurrentSpielerIndex(){
		int spieler = 0;
		for(int i = 0 ; i < getSpieler().length; i++){
			if(getSpieler()[i].isIstDran()){
				spieler = i;
			}
		}
		return spieler;
	}
	
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
	
	public ArrayList<Schiff> getCurrentSpielerSchiffe(){
		return getCurrentSpieler().getSpielfeld().getSchiffe();
	}
	
	public boolean lastRundenSpieler(){
		return getCurrentSpielerIndex() +1== getSpieler().length;
		
	}
	
	public Schiff getAusgewähltesSchiff() {
		return ausgewähltesSchiff;
	}

	public void setAusgewähltesSchiff(Schiff ausgewähltesSchiff) {
		this.ausgewähltesSchiff = ausgewähltesSchiff;
	}
	
	public SchiffeSetzenController getSchiffeSetzenController() {
		return schiffeSetzenController;
	}

	public RundenController getRundenController() {
		return rundenController;
	}

	public void setRundenController(RundenController rundenController) {
		this.rundenController = rundenController;
	}
	
	public MainController getMainController(){
		return this;
		
	}
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
	
	class SpeichernItemKlick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(rundeSpielt){
				JMenuItem jMenuItem = (JMenuItem) e.getSource();
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
