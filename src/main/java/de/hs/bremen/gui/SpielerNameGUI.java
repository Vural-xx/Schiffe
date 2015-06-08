package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpielerNameGUI extends JPanel {
	
	public JLabel spielerNametext;
	
	
	public JPanel container1;
	public JTextField spielerEingabe;
	public JButton weiter;
	public GridBagConstraints gbc= new GridBagConstraints();

	



	public JLabel spielernameAnfangsText;
	
	FlowLayout flowLayout;
	
	
	public SpielerNameGUI(){
		
		initComponents();
		createNameFenster();
		setVisible(true);
		
	}
	
	
	
	public void initComponents(){
		
		this.setLayout(new GridBagLayout());
		gbc.gridx=0;
		gbc.gridx=1;
		gbc.insets=new Insets(20, 0, 0, 0);
		
		weiter= new JButton("weiter");

		
		
	}
	
	
	public void createNameFenster(){
		int spieleranzahl=3;
		for(int i= 1; i <=spieleranzahl; i++){
			
			spielerEingabe= new JTextField();
			spielerEingabe.setPreferredSize( new Dimension( 150, 24 ) );
			
			spielerNametext= new JLabel("Spielername " + i+": ");
		
			this.add(spielerNametext,gbc);
			this.add(spielerEingabe,gbc);
			
			
			
		}
		this.add(weiter,gbc);
		
	}
	
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}



	

}
