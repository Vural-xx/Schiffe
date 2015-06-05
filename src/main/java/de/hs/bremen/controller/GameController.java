package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.TestFrame;
import de.hs.bremen.gui.TestGui;
import de.hs.bremen.gui.mainFrame;
import de.hs.bremen.model.Spieler;

public class GameController{
		
	/**
	 * Spieler die an dem Spiel beteiligt sind.
	 */
	private Spieler[] spieler;
	
	/**
	 * Runden die gespielt wurden.
	 */
	private int runde;
	private mainFrame mainFrame;
	private TestFrame testFrame;
	
	public GameController(){
		testFrame = new TestFrame();
		startGame();
	}
	
	public void startGame(){
		testFrame.add(new TestGui());
		testFrame.repaint();
	}
	
	class spielerAuswahlListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	/*
        	if(e.getSource() == spieler1)auswahlZahlSpieler(1);
    		if(e.getSource() == spieler2)auswahlZahlSpieler(2);
    		if(e.getSource() == spieler3)auswahlZahlSpieler(3);
    		if(e.getSource() == spieler4)auswahlZahlSpieler(4);
    		if(e.getSource() == spieler5)auswahlZahlSpieler(5);
    		if(e.getSource() == spieler6)auswahlZahlSpieler(6);*/
        	System.out.println("Hallo");
        }
    }

}
