package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpielerNameGUI extends JPanel implements ActionListener {
	
	public JLabel spielerNametext;
	
	
	private JPanel container1;
	public JTextField[] spielerEingabe;
	private JButton weiter;
	private JButton pruefen;
	private GridBagConstraints gbc= new GridBagConstraints();
	
	private int spieler;
	



	private JLabel spielernameAnfangsText;
	
	FlowLayout flowLayout;
	
	
	public SpielerNameGUI(){
		
		initComponents();
		setVisible(true);
		
	}
	
	
	
	public void initComponents(){
		
		this.setLayout(new GridBagLayout());
		gbc.gridx=1;
		gbc.insets=new Insets(10, 0, 0, 0);
		
		weiter= new JButton("weiter");
		weiter.setEnabled(false);
		
		pruefen= new JButton("Eingabe prüfen!");
		pruefen.addActionListener(this);
		

		
		
	}
	
	
	public void createNameFenster(int spieleranzahl){
		spielerEingabe = new JTextField[spieleranzahl];
		for(int i= 0; i <spieleranzahl; i++){
			spielerEingabe[i]= new JTextField();
			spielerEingabe[i].setName("spieler"+ (i+1));
			spielerEingabe[i].setPreferredSize( new Dimension( 150, 24 ) );
			
			spielerNametext= new JLabel("Spielername " + (i+1)+": ");
			spieler= spieleranzahl;
			this.add(spielerNametext,gbc);
			this.add(spielerEingabe[i],gbc);
			
			
			
		}
		this.add(pruefen,gbc);
		this.add(weiter,gbc);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i< spieler; i++){
			if(spielerEingabe[i].getText().equals("")){
				weiter.setEnabled(false);
			} else {
				weiter.setEnabled(true);
			}
		}
	}
	
	public void setActionListener(ActionListener l){
		weiter.addActionListener(l);
		
	}



	

}
