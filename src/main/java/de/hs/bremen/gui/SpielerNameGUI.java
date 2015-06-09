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
	public JTextField[] spielerEingabe;;
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
		spielerEingabe = new JTextField[spieleranzahl];
		for(int i= 0; i <spieleranzahl; i++){
			spielerEingabe[i]= new JTextField();
			spielerEingabe[i].setName("spieler"+ (i+1));
			spielerEingabe[i].setPreferredSize( new Dimension( 150, 24 ) );
			
			spielerNametext= new JLabel("Spielername " + (i+1)+": ");
		
			this.add(spielerNametext,gbc);
			this.add(spielerEingabe[i],gbc);
			
			
			
		}
		this.add(weiter,gbc);
		
	}
	
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}



	

}
