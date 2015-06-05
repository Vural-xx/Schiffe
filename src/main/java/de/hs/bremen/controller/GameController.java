package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.SpielerNameGUI;
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
	private JComponent currentComponent; 
	
	public GameController(){
		mainFrame = new mainFrame();
		startGame();
	}
	
	public void startGame(){
		currentComponent = new SpielerAuswahlGUI();
		((SpielerAuswahlGUI) currentComponent).setActionListener(new SpielerAuswahlListener());
		mainFrame.add(currentComponent);
		mainFrame.revalidate();
	}
	
	public void showSpielernamen(){
		mainFrame.remove(currentComponent);
		currentComponent = null;
		currentComponent = new SpielerNameGUI();
		((SpielerNameGUI)currentComponent).setActionListener(new SpielerNameListener());
		mainFrame.add(currentComponent);
		mainFrame.revalidate();
	}
	
	class SpielerAuswahlListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	System.out.println("Hallo");
        	showSpielernamen();
        }
    }
	
	class SpielerNameListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	System.out.println("Hallo");
        }
    }

}
