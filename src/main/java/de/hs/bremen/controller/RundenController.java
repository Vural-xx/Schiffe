package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.hs.bremen.enums.Spielerart;
import de.hs.bremen.gui.EndeGUI;
import de.hs.bremen.gui.RundenGUI;
import de.hs.bremen.gui.RundenwechselGUI;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.ComputerGegner;
import de.hs.bremen.model.Schiff;

/**
 * @author Christin
 *
 */
public class RundenController {
	
	/**
	 * GUI zum Spielen der Runden
	 */
	private RundenGUI rundenGui;
	
	/**
	 * HauptController
	 */
	private MainController mainController;
	
	/**
	 * GUI beim Runden spielen, zum Wechsel der Spieler
	 */
	private RundenwechselGUI rundenwechselGUI;
	
	/**
	 * Abschluss GUI
	 */
	private EndeGUI endeGui;
	
	
	/**
	 * Konstruktor
	 * @param mainController: HauptController
	 */
	public RundenController(MainController mainController){
		rundenGui = new RundenGUI(mainController);
		rundenGui.setActionListener(new SchiffButtonClickedListener());
		this.mainController = mainController;
		this.mainController.getMainFrame().add(rundenGui);
		this.mainController.getMainFrame().revalidate();
	}
	
	/**
	 * Feuern der Menschen oder KI-Gegner auf das Gegnerspielfeld
	 */
	public void gefeuert(){
		this.mainController.getMainFrame().remove(rundenGui);
		if(ausgeschieden()){
			rausschmeissen();
		}
		if(mainController.getSpieler().length>1){
			if(mainController.getNextSpielerIndex() == 0){
				mainController.wartezeitVerringern();
			}
			mainController.nextSpieler();
			
			if(mainController.getCurrentSpieler().schiffeOhneWartezeit() && mainController.getCurrentSpieler().getSpielerart()==Spielerart.MENSCH){
				rundenwechselGUI = new RundenwechselGUI(mainController);
				this.mainController.getMainFrame().add(rundenwechselGUI);
				this.mainController.getMainFrame().revalidate();
				JOptionPane.showMessageDialog(mainController.getMainFrame(), mainController.getCurrentSpieler().getName() + " ist an der Reihe");
				rundenGui = new RundenGUI(mainController);
				rundenGui.setActionListener( new SchiffButtonClickedListener());
				this.mainController.getMainFrame().add(rundenGui);
				this.mainController.getMainFrame().revalidate();
			} else if(mainController.getCurrentSpieler().schiffeOhneWartezeit() && mainController.getCurrentSpieler().getSpielerart()== Spielerart.KI){
				ComputerGegner computerGegner =(ComputerGegner) mainController.getCurrentSpieler();
				computerGegner.intelligent();
				gefeuert();
			}else{
				JOptionPane.showMessageDialog(null,mainController.getCurrentSpieler().getName()+" hat keine Schiffe ohne Wartezeit und muss die Runde aussetzen. Spieler " + mainController.getSpieler()[mainController.getNextSpielerIndex()].getName()+" ist dran");
				gefeuert();
			}
		}else{
			endeGui = new EndeGUI(mainController.getSpieler()[0].getName());
			this.mainController.getMainFrame().add(endeGui);
			this.mainController.getMainFrame().revalidate();
		}
	}
	
	/**
	 * Entfernt den Spieler sobald er aus dem Spiel ausgeschieden ist
	 */
	public void rausschmeissen(){
		ArrayList<Actor> tempSpieler;
		Actor[] spieler;
		tempSpieler=null;
		tempSpieler = new ArrayList<Actor>();
		for(int i = 0;i<mainController.getSpieler().length; i++){
			if(!mainController.getSpieler()[i].ausgeschieden()){
				tempSpieler.add(mainController.getSpieler()[i]);
			}else{
				JOptionPane.showMessageDialog(mainController.getMainFrame(), mainController.getSpieler()[i].getName() + " ist ausgeschieden");
			}	
		}
		spieler = new Actor[tempSpieler.size()];
		mainController.setSpieler((Actor[]) tempSpieler.toArray(spieler));
	}

	
	/**
	 * Überprüft, ob ein Spieler ausgeschieden ist. 
	 * @return true oder false
	 */
	public boolean ausgeschieden(){
		for(int i = 0;i<mainController.getSpieler().length; i++){
			if(mainController.getSpieler()[i].ausgeschieden()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Übergibt das Schiff, mit dem gefeuert werden kann
	 * @param name Name des Schiffs
	 * @return
	 */
	public Schiff getFeuerndesSchiff(String name){
		Schiff schiff = null;
		for(Schiff s: mainController.getCurrentSpielerSchiffe()){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && s.getWartezeit() == 0){
				schiff = s;
			}
		}
		return schiff;
	}
	
	
	/**
	 * Spielerwechsel nach Setzen des Schiffes
	 * @author Christin
	 *
	 */
	class SchiffButtonClickedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton schiff = (JButton) e.getSource();
			mainController.setAusgewähltesSchiff(getFeuerndesSchiff(schiff.getName()));
		}
	}
}
