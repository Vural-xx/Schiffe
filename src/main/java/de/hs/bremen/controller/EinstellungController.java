package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.SpielerNameGUI;

public class EinstellungController {
	private MainController mainController;
	public SpielerAuswahlGUI spielerAuswahlGUI;
	public SpielerNameGUI spielerNameGUI;

	
	public EinstellungController(MainController mainController){
		this.mainController= mainController;
		spielerAuswahlGUI= new SpielerAuswahlGUI();
		spielerNameGUI= new SpielerNameGUI();
		spielerAuswahlGUI.setActionListener(new SpielerAnzahlListener());
		this.mainController.getMainFrame().add(spielerAuswahlGUI);
		this.mainController.getMainFrame().revalidate();
		
		
		
	}
	
	
	class SpielerAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(spielerAuswahlGUI);
			spielerNameGUI.setActionListener(new SpielerNameListener());
			mainController.getMainFrame().add(spielerNameGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	class SpielerNameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(spielerNameGUI);
			mainController.startSchiffeSetzen();
		}
		
	}
}
