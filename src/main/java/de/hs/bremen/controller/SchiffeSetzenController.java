package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.LastOwnerException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.hs.bremen.gui.MainFrame;
import de.hs.bremen.gui.SchiffSetzenGUI;
import de.hs.bremen.model.Schiff;

public class SchiffeSetzenController {
	private SchiffSetzenGUI schiffSetzenGui;
	private MainController mainController;
	 
	public SchiffeSetzenController(MainController mainController){
		this.mainController = mainController;
		schiffSetzenGui = new SchiffSetzenGUI(mainController);
		schiffSetzenGui.setActionListener(new SpielerWechselListener(), new FinishListener(), new SchiffButtonClickedListener());
		this.mainController.getMainFrame().add(schiffSetzenGui);
		this.mainController.getMainFrame().revalidate();
	}
	
	public int getAnzahlUngesetzteSchiffe(String name){
		int counter = 0;
		for(Schiff s: mainController.getCurrentSpielerSchiffe()){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && !s.isPlatziert()){
				counter = counter++;
			}
		}
		return counter;
	}
	
	public Schiff getZuSetztendesSchiff(String name){
		Schiff schiff = null;
		for(Schiff s: mainController.getCurrentSpielerSchiffe()){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && !s.isPlatziert()){
				schiff = s;
			}
		}
		return schiff;
	}

	public SchiffSetzenGUI getSchiffSetzenGui() {
		return schiffSetzenGui;
	}

	public void setSchiffSetzenGui(SchiffSetzenGUI schiffSetzenGui) {
		this.schiffSetzenGui = schiffSetzenGui;
	}
	
	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(schiffSetzenGui);
			if(!mainController.lastRundenSpieler()){
				mainController.nextSpieler();
				mainController.setAusgewähltesSchiff(null);
				JOptionPane.showMessageDialog(null, mainController.getCurrentSpieler().getName() + " ist an der Reihe");
				mainController.startSchiffeSetzen();
			}
		}
		
	}
	class SchiffButtonClickedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton schiff = (JButton) e.getSource();
			mainController.setAusgewähltesSchiff(getZuSetztendesSchiff(schiff.getName()));
		}
		
	}
	public class FinishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.nextSpieler();
			JOptionPane.showMessageDialog(null, "Alle Spieler waren an der Reihe, nun gehts los! " + mainController.getCurrentSpieler().getName() + " beginnt!");
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startRunden();
		}
		
	}
	
	
	
}
