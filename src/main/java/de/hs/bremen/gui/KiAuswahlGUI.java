package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KiAuswahlGUI extends JPanel implements ActionListener {
	
	private JButton computer1;
	private JButton computer2;
	private JButton computer3;
	private JButton computer4;
	private JButton computer5;
	private JButton computer6;
	
	private JButton weiter;
	private int spielerAnzahl;
	
	private JPanel fuellcontainer1;
	private JPanel container1_inhalt1;
	private JPanel container1_inhalt2;
	private JPanel container1_inhalt3;
	private JPanel container1_inhalt4;
	
	private JLabel ausgewaehlteGegnerText;
	private JLabel spielerAnzahltext;
	
	FlowLayout flowlayout= new FlowLayout();
	
	public int getSpielerAnzahl() {
		return spielerAnzahl;
	}
	public void setSpielerAnzahl(int spielerAnzahl) {
		this.spielerAnzahl = spielerAnzahl;
	}
	
	
	
	// Konstruktor
	public KiAuswahlGUI(){
		initComponents();
		setVisible(true);		
	}
	
	
	
	
	
	
	public void initComponents(){
		
		this.setLayout(new GridLayout(0,1,0,0));
		
		
		fuellcontainer1 = new JPanel();
		container1_inhalt2 = new JPanel();
		container1_inhalt3 = new JPanel();
		container1_inhalt4= new JPanel();
		container1_inhalt1= new JPanel();
		
	
		
		computer1 = new JButton("0");
		computer2 = new JButton("1");
		computer3 = new JButton("2");
		computer4 = new JButton("3");
		computer5 = new JButton("4");
		computer6 = new JButton("5");
		
		weiter = new JButton("weiter");
		
		computer1.addActionListener(this);
		computer2.addActionListener(this);
		computer3.addActionListener(this);
		computer4.addActionListener(this);
		computer5.addActionListener(this);
		computer6.addActionListener(this);
		
		spielerAnzahltext= new JLabel("Bitte wählen Sie die bitte wählen Sie die Computergegneranzahl aus:");
		container1_inhalt1.add(spielerAnzahltext);
		
		ausgewaehlteGegnerText= new JLabel();
		ausgewaehlteGegnerText.setForeground(Color.red);
		
		container1_inhalt3.add(ausgewaehlteGegnerText);
		
		

		
		container1_inhalt2.setLayout(flowlayout);
		container1_inhalt2.add(computer1);
		container1_inhalt2.add(computer2);
		container1_inhalt2.add(computer3);
		container1_inhalt2.add(computer4);
		container1_inhalt2.add(computer5);
		container1_inhalt2.add(computer6);
		
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
		if(e.getSource() == computer1){
			auswahlZahlSpieler(0);
			ausgewaehlteGegnerText.setText("Es wurde 0 KI-Gegner ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == computer2){
			auswahlZahlSpieler(1);
			ausgewaehlteGegnerText.setText("Es wurde 1 KI-Gegner ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == computer3){
			auswahlZahlSpieler(2);
			ausgewaehlteGegnerText.setText("Es wurde 2 KI-Gegner ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == computer4){
			auswahlZahlSpieler(3);
			ausgewaehlteGegnerText.setText("Es wurde 3 KI-Gegner ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == computer5){
			auswahlZahlSpieler(4);
			ausgewaehlteGegnerText.setText("Es wurde 4 KI-Gegner ausgewählt");
			this.revalidate();
		}
		if(e.getSource() == computer6){
			auswahlZahlSpieler(5);
			ausgewaehlteGegnerText.setText("Es wurde 5 KI-Gegner ausgewählt");
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


