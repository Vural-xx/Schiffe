package de.hs.bremen.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpielerAuswahlGUI extends JPanel implements ActionListener {
	
	private JButton spieler1;
	private JButton spieler2;
	private JButton spieler3;
	private JButton spieler4;
	private JButton spieler5;
	private JButton spieler6;
	
	private JButton weiter;
	private int spielerAnzahl;
	
	private JPanel fuellcontainer1;
	private JPanel container1_inhalt1;
	private JPanel container1_inhalt2;
	private JPanel container1_inhalt3;
	private JPanel container1_inhalt4;
	
	private JLabel ausgewaehlteSpielerText;
	private JLabel spielerAnzahltext;
	
	FlowLayout flowlayout= new FlowLayout();
	
	public int getSpielerAnzahl() {
		return spielerAnzahl;
	}
	public void setSpielerAnzahl(int spielerAnzahl) {
		this.spielerAnzahl = spielerAnzahl;
	}
	
	
	
	// Konstruktor
	public SpielerAuswahlGUI(){
		initComponents();
		setVisible(true);		
	}
	
	
	
	
	
	
	public void initComponents(){
		
		this.setLayout(new GridLayout(0,1,0,0));
		
		
		container1_inhalt1 = new JPanel();
		container1_inhalt2 = new JPanel();
		container1_inhalt3 = new JPanel();
		container1_inhalt4= new JPanel();
		fuellcontainer1= new JPanel();
		
	
		
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
		
		ausgewaehlteSpielerText= new JLabel();
		ausgewaehlteSpielerText.setForeground(Color.red);
		
		container1_inhalt3.add(ausgewaehlteSpielerText);
		
		
		spielerAnzahltext= new JLabel("Bitte wählen Sie die Spieleranzahl aus:");
		container1_inhalt1.add(spielerAnzahltext);
		
		container1_inhalt2.setLayout(flowlayout);
		container1_inhalt2.add(spieler1);
		container1_inhalt2.add(spieler2);
		container1_inhalt2.add(spieler3);
		container1_inhalt2.add(spieler4);
		container1_inhalt2.add(spieler5);
		container1_inhalt2.add(spieler6);
		
		container1_inhalt4.add(weiter);
		
		this.add(fuellcontainer1);
		this.add(container1_inhalt1);
		this.add(container1_inhalt2);
		this.add(container1_inhalt3);
		this.add(container1_inhalt4);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// Warum ich hier keine Klammern setzen muss kp.. geht auch so. vll ja mit klammer besser
		if(e.getSource() == spieler1){
			auswahlZahlSpieler(1);
			ausgewaehlteSpielerText.setText("Es wurde 1 Spieler ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == spieler2){
			auswahlZahlSpieler(2);
			ausgewaehlteSpielerText.setText("Es wurde 2 Spieler ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == spieler3){
			auswahlZahlSpieler(3);
			ausgewaehlteSpielerText.setText("Es wurde 3 Spieler ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == spieler4){
			auswahlZahlSpieler(4);
			ausgewaehlteSpielerText.setText("Es wurde 4 Spieler ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == spieler5){
			auswahlZahlSpieler(5);
			ausgewaehlteSpielerText.setText("Es wurde 5 Spieler ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == spieler6){
			auswahlZahlSpieler(6);
			ausgewaehlteSpielerText.setText("Es wurde 6 Spieler ausgewählt");
			this.revalidate();
		}
		
	}
	
	public int auswahlZahlSpieler(int i){
		spielerAnzahl=i;
		return i;
	}
	

	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
	
	
	
	

}

