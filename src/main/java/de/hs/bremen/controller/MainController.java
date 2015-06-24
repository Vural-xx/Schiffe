package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import de.hs.bremen.abstracts.AbstractController;
import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Schiff;
import de.hs.bremen.model.Spieler;

public class MainController extends AbstractController {
	private EinstellungController einstellungController;
	private RundenController rundenController;
	private SchiffeSetzenController schiffeSetzenController;
	private Schiff ausgewähltesSchiff;
	private int spielfeldgroesse;

	
	public int getSpielfeldgroesse() {
		return spielfeldgroesse;
	}

	public void setSpielfeldgroesse(int spielfeldgroesse) {
		this.spielfeldgroesse = spielfeldgroesse;
	}

	public MainController() {
		super();
		// TODO Auto-generated constructor stub
		//startSchiffeSetzen();
		//startRunden();
		startEinstellungController();
	}
	
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this, einstellungController.getSchiffe());
	}
	
	public void startRunden(){
		rundenController = new RundenController(this);	
	}
	
	public void startGame(){
		
	}
	
	public void startEinstellungController(){
		einstellungController= new EinstellungController(this);
	}
	
	public ActionListener getZuRundeWechselnListener(){
		return new ZuRundeWechseln();
		
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
	
	public class ZuRundeWechseln implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Test");
		}
		
	}

}
