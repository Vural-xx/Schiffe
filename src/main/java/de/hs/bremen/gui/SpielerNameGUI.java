package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpielerNameGUI extends JPanel {
	
	public JLabel spielerNametext;
	
	
	public JPanel container1;
	public JTextField spielerEingabe;
	private JButton jButton;

	



	public JLabel spielernameAnfangsText;
	
	FlowLayout flowLayout;
	
	
	public SpielerNameGUI(){
		
		initComponents();
		
		setVisible(true);
		
	}
	
	
	
	public void initComponents(){
		
		this.setLayout(new BorderLayout(0, 100));
		flowLayout = new FlowLayout();
		container1 = new JPanel();
		spielernameAnfangsText= new JLabel("Bitte geben Sie ihren Spielernamen ein:");
		container1.setLayout(new GridLayout(0,1));
	
		

		
		this.add(spielernameAnfangsText, BorderLayout.PAGE_START);
		this.add(container1);

		jButton = new JButton("Fertig");
		this.add(jButton);
		
		
	}
	

	public void test1(boolean visible){
		this.setVisible(visible);
	}
	
	public void createNameFenster(int spieleranzahl){
		for(int i= 1; i <=spieleranzahl; i++){
			
			spielerEingabe= new JTextField();
			spielerEingabe.setPreferredSize( new Dimension( 150, 24 ) );
			
			spielerNametext= new JLabel("Spielername " + i+": ");
		
			container1.add(spielerNametext);
			container1.add(spielerEingabe);
			
			
			
		}
		
	}
	
	public void setActionListener(ActionListener l){
		jButton.addActionListener(l);
	}



	

}
