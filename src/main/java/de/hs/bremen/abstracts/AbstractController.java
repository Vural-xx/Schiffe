package de.hs.bremen.abstracts;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spiel;


/**
 * Abstrakte Klasse für die Controller
 * @author Christin
 *
 */
public class AbstractController {
	
	/**
	 * Das Spiel welches gespielt wird
	 */
	private Spiel spiel;
	
	/**
	 * die MainFrame, die genutzt wird
	 */
	private MainFrame mainFrame;
	
	/**
	 * Konstruktor
	 */
	public AbstractController(){
		spiel = new Spiel();
	}
	/**
	 * Getter Spieler
	 * @return
	 */
	public Actor[] getSpieler() {
		return spiel.getSpieler();
	}
	/**
	 * Setter Spieler
	 * @param spieler: Setzt den Spieler
	 */
	public void setSpieler(Actor[] spieler) {
		this.spiel.setSpieler(spieler);
	}

	/**
	 * Getter Spiel
	 * @return
	 */
	public Spiel getSpiel() {
		return spiel;
	}
	/**
	 * Setter Spiel
	 * @param spiel: Setzt das Spiel
	 */
	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}
	
	/**
	 * Getter MainFrame
	 * @return
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	/**
	 * Setter MainFrame
	 * @param mainFrame Setzt die MainFrame
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
