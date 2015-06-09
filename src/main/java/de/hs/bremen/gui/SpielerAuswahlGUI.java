package de.hs.bremen.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;
import javax.swing.plaf.basic.BasicBorders.ToggleButtonBorder;

public class SpielerAuswahlGUI extends JPanel implements ActionListener {
	
	private JToggleButton spieler1;
	private JToggleButton spieler2;
	private JToggleButton spieler3;
	private JToggleButton spieler4;
	private JToggleButton spieler5;
	private JToggleButton spieler6;
	
	private JButton weiter;
	private int spielerAnzahl;
	
	// JPanel Public gesetzt. Kp bei Private zeigt er das die variablen nicht genutzt werden
	
	
	
	public JPanel container1_inhalt1;
	
	
	public int getSpielerAnzahl() {
		return spielerAnzahl;
	}
	public void setSpielerAnzahl(int spielerAnzahl) {
		this.spielerAnzahl = spielerAnzahl;
	}


	public JPanel container1_inhalt2;
	public JPanel container1_inhalt3;
	
	private JLabel spielerAnzahltext;
	
	FlowLayout flowlayout= new FlowLayout();
	
	
	public SpielerAuswahlGUI(){
		
		initComponents();

		setVisible(true);
		
}
	public void initComponents(){
		
		this.setLayout(new GridLayout(0,1,0,0));
		
		
		JPanel container1_inhalt1 = new JPanel();
		JPanel container1_inhalt2 = new JPanel();
		JPanel container1_inhalt3 = new JPanel();
		
	
		
		spieler1 = new JToggleButton("1");
		spieler2 = new JToggleButton("2");
		spieler3 = new JToggleButton("3");
		spieler4 = new JToggleButton("4");
		spieler5 = new JToggleButton("5");
		spieler6 = new JToggleButton("6");
		
		weiter = new JButton("weiter");
		
		
		
		spieler1.addActionListener(this);
		spieler2.addActionListener(this);
		spieler3.addActionListener(this);
		spieler4.addActionListener(this);
		spieler5.addActionListener(this);
		spieler6.addActionListener(this);
		

		
		spielerAnzahltext= new JLabel("Bitte wählen Sie die Spieleranzahl aus:");
		container1_inhalt1.add(spielerAnzahltext);
		
		container1_inhalt2.setLayout(flowlayout);
		container1_inhalt2.add(spieler1);
		container1_inhalt2.add(spieler2);
		container1_inhalt2.add(spieler3);
		container1_inhalt2.add(spieler4);
		container1_inhalt2.add(spieler5);
		container1_inhalt2.add(spieler6);
		
		container1_inhalt3.add(weiter);
		
		this.add(container1_inhalt1);
		this.add(container1_inhalt2);
		this.add(container1_inhalt3);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// Warum ich hier keine Klammern setzen muss kp.. geht auch so. vll ja mit klammer besser
		if(e.getSource() == spieler1)auswahlZahlSpieler(1);
		if(e.getSource() == spieler2)auswahlZahlSpieler(2);
		if(e.getSource() == spieler3)auswahlZahlSpieler(3);
		if(e.getSource() == spieler4)auswahlZahlSpieler(4);
		if(e.getSource() == spieler5)auswahlZahlSpieler(5);
		if(e.getSource() == spieler6)auswahlZahlSpieler(6);
		
	}
	
	public int auswahlZahlSpieler(int i){
		spielerAnzahl=i;
		return i;
	}
	

	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
	
	
	
	

}

