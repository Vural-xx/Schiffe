package de.hs.bremen.controller;

import javax.swing.JFrame;

import de.hs.bremen.abstracts.AbstractController;
import de.hs.bremen.gui.MainFrame;

public class MainController extends AbstractController {
	private MainController mainController;
	private EinstellungController einstellungController;
	private RundenController rundenController;
	private SchiffeSetzenController schiffeSetzenController;
	
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
		startSchiffeSetzen();
	}
	
	public void startSchiffeSetzen(){
		schiffeSetzenController = new SchiffeSetzenController(this);
		
	}
	
	public void startGame(){
		
	}

}
