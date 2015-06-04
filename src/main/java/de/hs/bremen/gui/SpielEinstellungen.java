package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SpielEinstellungen extends javax.swing.JFrame {
	private static final long serialVersionUID = -3323743819278321734L;
	
	private Toolkit t;
	private int x=0,y=0,width=600, height=400;
	
	//Buttons
	
	private JButton spieler1;
	private JButton spieler2;
	private JButton spieler3;
	private JButton spieler4;
	private JButton spieler5;
	private JButton spieler6;
	
	private JButton zerstoererButton;
	private JButton fregatteButton;
	private JButton korvetteButton;
	private JButton ubootButton;
	
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	
	
	private JPanel container1_inhalt1;
	private JPanel container1_inhalt2;
	
	private JPanel container2_inhalt1;
	private JPanel container2_inhalt2;
	
	private JPanel container3_inhalt1;
	private JPanel container3_inhalt2;
	
	
	
	private JLabel spielerAnzahltext;
	private JLabel schiffAuswahltext;
	private JLabel schiffAnzahltext;
	private JTextField schiffAnzahleingabe; 
	
	
	
	
	public SpielEinstellungen(){
		
		setTitle("Spieleinstellungen");
		t=Toolkit.getDefaultToolkit();
		
		Dimension d= t.getScreenSize();
		
		x = (int)((d.getWidth() - width)/2);
		y = (int)((d.getHeight() - height)/2);
		setBounds(x,y,width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponents();
		setVisible(true);

		
	}
	
	private void initComponents(){
		getContentPane().setLayout(new GridLayout(0,1,0,0));
		
		FlowLayout flowlayout = new FlowLayout();
		
		container1=  new JPanel();
		container2= new JPanel();
		container3= new JPanel();
		container1_inhalt1 = new JPanel();
		container1_inhalt2=  new JPanel();
		container2_inhalt1= new JPanel();
		container2_inhalt2= new JPanel();
		container3_inhalt1= new JPanel();
		container3_inhalt2= new JPanel();

		
		container1_inhalt2.setLayout(flowlayout);
		
		spielerAnzahltext= new JLabel("Bitte wählen Sie die Spieleranzahl aus:");
		container1_inhalt1.add(spielerAnzahltext);

		
		
		spieler1 = new JButton("1");
		spieler2 = new JButton("2");
		spieler3 = new JButton("3");
		spieler4 = new JButton("4");
		spieler5 = new JButton("5");
		spieler6 = new JButton("6");
		
		
		container1_inhalt2.add(spieler1);
		container1_inhalt2.add(spieler2);
		container1_inhalt2.add(spieler3);
		container1_inhalt2.add(spieler4);
		container1_inhalt2.add(spieler5);
		container1_inhalt2.add(spieler6);
		
		container1.add(container1_inhalt1, BorderLayout.CENTER);
		container1.add(container1_inhalt2, BorderLayout.PAGE_END);
		
		
		container2_inhalt2.setLayout(flowlayout);
		
		
		zerstoererButton = new JButton("Zerstörer");
		fregatteButton = new JButton("Fregatte");
		korvetteButton = new JButton("Korvette");
		ubootButton= new JButton("Uboot");
				
		schiffAuswahltext= new JLabel("Bitte wählen Sie ein Schiff aus:");
		container2_inhalt1.add(schiffAuswahltext);
		
		container2_inhalt2.add(zerstoererButton);
		container2_inhalt2.add(fregatteButton);
		container2_inhalt2.add(korvetteButton);
		container2_inhalt2.add(ubootButton);
		
		
		
		
		container2.add(container2_inhalt1, BorderLayout.PAGE_START);
		container2.add(container2_inhalt2, BorderLayout.CENTER);
		
		
		schiffAnzahltext= new JLabel("Bitte geben Sie die Anzahl an Schiffen ihres Schifftyps ein:");
		container3_inhalt1.add(schiffAnzahltext);
		
		schiffAnzahleingabe= new JTextField("           ");
		container3_inhalt2.add(schiffAnzahleingabe);
		
		container3.setLayout(new BorderLayout());
		
		container3.add(container3_inhalt1, BorderLayout.PAGE_START);
		container3.add(container3_inhalt2, BorderLayout.CENTER);
		
		
		
		getContentPane().add(container1);
		getContentPane().add(container2);
		getContentPane().add(container3);
		
	
		
		
	}
	
	
	

	public static void main(String[] args){
		new SpielEinstellungen();
	}
	
	
	
	
}
