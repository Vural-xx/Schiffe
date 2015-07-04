package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SpielfeldGroeßeGUI extends JPanel implements DocumentListener {

	private GridBagConstraints gbc= new GridBagConstraints();
	public JTextField spielfeldEingabe;
	private JButton weiter;
	private JLabel spielfeldText;
	private JLabel warnung;
	
	public SpielfeldGroeßeGUI(){
		initComponents();
		setVisible(true);
		
		
	}
	
	
	public void initComponents(){
		
		this.setLayout(new GridBagLayout());
		gbc.gridx=0;
		gbc.gridx=1;
		gbc.insets=new Insets(20, 0, 0, 0);
		
		spielfeldText= new JLabel("Wählen Sie ihre Spielfeldgröße:");
		spielfeldEingabe= new JTextField();
		spielfeldEingabe.getDocument().addDocumentListener(this);
		spielfeldEingabe.setPreferredSize( new Dimension( 50, 24 ) );
		warnung= new JLabel("");
		weiter= new JButton("weiter");
		weiter.setEnabled(false);
		
		this.add(spielfeldText,gbc);
		this.add(spielfeldEingabe,gbc);
		this.add(weiter,gbc);
		this.add(warnung,gbc);
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
	
	
	public void pruefen(){
		int spielfeldgroesse=0;
		String spielfeldgroesseString;
		
		spielfeldgroesseString=spielfeldEingabe.getText();
		
		try{
			if(!spielfeldgroesseString.equals("")){
				spielfeldgroesse= Integer.parseInt(spielfeldgroesseString);
			}
		} catch(Exception e) {
				System.out.println("Integer fehler");		
		}
		
		if(spielfeldgroesse >= 10 && spielfeldgroesse<= 40){
			weiter.setEnabled(true);
			warnung.setForeground(Color.BLACK);
			warnung.setText("Ihre Eingabe ist Korrekt");
			warnung.revalidate();
		} else {
			weiter.setEnabled(false);
			warnung.setForeground(Color.RED);
			warnung.setText("Die Spielfeldgröße muss mindestens 10 Felder und darf maximal 40 Felder betragen.");
			warnung.revalidate();
		}
	}
}
