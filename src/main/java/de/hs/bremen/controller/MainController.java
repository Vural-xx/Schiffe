package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import de.hs.bremen.abstracts.AbstractController;
import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Spieler;

public class MainController extends AbstractController {
	private EinstellungController einstellungController;
	private RundenController rundenController;
	private SchiffeSetzenController schiffeSetzenController;
	
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
		//startSchiffeSetzen();
		//startRunden();
		startEinstellungController();
	}
	
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this);
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
	
	public Spieler getCurrentSpieler(){
		Spieler spieler = null;
		for(int i = 0 ; i < getSpieler().length; i++){
			if(getSpieler()[i].isIstDran()){
				spieler = getSpieler()[i];
			}
		}
		return spieler;
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
	
	public boolean lastRundenSpieler(){
		return getCurrentSpielerIndex() +1== getSpieler().length;
		
	}
	
	public class ZuRundeWechseln implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Test");
		}
		
	}

}
