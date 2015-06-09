package de.hs.bremen.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpielfeldGroeßeGUI extends JPanel {

	private GridBagConstraints gbc= new GridBagConstraints();
	public JTextField spielfeldEingabe;
	private JButton weiter;
	private JLabel spielfeldText;
	
	
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
		spielfeldEingabe.setPreferredSize( new Dimension( 50, 24 ) );
		
		weiter= new JButton("weiter");
		
		this.add(spielfeldText,gbc);
		this.add(spielfeldEingabe,gbc);
		this.add(weiter,gbc);

		
		
	}
	
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}
	
	
	
}
