package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.LastOwnerException;

import javax.swing.JOptionPane;

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
			if(!mainController.lastRundenSpieler()){
				JOptionPane.showMessageDialog(null, "Der nächste Spieler ist an der Reihe");
				mainController.nextSpieler();
				mainController.startSchiffeSetzen();
			}
		}
		
	}
	public class FinishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.nextSpieler();
			JOptionPane.showMessageDialog(null, "Alle Spieler waren an der Reihe. Nun gehts los." + mainController.getCurrentSpieler().getName() + " ist nun mit dem Schießen dran!");
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startRunden();
		}
		
	}
	
	
	
}
