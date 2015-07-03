package de.hs.bremen.abstracts;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spiel;


public class AbstractController {
	private Spiel spiel;
	private MainFrame mainFrame;
	
	public AbstractController(){
		spiel = new Spiel();
	}
	public Actor[] getSpieler() {
		return spiel.getSpieler();
	}
	public void setSpieler(Actor[] spieler) {
		this.spiel.setSpieler(spieler);
	}

	public Spiel getSpiel() {
		return spiel;
	}
	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
