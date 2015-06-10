package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import de.hs.bremen.gui.KiAuswahlGUI;
import de.hs.bremen.gui.SchiffSetzenGUI;
import de.hs.bremen.gui.SchiffeAuswahlGUI;
import de.hs.bremen.gui.SpielerAuswahlGUI;
import de.hs.bremen.gui.SpielerNameGUI;
import de.hs.bremen.gui.SpielfeldGroeßeGUI;
import de.hs.bremen.model.Schiff;
import de.hs.bremen.model.Spieler;

public class EinstellungController {
	public MainController mainController;
	private SpielerAuswahlGUI spielerAuswahlGUI;
	private SpielerNameGUI spielerNameGUI;
	private SpielfeldGroeßeGUI spielfeldGroeßeGUI;
	private SchiffeAuswahlGUI schiffeAuswahlGUI;
	private KiAuswahlGUI kiAuswahlGUI;
	private Spieler[] spieler;
	private int spielfeldGroesse;
	private ArrayList<String> schiffe;
	
	public EinstellungController(MainController mainController){
		this.mainController= mainController;
		spielerAuswahlGUI= new SpielerAuswahlGUI();
		spielerNameGUI= new SpielerNameGUI();
		spielfeldGroeßeGUI= new SpielfeldGroeßeGUI();
		schiffeAuswahlGUI= new SchiffeAuswahlGUI();
		kiAuswahlGUI = new KiAuswahlGUI();
		spielerAuswahlGUI.setActionListener(new SpielerAnzahlListener());
		this.mainController.getMainFrame().add(spielerAuswahlGUI);
		this.mainController.getMainFrame().revalidate();
		
		
		
	}
	
	
	class SpielerAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			spielerNameGUI.createNameFenster(spielerAuswahlGUI.getSpielerAnzahl());
			mainController.getMainFrame().remove(spielerAuswahlGUI);
			kiAuswahlGUI.setActionListener(new KiAnzahlListener());
			mainController.getMainFrame().add(kiAuswahlGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	class KiAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.getMainFrame().remove(kiAuswahlGUI);
			spielerNameGUI.setActionListener(new SpielerNameListener());
			mainController.getMainFrame().add(spielerNameGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	class SpielerNameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tempSpieler();
			
			mainController.setSpieler(spieler);
			//Probe 
			for (int j=0; j< spieler.length;j++){
				System.out.println(mainController.getSpieler()[j].getName());
			}
				
			mainController.getMainFrame().remove(spielerNameGUI);
			schiffeAuswahlGUI.setActionListener(new SchiffAnzahlListener());
			mainController.getMainFrame().add(schiffeAuswahlGUI);
			mainController.getMainFrame().revalidate();
			
		}
		
	}
	class SchiffAnzahlListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//tempSpielfeldgroesse();
			//System.out.println(spielfeldGroesse);
			tempArraySchiff();
			System.out.println(schiffe);
			mainController.getMainFrame().remove(schiffeAuswahlGUI);
			spielfeldGroeßeGUI.setActionListener(new SpielfeldGroeßeListener());
			mainController.getMainFrame().add(spielfeldGroeßeGUI);
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	class SpielfeldGroeßeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//tempSpielfeldgroesse();
			//System.out.println(spielfeldGroesse);
			mainController.getMainFrame().remove(spielfeldGroeßeGUI);
			mainController.startSchiffeSetzen();
			mainController.getMainFrame().revalidate();
		}
		
	}
	
	private Spieler[] tempSpieler(){
		spieler = new Spieler[spielerAuswahlGUI.getSpielerAnzahl()];
		for (int i=0; i< spieler.length; i++){	
			spieler[i]= new Spieler(spielerNameGUI.spielerEingabe[i].getText());
		}
		
		return spieler;
		
	}
	
	public int tempSpielfeldgroesse(){
		spielfeldGroesse=Integer.parseInt(spielfeldGroeßeGUI.spielfeldEingabe.getText());
		return spielfeldGroesse;
	}
	
	public void tempArraySchiff(){
			int anzahlZerstoerer= Integer.parseInt(schiffeAuswahlGUI.zerstoererEingabe.getText());
			int anzahlFregatte= Integer.parseInt(schiffeAuswahlGUI.fregatteEingabe.getText());
			int anzahlKorvette= Integer.parseInt(schiffeAuswahlGUI.korvetteEingabe.getText());
			int anzahlUboot= Integer.parseInt(schiffeAuswahlGUI.ubootEingabe.getText());
			schiffe = new ArrayList<String>();
			
			
			for(int i=0;i<anzahlZerstoerer;i++){
				schiffe.add("Zerstörer");
			}
			for(int i=0;i<anzahlFregatte;i++){
				schiffe.add("Fregatte");
			}
			for(int i=0;i<anzahlKorvette;i++){
				schiffe.add("Korvette");
			}
			for(int i=0;i<anzahlUboot;i++){
				schiffe.add("UBoot");
			}
			
		
	}
	
}



