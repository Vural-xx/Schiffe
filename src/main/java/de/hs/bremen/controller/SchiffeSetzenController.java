package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.SchiffSetzenGui;

public class SchiffeSetzenController {
	private SchiffSetzenGui schiffSetzenGui;
	
	public SchiffeSetzenController(MainController mainController){
		schiffSetzenGui = new SchiffSetzenGui();
		schiffSetzenGui.setActionListener(new SpielerWechselListener());
		mainController.getMainFrame().add(schiffSetzenGui);
		mainController.getMainFrame().revalidate();
	}

	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Hallooo");
		}
		
	}
}
