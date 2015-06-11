package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.gui.SchiffSetzenGUI;

public class SchiffeSetzenController {
	private SchiffSetzenGUI schiffSetzenGui;
	public MainController mainController;
	
	public SchiffeSetzenController(MainController mainController){
		this.mainController = mainController;
		schiffSetzenGui = new SchiffSetzenGUI(mainController);
		schiffSetzenGui.setActionListener(new SpielerWechselListener(), new FinishListener());
		this.mainController.getMainFrame().add(schiffSetzenGui);
		this.mainController.getMainFrame().revalidate();
	}

	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.nextSpieler();
			mainController.startSchiffeSetzen();
		}
		
	}
	
	class SchiffgewähltListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		}
		
	}
	class FinishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startRunden();
		}
		
	}
	
	
	
}
