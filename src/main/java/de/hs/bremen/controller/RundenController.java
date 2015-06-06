package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.RundenGui;


public class RundenController {
private RundenGui rundenGui;
	
	public RundenController(MainController mainController){
		rundenGui = new RundenGui();
		rundenGui.setActionListener(new SpielerWechselListener());
		mainController.getMainFrame().add(rundenGui);
		mainController.getMainFrame().revalidate();
	}

	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("34466 Test");
		}
		
	}

}
