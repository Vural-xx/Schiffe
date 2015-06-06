package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import de.hs.bremen.abstracts.AbstractController;
import de.hs.bremen.gui.MainFrame;

public class MainController extends AbstractController {
	private EinstellungController einstellungController;
	private RundenController rundenController;
	private SchiffeSetzenController schiffeSetzenController;
	
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
		startSchiffeSetzen();
		//startRunden();
	}
	
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this);
	}
	
	public void startRunden(){
		rundenController = new RundenController(this);	
	}
	
	public void startGame(){
		
	}
	
	public ActionListener getZuRundeWechselnListener(){
		return new ZuRundeWechseln();
		
	}
	
	
	public class ZuRundeWechseln implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Test");
		}
		
	}

}
