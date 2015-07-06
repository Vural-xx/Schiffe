package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Bildschirm zur Auswahl der KI
 * @author Christin
 *
 */
public class KiAuswahlGUI extends JPanel implements ActionListener {
	
	private JButton computer1;
	private JButton computer2;
	private JButton computer3;
	private JButton computer4;
	private JButton computer5;
	private JButton computer6;
	
	private JButton weiter;
	private int kiAnzahl;
	
	private JPanel fuellcontainer1;
	private JPanel container1_inhalt1;
	private JPanel container1_inhalt2;
	private JPanel container1_inhalt3;
	private JPanel container1_inhalt4;
	
	private JLabel ausgewaehlteGegnerText;
	private JLabel spielerAnzahltext;
	
	FlowLayout flowlayout= new FlowLayout();
	
	/**
	 * Getter der Anzahl der Spieler
	 * @return
	 */
	public int getSpielerAnzahl() {
		return kiAnzahl;
	}
	/**
	 * Setter der Anzahl der Spieler
	 * @param spielerAnzahl: Wie viele KI Gegner gesetzt werden
	 */
	public void setSpielerAnzahl(int spielerAnzahl) {
		this.kiAnzahl = spielerAnzahl;
	}
	
	/**
	 * Konstruktor
	 */
	public KiAuswahlGUI(){
		initComponents();
		setVisible(true);		
	}
	
	/**
	 * Erzeugt die GUI zur Auswahl der KI Gegner
	 */
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
		weiter.setEnabled(false);
		
		computer1.addActionListener(this);
		computer2.addActionListener(this);
		computer3.addActionListener(this);
		computer4.addActionListener(this);
		computer5.addActionListener(this);
		computer6.addActionListener(this);
		
		spielerAnzahltext= new JLabel("Bitte wählen Sie die Computergegneranzahl aus:");
		container1_inhalt1.add(spielerAnzahltext);
		
		ausgewaehlteGegnerText= new JLabel();
		ausgewaehlteGegnerText.setForeground(Color.blue);
		
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
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == computer1){
			auswahlZahlSpieler(0);
			ausgewaehlteGegnerText.setText("Es wurde 0 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
		if(e.getSource() == computer2){
			auswahlZahlSpieler(1);
			ausgewaehlteGegnerText.setText("Es wurde 1 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
		if(e.getSource() == computer3){
			auswahlZahlSpieler(2);
			ausgewaehlteGegnerText.setText("Es wurde 2 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
		if(e.getSource() == computer4){
			auswahlZahlSpieler(3);
			ausgewaehlteGegnerText.setText("Es wurde 3 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
		if(e.getSource() == computer5){
			auswahlZahlSpieler(4);
			ausgewaehlteGegnerText.setText("Es wurde 4 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
		if(e.getSource() == computer6){
			auswahlZahlSpieler(5);
			ausgewaehlteGegnerText.setText("Es wurde 5 KI-Gegner ausgewählt");
			ausgewaehlteGegnerText.revalidate();
			weiter.setEnabled(true);
			weiter.revalidate();
		}
	}
	
	/**
	 * Anzahl der KI Gegner
	 * @param i: Anzahl der KI Gegner
	 * @return
	 */
	public int auswahlZahlSpieler(int i){
		kiAnzahl=i;
		return i;
	}
	

	/**
	 * Erzeugt den Wechsel, wenn Anzahl der KI Gegner gewählt wurde
	 * @param l
	 */
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
	
	/**
	 * Setzt bei einem Menschspieler 0 KI Gegner auf unsichtbar, sodass nicht allein gespielt werden kann
	 */
	public void buttonBeiEinemSpieler(){
		computer1.setEnabled(false);
	}	

}


