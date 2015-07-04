package de.hs.bremen.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.controller.SchiffeSetzenController;
import de.hs.bremen.enums.Spielfeldmodus;

public class SchiffSetzenGUI extends JPanel{

	private JLabel textLabel3;
	private JLabel textLabel4;
	private JPanel container5;
	private JPanel container6;
	private JButton button1;
	private JButton button2;
	private JTextField textField;
	private SpielerfeldGUI[] spielerfeld;
	public MainController mainController;
	private JLabel zerstoerAnzahl;
	private JLabel fregatteAnzahl;
	private JLabel korvetteAnzahl;
	private JLabel ubootAnzahl;
	public JLabel ausrichtungText;
	private JButton zerstoerer;
	private JButton fregatte;
	private JButton korvette;
	private JButton uboot;
	
	public SchiffSetzenGUI(MainController mainController, SchiffeSetzenController schiffeSetzenController){
		this.mainController = mainController;
		initComponents(schiffeSetzenController);

		setVisible(true);
		
	}
	public void initComponents(SchiffeSetzenController schiffeSetzenController){
		
		textLabel3 = new JLabel("Herzlich Willkommen bei Schiffe versenken!");
		textLabel4 = new JLabel(mainController.getCurrentSpieler().getName() +" ist dran. Bitte setze deine Schiffe");
		button1 = new JButton("Weiter");
		button1.setEnabled(false);
		button2 = new JButton("Weiter");
		button2.setEnabled(false);
		textField = new JTextField();	
		textField.setColumns(2);
		
		//Schiffe zum hinzufügen
		container5 = new JPanel();
		container5.setLayout(new GridLayout(14,2,10,10));
		container5.setPreferredSize(new Dimension(200, 15));
		zerstoerer = new JButton("Zerstörer ");
		zerstoerer.setName("Zerstoerer");
		zerstoerAnzahl = new JLabel(schiffeSetzenController.getAnzahlUngesetzteSchiffe("Zerstoerer")+"X");
		container5.add(zerstoerAnzahl);
		container5.add(zerstoerer);
		
		fregatte = new JButton("Fregatte ");
		fregatte.setName("Fregatte");
		fregatteAnzahl = new JLabel(schiffeSetzenController.getAnzahlUngesetzteSchiffe("Fregatte")+"X");
		container5.add(fregatteAnzahl);
		container5.add(fregatte);
		
		korvette = new JButton("Korvette");
		korvette.setName("Korvette");
		korvetteAnzahl = new JLabel(schiffeSetzenController.getAnzahlUngesetzteSchiffe("Korvette")+"X");
		container5.add(korvetteAnzahl);
		container5.add(korvette);
		
		uboot = new JButton("UBoot");
		uboot.setName("UBoot");
		ubootAnzahl = new JLabel(schiffeSetzenController.getAnzahlUngesetzteSchiffe("UBoot")+"X");
		container5.add(ubootAnzahl);
		container5.add(uboot);

		//Abstand zum nächsten Button erzeugen
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		container5.add(Box.createVerticalGlue());
		if(!mainController.lastRundenSpieler()){
			container5.add(button1);	
		}
		if(mainController.lastRundenSpieler()){
			container5.add(button2);	
		}
		
		//spielfeld für einzelnen spieler erzeugen
		container6 = new JPanel();
		container6.setLayout(new BoxLayout(container6, BoxLayout.PAGE_AXIS));
		spielerfeld = new SpielerfeldGUI[mainController.getSpieler().length];
		textLabel4 = new JLabel(mainController.getCurrentSpieler().getName() +" ist dran. Bitte setze deine Schiffe");
		spielerfeld[mainController.getCurrentSpielerIndex()] = new SpielerfeldGUI(mainController.getSpielfeldgroesse()*15, 15,mainController,Spielfeldmodus.SETZEN);
		container6.add(textLabel4);
		container6.add(spielerfeld[mainController.getCurrentSpielerIndex()]);
		container6.setBorder(BorderFactory.createEmptyBorder(30, 250, 30, 30));//(top, left, bottom, right)
		JLabel infoText = new JLabel ("Änderung der Schiffausrichtung: Drücke die rechte Maustaste");
		container6.add(infoText);
		ausrichtungText= new JLabel("Ihr Schiff ist horizontal ausgerichtet.");
		ausrichtungText.setForeground(Color.blue);
		container6.add(ausrichtungText);	
		
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//(top, left, bottom, right)
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel3, BorderLayout.NORTH);
		this.add(container6, BorderLayout.CENTER);
		this.add(container5, BorderLayout.EAST);

		
	}
	
	public void schiffGesetzt(){
		int zerstoererZahl = mainController.getSchiffeSetzenController().getAnzahlUngesetzteSchiffe("Zerstoerer");    
		int fregatteZahl = mainController.getSchiffeSetzenController().getAnzahlUngesetzteSchiffe("Fregatte");  
		int korvetteZahl = mainController.getSchiffeSetzenController().getAnzahlUngesetzteSchiffe("Korvette");   
		int ubootZahl = mainController.getSchiffeSetzenController().getAnzahlUngesetzteSchiffe("UBoot"); 
		if(zerstoererZahl == 0){
			zerstoerAnzahl.setEnabled(false);
			zerstoerer.setEnabled(false);
		}
		if(fregatteZahl == 0){
			fregatteAnzahl.setEnabled(false);
			fregatte.setEnabled(false);
		}
		if(korvetteZahl == 0){
			korvetteAnzahl.setEnabled(false);
			korvette.setEnabled(false);
		}
		if(ubootZahl == 0){
			ubootAnzahl.setEnabled(false);
			uboot.setEnabled(false);
		}
		if(zerstoererZahl == 0 && fregatteZahl == 0 && korvetteZahl == 0 && ubootZahl == 0){
			if(!mainController.lastRundenSpieler()){
				button1.setEnabled(true);
			}else{
				button2.setEnabled(true);
			}
			
		}
		zerstoerAnzahl.setText(zerstoererZahl+"X");
		fregatteAnzahl.setText(fregatteZahl+"X");
		korvetteAnzahl.setText(korvetteZahl+"X");
		ubootAnzahl.setText(ubootZahl+"X");
		revalidate();
	}
	
	public void setActionListener(ActionListener spielerWechselListener, ActionListener finishListener, ActionListener schiffButtonListener){
		button1.addActionListener(spielerWechselListener);
		button2.addActionListener(finishListener);
		zerstoerer.addActionListener(schiffButtonListener);
		korvette.addActionListener(schiffButtonListener);
		fregatte.addActionListener(schiffButtonListener);
		uboot.addActionListener(schiffButtonListener);
	}
}
