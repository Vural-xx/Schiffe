package de.hs.bremen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.hs.bremen.gui.RundenGUI;
import de.hs.bremen.model.Schiff;


public class RundenController {
	private RundenGUI rundenGui;
	private MainController mainController;
	
	public RundenController(MainController mainController){
		rundenGui = new RundenGUI(mainController);
		rundenGui.setActionListener(new SpielerWechselListener(), new SchiffButtonClickedListener());
		this.mainController = mainController;
		this.mainController.getMainFrame().add(rundenGui);
		this.mainController.getMainFrame().revalidate();
	}
	
	public void gefeuert(){
		this.mainController.getMainFrame().remove(rundenGui);
		if(mainController.getNextSpielerIndex() == 0){
			mainController.wartezeitVerringern();
		}
		mainController.nextSpieler();
		if(mainController.getCurrentSpieler().schiffeOhneWartezeit()){
			rundenGui = new RundenGUI(mainController);
			rundenGui.setActionListener(new SpielerWechselListener(), new SchiffButtonClickedListener());
			this.mainController.getMainFrame().add(rundenGui);
			this.mainController.getMainFrame().revalidate();
		}else{
			JOptionPane.showMessageDialog(null,mainController.getCurrentSpieler().getName()+" hat keine Schiffe ohne Wartezeit und muss die Runde aussetzen. Spieler " + mainController.getSpieler()[mainController.getNextSpielerIndex()].getName()+" ist dran");
			gefeuert();
		}	
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
			JButton schiff = (JButton) e.getSource();
			System.out.println(schiff.getName());
			mainController.setAusgewähltesSchiff(getFeuerndesSchiff(schiff.getName()));
		}
		
	}

}
