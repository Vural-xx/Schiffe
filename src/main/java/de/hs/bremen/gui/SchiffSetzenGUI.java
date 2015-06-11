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

public class SchiffSetzenGUI extends JPanel{

	private JLabel textLabel3;
	private JLabel textLabel4;
	private JPanel container5;
	private JPanel container6;
	private JLabel bildLabel;
	private JButton button1;
	private JButton button2;
	private JTextField textField;
	private SpielerfeldGUI[] spielerfeld;
	public MainController mainController;
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
		button2 = new JButton("Spiel beginnen");
		textField = new JTextField();	
		textField.setColumns(2);
		
		bildLabel = new JLabel();
		bildLabel.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\submarine_0.gif"));
		
		
		
		//Schiffe zum hinzufügen
		container5 = new JPanel();
		container5.setLayout(new BoxLayout(container5, BoxLayout.PAGE_AXIS));
		zerstoerer = new JButton("Zerstörer ");
		zerstoerer.setBackground(Color.red);
		zerstoerer.setName("Zerstörer");
		container5.add(zerstoerer);
		
		fregatte = new JButton("Fregatte ");
		fregatte.setBackground(Color.green);
		fregatte.setName("Fregatte");
		container5.add(fregatte);
		
		korvette = new JButton("Korvette");
		korvette.setBackground(Color.blue);
		korvette.setName("Korvette");
		container5.add(korvette);
		
		uboot = new JButton("UBoot");
		uboot.setBackground(Color.yellow);
		uboot.setName("Uboot");
		container5.add(uboot);
	
		//container5.add(bildLabel);
		container5.add(Box.createRigidArea(new Dimension(0, 0)));
		container5.add(Box.createVerticalGlue());
		if(!mainController.lastRundenSpieler()){
			container5.add(button1);	
		}
		container5.add(button2);
		
		//Spielfeld
		container6 = new JPanel();
		container6.setLayout(new BoxLayout(container6, BoxLayout.PAGE_AXIS));
		//container6.add(textLabel4);
		//spielerfeld[0] = new SpielerfeldGUI(375, 15);
		//container6.add(spielerfeld[0]);

		//spielfeld für einzelnen spieler erzeugen
		spielerfeld = new SpielerfeldGUI[mainController.getSpieler().length];
		textLabel4 = new JLabel(mainController.getCurrentSpieler().getName() +" ist dran. Bitte setze deine Schiffe");
		spielerfeld[mainController.getCurrentSpielerIndex()] = new SpielerfeldGUI(375, 15);
		container6.add(textLabel4);
		container6.add(spielerfeld[mainController.getCurrentSpielerIndex()]);

		
		//container6.add(textLabel4);
		//container6.add(spielerfeld);
			
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel3, BorderLayout.NORTH);
		this.add(container6, BorderLayout.CENTER);
		this.add(container5, BorderLayout.EAST);
		
	}
	
	public void setActionListener(ActionListener l, ActionListener p){
		button1.addActionListener(l);
		button2.addActionListener(p);
		
	}
	
	
}
