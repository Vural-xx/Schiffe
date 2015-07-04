package de.hs.bremen.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class SpielerNameGUI extends JPanel implements DocumentListener  {
	
	public JLabel spielerNametext;
	public JTextField[] spielerEingabe;
	private JButton weiter;
	private GridBagConstraints gbc= new GridBagConstraints();
	private int spieler;
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
		
	}
	
	
	public void createNameFenster(int spieleranzahl){
		spielerEingabe = new JTextField[spieleranzahl];
		for(int i= 0; i <spieleranzahl; i++){
			spielerEingabe[i]= new JTextField();
			spielerEingabe[i].getDocument().addDocumentListener(this);
			spielerEingabe[i].setName("spieler"+ (i+1));
			spielerEingabe[i].setPreferredSize( new Dimension( 150, 24 ) );
			
			spielerNametext= new JLabel("Spielername " + (i+1)+": ");
			spieler= spieleranzahl;
			this.add(spielerNametext,gbc);
			this.add(spielerEingabe[i],gbc);
			
		}
		this.add(weiter,gbc);
		
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
		int counter=0;
		weiter.setEnabled(false);
		for(int i=0; i< spieler; i++){
			for(int j=0; j< spieler; j++){
			if(i!=j && !spielerEingabe[i].getText().equals("") && !spielerEingabe[i].getText().equals(spielerEingabe[j].getText())){
				counter++;
				
			}
		}}
		
		if((counter/spieler+1)==spieler){
			weiter.setEnabled(true);
		}
	}


}