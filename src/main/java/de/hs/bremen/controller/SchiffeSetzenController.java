package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.hs.bremen.abstracts.Schiff;
import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.gui.SchiffSetzenGUI;
import de.hs.bremen.model.ComputerGegner;

/**
 * @author Christin
 *
 */
public class SchiffeSetzenController {
	
	/**
	 * GUI zum Setzen der Schiffe
	 */
	private SchiffSetzenGUI schiffSetzenGui;
	
	/**
	 * HauptController
	 */
	private MainController mainController;
	
	/**
	 * Schiffe für jeden Spieler der gesetzt werden muss
	 */
	HashMap<String, ArrayList<Schiff>> zuSetzendeSchiffe;
	
	 
	/**
	 * Konstruktor
	 * @param mainController: HauptController
	 * @param zuSetzendeSchiffe: welches Schiff gesetzt werden muss
	 */
	public SchiffeSetzenController(MainController mainController, HashMap<String, ArrayList<Schiff>> zuSetzendeSchiffe){
		this.mainController = mainController;
		this.zuSetzendeSchiffe = zuSetzendeSchiffe;
		startSchiffsetzenGui();
	}
	
	/**
	 * Getter SchiffSetzenGUI
	 * @return
	 */
	public SchiffSetzenGUI getSchiffSetzenGui() {
		return schiffSetzenGui;
	}

	/**
	 * Setter SchiffSetzenGUI
	 * @param schiffSetzenGui
	 */
	public void setSchiffSetzenGui(SchiffSetzenGUI schiffSetzenGui) {
		this.schiffSetzenGui = schiffSetzenGui;
	}
	
	/**
	 * Spielerwechsel beim Setzen der Schiffe
	 */
	public void startSchiffsetzenGui(){
		schiffSetzenGui = new SchiffSetzenGUI(mainController,this);
		schiffSetzenGui.setActionListener(new SpielerWechselListener(), new FinishListener(), new SchiffButtonClickedListener());
		this.mainController.getMainFrame().add(schiffSetzenGui);
		this.mainController.getMainFrame().revalidate();
	}
	
	/**
	 * Übergibt die Anzahl der Schiffe, die noch zu setzen sind
	 * @param name: Name des Schiffes
	 * @return Anzahl der ungesetzten Schiffe
	 */
	public int getAnzahlUngesetzteSchiffe(String name){
		int counter = 0;
		for(Schiff s: zuSetzendeSchiffe.get(mainController.getCurrentSpieler().getName())){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && !s.isPlatziert()){
				counter = counter + 1;
			}
		}
		return counter;
	}
	
	/**
	 * Übergibt die Schiffe die noch zu setzen sind
	 * @param name: Name des Schiffs
	 * @return
	 */
	public Schiff getZuSetztendesSchiff(String name){
		Schiff schiff = null;
		for(Schiff s: zuSetzendeSchiffe.get(mainController.getCurrentSpieler().getName())){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && !s.isPlatziert()){
				schiff = s;
			}
		}
		return schiff;
	}

	
	
	/**
	 * Klasse SpielerWechselListener zum Wechsel der Spieler nach dem Setzen der Schiffe
	 * @author Christin
	 *
	 */
	class SpielerWechselListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.getMainFrame().remove(schiffSetzenGui);
			if(!mainController.lastRundenSpieler()){
				mainController.nextSpieler();
				mainController.setAusgewähltesSchiff(null);
				JOptionPane.showMessageDialog(null, mainController.getCurrentSpieler().getName() + " ist an der Reihe");
				if(mainController.getCurrentSpieler().getSpielerart() == Spielerart.MENSCH){
					mainController.startSchiffeSetzen();
				}else{
					ComputerGegner c = (ComputerGegner) mainController.getCurrentSpieler();
					c.schiffeSetzen(mainController.getEinstellungController().getSchiffe().get(c.getName()));
					this.actionPerformed(new ActionEvent(e.getSource(), e.getID(), e.getActionCommand()));
				}
			}else{
				mainController.nextSpieler();
				JOptionPane.showMessageDialog(null, "Alle Spieler waren an der Reihe, nun gehts los! " + mainController.getCurrentSpieler().getName() + " beginnt!");
				mainController.getMainFrame().remove(schiffSetzenGui);
				mainController.startRunden();
			}
		}
		
	}
	/**
	 * Klasse zum Setzen der Schiffe in der GUI
	 * @author Christin
	 *
	 */
	class SchiffButtonClickedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton schiff = (JButton) e.getSource();
			Schiff zusetzendesSchiff = getZuSetztendesSchiff(schiff.getName());
			mainController.setAusgewähltesSchiff(zusetzendesSchiff);
		}
		
	}
	
	/**
	 * Klasse zum Wechsel von der SchiffsetzenGUI zur RundenGUI
	 * @author Christin
	 *
	 */
	public class FinishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainController.nextSpieler();
			JOptionPane.showMessageDialog(null, "Alle Spieler waren an der Reihe, nun gehts los! " + mainController.getCurrentSpieler().getName() + " beginnt!");
			mainController.getMainFrame().remove(schiffSetzenGui);
			mainController.startRunden();
		}
	}
}
