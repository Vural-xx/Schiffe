package de.hs.bremen.abstracts;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Spieler;


public class AbstractController {
	private Spieler[] spieler;
	
	public Spieler[] getSpieler() {
		return spieler;
	}
	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}
	private MainFrame mainFrame;
	public AbstractController(){
		mainFrame = new MainFrame();
	}
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
