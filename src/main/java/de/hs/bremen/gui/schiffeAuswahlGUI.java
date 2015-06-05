package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class schiffeAuswahlGUI extends JPanel {
	
	public JLabel schiffeAnzahlText;
	public JLabel zerstoererText;
	public JLabel fregatteText;
	public JLabel korvetteText;
	public JLabel ubootText;
	
	public JPanel container1;
	public JPanel container1_inhalt1;
	public JPanel container1_inhalt2;
	
	public JTextField zerstoererEingabe;
	public JTextField fregatteEingabe;
	public JTextField korvetteEingabe;
	public JTextField ubootEingabe;
	
	public JButton weiter;
	
	
	
	public schiffeAuswahlGUI(){
		
	initComponents();	
	setVisible(true);	
	}
	
	
	
	public void initComponents(){
		this.setLayout(new BorderLayout(0,100));
		container1= new JPanel();
		container1_inhalt1= new JPanel();
		container1_inhalt2= new JPanel();
		weiter= new JButton("weiter");
		container1.setLayout(new GridLayout(1,1,0,50));
		container1_inhalt1.setLayout(new GridLayout(0,1,0,50));
		container1_inhalt2.setLayout(new GridLayout(0,1,0,50));
		
		
		schiffeAnzahlText= new JLabel("Bitte geben Sie Anzahl der Schiffe ein:");
		
		
		zerstoererText= new JLabel("Zerstörer:");
		fregatteText= new JLabel("Fregatte:");
		korvetteText= new JLabel("korvette");
		ubootText= new JLabel("Uboot:");
		
		container1_inhalt1.add(zerstoererText);
		container1_inhalt1.add(fregatteText);
		container1_inhalt1.add(korvetteText);
		container1_inhalt1.add(ubootText);
		
		container1.add(container1_inhalt1);
		
		
		zerstoererEingabe= new JTextField();
		zerstoererEingabe.setPreferredSize(new Dimension(150, 24));
		
		fregatteEingabe= new JTextField();
		fregatteEingabe.setPreferredSize(new Dimension(150, 24));
		
		korvetteEingabe= new JTextField();
		korvetteEingabe.setPreferredSize(new Dimension(150, 24));
		
		ubootEingabe= new JTextField();
		ubootEingabe.setPreferredSize(new Dimension(150, 24));
		
		container1_inhalt2.add(zerstoererEingabe);
		container1_inhalt2.add(fregatteEingabe);
		container1_inhalt2.add(korvetteEingabe);
		container1_inhalt2.add(ubootEingabe);
		
		container1.add(container1_inhalt2);
		
		this.add(schiffeAnzahlText, BorderLayout.PAGE_START);
		this.add(weiter, BorderLayout.PAGE_END);
		
		this.add(container1);

		
	}

}
