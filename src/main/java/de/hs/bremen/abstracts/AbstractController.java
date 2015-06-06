package de.hs.bremen.abstracts;

import de.hs.bremen.gui.MainFrame;

public class AbstractController {
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
