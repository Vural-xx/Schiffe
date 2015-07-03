package de.hs.bremen.abstracts;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spiel;


public class AbstractController {
	private Spiel spiel;
	private MainFrame mainFrame;
	
	public Actor[] getSpieler() {
		return spiel.getSpieler();
	}
	public void setSpieler(Actor[] spieler) {
		this.spiel.setSpieler(spieler);
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
