package de.hs.bremen.abstracts;

import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.model.ComputerGegner;
import de.hs.bremen.model.Spieler;


public class AbstractController {
	private Spieler[] spieler;
	private ComputerGegner[] kiGegner;
	private MainFrame mainFrame;
	
	public ComputerGegner[] getKiGegner() {
		return kiGegner;
	}
	public void setKiGegner(ComputerGegner[] kiGegner) {
		this.kiGegner = kiGegner;
	}
	public Spieler[] getSpieler() {
		return spieler;
	}
	public void setSpieler(Spieler[] spieler) {
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
