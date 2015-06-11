package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.FeatureDescriptor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SchiffeAuswahlGUI extends JPanel {
	
	private JLabel schiffeAnzahlText;
	private JLabel zerstoererText;
	private JLabel fregatteText;
	private JLabel korvetteText;
	private JLabel ubootText;
	
	private JPanel container1;
	private JPanel fuellcontainer;

	
	public JTextField zerstoererEingabe;
	public JTextField fregatteEingabe;
	public JTextField korvetteEingabe;
	public JTextField ubootEingabe;
	
	private JButton weiter;
	private GridBagConstraints gbc;
	

	
	
	public SchiffeAuswahlGUI(){
		
	initComponents();	
	setVisible(true);	
	}
	
	
	
	public void initComponents(){
		gbc= new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		gbc.gridx=1;
		gbc.insets=new Insets(60, 0, 0, 0);
		
		container1= new  JPanel();
		container1.setLayout(new GridLayout(0,2,10,10));
		
		fuellcontainer= new JPanel();

		weiter = new JButton("weiter");
		
		schiffeAnzahlText= new JLabel("Bitte geben Sie Anzahl der Schiffe ein:");
		
		
		zerstoererText= new JLabel("Zerstörer:");
		fregatteText= new JLabel("Fregatte:");
		korvetteText= new JLabel("korvette");
		ubootText= new JLabel("Uboot:");
		
		
		zerstoererEingabe= new JTextField();
		fregatteEingabe= new JTextField();
		korvetteEingabe= new JTextField();
		ubootEingabe= new JTextField();
		
		
		zerstoererEingabe.setPreferredSize(new Dimension(50, 24));
		fregatteEingabe.setPreferredSize(new Dimension(50, 24));
		korvetteEingabe.setPreferredSize(new Dimension(50, 24));
		ubootEingabe.setPreferredSize(new Dimension(50, 24));
		
		container1.add(zerstoererText);
		container1.add(zerstoererEingabe);
		container1.add(fregatteText);
		container1.add(fregatteEingabe);
		container1.add(korvetteText);
		container1.add(korvetteEingabe);
		container1.add(ubootText);
		container1.add(ubootEingabe);
		
		this.add(schiffeAnzahlText,gbc);
		this.add(container1,gbc);
		this.add(weiter,gbc);
		this.add(fuellcontainer,gbc);
		

		
	}

	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
}
