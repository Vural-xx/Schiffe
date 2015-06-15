package de.hs.bremen.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.hs.bremen.controller.MainController;
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
	
	public SchiffSetzenGUI(MainController mainController){
		this.mainController = mainController;
		initComponents();

		setVisible(true);
		
	}
	public void initComponents(){
		
		textLabel3 = new JLabel("Herzlich Willkommen bei Schiffe versenken!");
		textLabel4 = new JLabel(mainController.getCurrentSpieler().getName() +" ist dran. Bitte setze deine Schiffe");
		button1 = new JButton("Nächster Spieler");
		button1.setEnabled(false);
		button2 = new JButton("Spiel beginnen");
		button2.setEnabled(false);
		textField = new JTextField();	
		textField.setColumns(2);
		
		//Schiffe zum hinzufügen
		container5 = new JPanel();
		container5.setLayout(new BoxLayout(container5, BoxLayout.PAGE_AXIS));
		zerstoerer = new JButton("Zerstörer ");
		zerstoerer.setName("Zerstoerer");
		zerstoerAnzahl = new JLabel(mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Zerstoerer")+"X");
		container5.add(zerstoerAnzahl);
		container5.add(zerstoerer);
		
		fregatte = new JButton("Fregatte ");
		fregatte.setName("Fregatte");
		fregatteAnzahl = new JLabel(mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Fregatte")+"X");
		container5.add(fregatteAnzahl);
		container5.add(fregatte);
		
		korvette = new JButton("Korvette");
		korvette.setName("Korvette");
		korvetteAnzahl = new JLabel(mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Korvette")+"X");
		container5.add(korvetteAnzahl);
		container5.add(korvette);
		
		uboot = new JButton("UBoot");
		uboot.setName("UBoot");
		ubootAnzahl = new JLabel(mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.UBoot")+"X");
		container5.add(ubootAnzahl);
		container5.add(uboot);
	
		//container5.add(bildLabel);
		container5.add(Box.createRigidArea(new Dimension(0, 0)));
		container5.add(Box.createVerticalGlue());
		if(!mainController.lastRundenSpieler()){
			container5.add(button1);	
		}
		if(mainController.lastRundenSpieler()){
			container5.add(button2);	
		}
		//container5.add(button2);
		
		//spielfeld für einzelnen spieler erzeugen
		container6 = new JPanel();
		container6.setLayout(new BoxLayout(container6, BoxLayout.PAGE_AXIS));
		spielerfeld = new SpielerfeldGUI[mainController.getSpieler().length];
		textLabel4 = new JLabel(mainController.getCurrentSpieler().getName() +" ist dran. Bitte setze deine Schiffe");
		spielerfeld[mainController.getCurrentSpielerIndex()] = new SpielerfeldGUI(375, 15,mainController,Spielfeldmodus.SETZEN);
		container6.add(textLabel4);
		container6.add(spielerfeld[mainController.getCurrentSpielerIndex()]);
		ausrichtungText= new JLabel("Ihr Schiff ist horizontal ausgerichtet.");
		
		ausrichtungText.setForeground(Color.blue);
		container6.add(ausrichtungText);	
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel3, BorderLayout.NORTH);
		this.add(container6, BorderLayout.CENTER);
		this.add(container5, BorderLayout.EAST);

		
	}
	
	public void schiffGesetzt(){
		int zerstoererZahl = mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Zerstoerer");
		int fregatteZahl = mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Fregatte");
		int korvetteZahl = mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.Korvette");
		int ubootZahl =  mainController.getCurrentSpieler().getSpielfeld().getAnzahlUngesetzteSchiffe("de.hs.bremen.model.UBoot");
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
