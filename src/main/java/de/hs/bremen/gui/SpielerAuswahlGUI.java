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
import javax.swing.OverlayLayout;

public class SpielerAuswahlGUI extends JPanel implements ActionListener {
	
	private JButton spieler1;
	private JButton spieler2;
	private JButton spieler3;
	private JButton spieler4;
	private JButton spieler5;
	private JButton spieler6;
	
	private JButton weiter;
	private int a;
	
	// JPanel Public gesetzt. Kp bei Private zeigt er das die variablen nicht genutzt werden
	
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public JPanel container1_inhalt1;
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
		
	
		
		spieler1 = new JButton("1");
		spieler2 = new JButton("2");
		spieler3 = new JButton("3");
		spieler4 = new JButton("4");
		spieler5 = new JButton("5");
		spieler6 = new JButton("6");
		
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
		a=i;
		return i;
	}
	

	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
	
	
	
	

}

