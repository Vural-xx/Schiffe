package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.SpielerNameGUI;
import de.hs.bremen.gui.SpielfeldGroeßeGUI;

public class EinstellungController {
	private MainController mainController;
	private SpielerAuswahlGUI spielerAuswahlGUI;
	private SpielerNameGUI spielerNameGUI;
	private SpielfeldGroeßeGUI spielfeldGroeßeGUI;

	
	public EinstellungController(MainController mainController){
		this.mainController= mainController;
		spielerAuswahlGUI= new SpielerAuswahlGUI();
		spielerNameGUI= new SpielerNameGUI();
		spielfeldGroeßeGUI= new SpielfeldGroeßeGUI();
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
			System.out.println(spielerNameGUI.spielerEingabe[0].getText());
			mainController.getMainFrame().remove(spielerNameGUI);
			spielfeldGroeßeGUI.setActionListener(new SpielfeldGroeßeListener());
			mainController.getMainFrame().add(spielfeldGroeßeGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	class SpielfeldGroeßeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainController.getMainFrame().remove(spielfeldGroeßeGUI);
			mainController.startSchiffeSetzen();
			mainController.getMainFrame().revalidate();
		}
		
	}
}



