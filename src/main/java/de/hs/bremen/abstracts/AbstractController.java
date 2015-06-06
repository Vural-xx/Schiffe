package de.hs.bremen.abstracts;

import de.hs.bremen.gui.mainFrame;

public class AbstractController {
	private mainFrame mainFrame;
	public AbstractController(){
		mainFrame = new mainFrame();
	}
	public mainFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(mainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
