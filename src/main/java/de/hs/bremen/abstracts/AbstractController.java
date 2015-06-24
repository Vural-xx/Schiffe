package de.hs.bremen.abstracts;

import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.ComputerGegner;
import de.hs.bremen.model.Spieler;


public class AbstractController {
	private Actor[] spieler;
	private MainFrame mainFrame;
	
	public Actor[] getSpieler() {
		return spieler;
	}
	public void setSpieler(Actor[] spieler) {
		this.spieler = spieler;
	}

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
