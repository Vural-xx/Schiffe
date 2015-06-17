package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Spielfeldmodus;

public class RundenGUI extends JPanel {

	private JPanel container;
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	
	private JButton button1;
	
	private JLabel textLabel2;
	private JLabel textLabel1;
	private JTabbedPane tab;
	private SpielerfeldGUI spielerfeld;
	private MainController mainController;
	private JButton zerstoerer;
	private JButton fregatte;
	private JButton korvette;
	private JButton uboot;
	
	public RundenGUI(MainController mainController){
		this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	private void initComponents(){
		textLabel1 = new JLabel("Wähle das Schiff zum feuern");
		textLabel2 = new JLabel(mainController.getCurrentSpieler().getName());
		button1 = new JButton("Fertig");
		spielerfeld = new SpielerfeldGUI(375, 15,mainController, Spielfeldmodus.SPIELER);
		spielerfeld.drawSpielfeld();
		
		tab = new JTabbedPane();
		SpielerfeldGUI spielerfeld2[] = new SpielerfeldGUI[mainController.getSpieler().length];
		for(int i = 0; i< mainController.getSpieler().length; i++){
			if(!mainController.getSpieler()[i].isIstDran()){
				spielerfeld2[i] = new SpielerfeldGUI(375, 15,mainController,Spielfeldmodus.GEGNER);
				spielerfeld2[i].setSpieler(mainController.getSpieler()[i]);
				spielerfeld2[i].drawGegnerSpielfeld();
				tab.addTab(mainController.getSpieler()[i].getName(), spielerfeld2[i]);
				
			}
		}
		
		container = new JPanel();
		container.add(textLabel1);	

		//Schiffeauswahl
		container1 = new JPanel();
		container1.setLayout(new BoxLayout(container1, BoxLayout.PAGE_AXIS));
		container1.add(textLabel1);
		
		if(mainController.schiffOhneWartezeit("Zerstoerer")){
			zerstoerer = new JButton("Zerstörer");
			zerstoerer.setName("Zerstoerer");
			container1.add(zerstoerer);
		}
		
		if(mainController.schiffOhneWartezeit("Fregatte")){
			fregatte = new JButton("Fregatte");
			fregatte.setName("Fregatte");
			container1.add(fregatte);
		}
		
		if(mainController.schiffOhneWartezeit("Korvette")){
			korvette = new JButton("Korvette");
			korvette.setName("Korvette");
			container1.add(korvette);
		}
		
		if(mainController.schiffOhneWartezeit("UBoot")){
			uboot = new JButton("UBoot");
			uboot.setName("UBoot");
			container1.add(uboot);
		}
		
		
		container1.add(Box.createRigidArea(new Dimension(0, 0)));
		container1.add(Box.createVerticalGlue());
		container1.add(button1);
		
		//Spielfelder Spieler & Gegner
		container2 = new JPanel();
		container2.setLayout(new BoxLayout(container2, BoxLayout.PAGE_AXIS));
		container2.add(textLabel2);
		container2.add(spielerfeld);
		
		container3 = new JPanel();
		container3.setLayout(new GridLayout(1,2));
		container3.add(container2);
		container3.add(tab);
		
		this.setLayout(new BorderLayout(5,5));
		this.add(container,BorderLayout.PAGE_START);
		this.add(container1, BorderLayout.WEST);
		this.add(container3, BorderLayout.CENTER);
	}
	
	public void setActionListener(ActionListener spielerwechselListener, ActionListener schiffButtonClickedListener){
		button1.addActionListener(spielerwechselListener);
		if(zerstoerer != null){
			zerstoerer.addActionListener(schiffButtonClickedListener);
		}
		if(fregatte != null){
			fregatte.addActionListener(schiffButtonClickedListener);
		}
		if(korvette != null){
			korvette.addActionListener(schiffButtonClickedListener);
		}
		if(uboot != null){
			uboot.addActionListener(schiffButtonClickedListener);
		}		
	}
		
}
