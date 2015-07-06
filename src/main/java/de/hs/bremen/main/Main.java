package de.hs.bremen.main;

import de.hs.bremen.controller.MainController;

/**
 * Klasse zum Spielen
 * @author Christin
 *
 */
public class Main {

	/**
	 * Controller zur Steuerung
	 */
	static MainController mainController;
	
	/**
	 * Hauptfunktion
	 */
	public static void main(String[] args){
		mainController = new MainController();
		
	}
}
