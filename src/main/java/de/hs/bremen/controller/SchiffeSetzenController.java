package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.gui.SchiffSetzenGui;

public class SchiffeSetzenController {
	private SchiffSetzenGui schiffSetzenGui;
	private MainController mainController;
	
	public SchiffeSetzenController(MainController mainController){
		this.mainController = mainController;
		schiffSetzenGui = new SchiffSetzenGui();
		schiffSetzenGui.setActionListener(new SpielerWechselListener(), new FinishListener());
		this.mainController.getMainFrame().add(schiffSetzenGui);
		this.mainController.getMainFrame().revalidate();
	}

	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startSchiffeSetzen();
		}
		
	}
	public class FinishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startRunden();
		}
		
	}
	
	
	
}
