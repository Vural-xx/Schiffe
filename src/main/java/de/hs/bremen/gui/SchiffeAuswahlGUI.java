package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SchiffeAuswahlGUI extends JPanel implements DocumentListener  {
	
	private JLabel schiffeAnzahlText;
	private JLabel zerstoererText;
	private JLabel fregatteText;
	private JLabel korvetteText;
	private JLabel ubootText;
	private JLabel warnung;
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
		weiter.setEnabled(false);

		schiffeAnzahlText= new JLabel("Bitte geben Sie Anzahl der Schiffe ein:");
		
		zerstoererText= new JLabel("Zerstörer:");
		fregatteText= new JLabel("Fregatte:");
		korvetteText= new JLabel("Korvette:");
		ubootText= new JLabel("Uboot:");
		
		warnung= new JLabel("");
		zerstoererEingabe= new JTextField();
		fregatteEingabe= new JTextField();
		korvetteEingabe= new JTextField();
		ubootEingabe= new JTextField();
		
		zerstoererEingabe.getDocument().addDocumentListener(this);
		fregatteEingabe.getDocument().addDocumentListener(this);
		korvetteEingabe.getDocument().addDocumentListener(this);
		ubootEingabe.getDocument().addDocumentListener(this);
		
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
		this.add(warnung,gbc);
		this.add(fuellcontainer,gbc);
	}
	
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		pruefen();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		pruefen();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		pruefen();
		
	}
	
	public void pruefen() {
		
		int anzahl=0;
		int anzahlzerstoerer=0;
		int anzahlfregatte=0;
		int anzahlkorvette=0;
		int anzahluboot=0;
		String zerstorer;
		String fregatte;
		String korvette;
		String uboot;
		
				zerstorer = zerstoererEingabe.getText();
				fregatte = fregatteEingabe.getText();
				korvette =korvetteEingabe.getText();
				uboot = ubootEingabe.getText();
				
				try{
					if(!zerstorer.equals("") && !fregatte.equals("") && !korvette.equals("") && !uboot.equals("")){
					anzahlzerstoerer= Integer.parseInt(zerstorer);
					anzahlfregatte= Integer.parseInt(fregatte);
					anzahlkorvette= Integer.parseInt(korvette);
					anzahluboot= Integer.parseInt(uboot);
					anzahl=anzahlzerstoerer+anzahlkorvette+anzahlfregatte+anzahluboot;
					warnung.setForeground(Color.BLACK);
					warnung.setText("Ihre Eingabe ist Korrekt");
					warnung.revalidate();
					}
				} catch (Exception e){
						System.out.println("parse falsche eingaben");
					}
	
			if(anzahlzerstoerer >=0 && anzahlfregatte >=0 &&anzahlkorvette >=0 &&anzahluboot >=0 && anzahl>=1){
				warnung.setForeground(Color.BLACK);
				warnung.setText("Ihre Eingabe ist Korrekt");
				warnung.revalidate();
				weiter.setEnabled(true);
			} else if(anzahlzerstoerer <0 || anzahlfregatte <0 || anzahlkorvette <0 || anzahluboot <0  || anzahl<1){
				weiter.setEnabled(false);
				warnung.setForeground(Color.RED);
				warnung.setText("Eingabe Falsch. Es muss mindestens 1 Schiff gesetzt werden.");
				warnung.revalidate();

	}}
	
}
