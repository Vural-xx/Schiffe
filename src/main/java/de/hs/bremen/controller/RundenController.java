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
import de.hs.bremen.helper.ConsoleColor;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.ComputerGegner;
import de.hs.bremen.model.Schiff;
import de.hs.bremen.model.Spieler;


public class RundenController {
	private RundenGUI rundenGui;
	private MainController mainController;
	private RundenwechselGUI rundenwechselGUI;
	private EndeGUI endeGui;
	
	public RundenController(MainController mainController){
		rundenGui = new RundenGUI(mainController);
		rundenGui.setActionListener(new SchiffButtonClickedListener());
		this.mainController = mainController;
		this.mainController.getMainFrame().add(rundenGui);
		this.mainController.getMainFrame().revalidate();
	}
	
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
			endeGui = new EndeGUI();
			this.mainController.getMainFrame().add(endeGui);
			this.mainController.getMainFrame().revalidate();
		}
	}
	
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

	
	public boolean ausgeschieden(){
		for(int i = 0;i<mainController.getSpieler().length; i++){
			if(mainController.getSpieler()[i].ausgeschieden()){
				return true;
			}
		}
		return false;
	}
	
	public Schiff getFeuerndesSchiff(String name){
		Schiff schiff = null;
		for(Schiff s: mainController.getCurrentSpielerSchiffe()){
			if(s.getClass().getCanonicalName().equals("de.hs.bremen.model."+name) && s.getWartezeit() == 0){
				schiff = s;
			}
		}
		return schiff;
	}
	
	
	class SchiffButtonClickedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//JOptionPane.showMessageDialog(null, "Der nächste Spieler ist an der Reihe" + mainController.getCurrentSpieler().getName() + "ist an der Reihe");
			JButton schiff = (JButton) e.getSource();
			System.out.println(schiff.getName());
			mainController.setAusgewähltesSchiff(getFeuerndesSchiff(schiff.getName()));
		}
		
	}

}
