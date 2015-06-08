package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.hs.bremen.gui.RundenGui;


public class RundenController {
	private RundenGui rundenGui;
	private MainController mainController;
	
	public RundenController(MainController mainController){
		rundenGui = new RundenGui(mainController);
		rundenGui.setActionListener(new SpielerWechselListener());
		mainController.getMainFrame().add(rundenGui);
		mainController.getMainFrame().revalidate();
	}

	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			for(int i = 0; i<mainController.getSpieler().length; i++){
				JOptionPane.showMessageDialog(null, "Der nächste Spieler ist an der Reihe"/* + MainController.getSpieler[i]().getName() + "ist an der Reihe"*/);
				//Hier muss irgendwie der spieler eingebunden werden, nur wie?? 
				mainController.getMainFrame().remove(rundenGui);
				mainController.startRunden();
			}
		}
		
	}

}
