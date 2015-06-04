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
		
		this.initComponents();
		setVisible(true);

		
	}
	
	private void initComponents(){
		this.getContentPane().setLayout(new GridLayout(0,1,0,0));
		
		FlowLayout flowlayout = new FlowLayout();
		
		this.container1=  new JPanel();
		this.container2= new JPanel();
		this.container3= new JPanel();
		this.container1_inhalt1 = new JPanel();
		this.container1_inhalt2=  new JPanel();
		this.container2_inhalt1= new JPanel();
		this.container2_inhalt2= new JPanel();
		this.container3_inhalt1= new JPanel();
		this.container3_inhalt2= new JPanel();

		
		this.container1_inhalt2.setLayout(flowlayout);
		
		this.spielerAnzahltext= new JLabel("Bitte wählen Sie die Spieleranzahl aus:");
		this.container1_inhalt1.add(this.spielerAnzahltext);

		
		
		this.spieler1 = new JButton("1");
		this.spieler2 = new JButton("2");
		this.spieler3 = new JButton("3");
		this.spieler4 = new JButton("4");
		this.spieler5 = new JButton("5");
		this.spieler6 = new JButton("6");
		
		
		this.container1_inhalt2.add(this.spieler1);
		this.container1_inhalt2.add(this.spieler2);
		this.container1_inhalt2.add(this.spieler3);
		this.container1_inhalt2.add(this.spieler4);
		this.container1_inhalt2.add(this.spieler5);
		this.container1_inhalt2.add(this.spieler6);
		
		this.container1.add(this.container1_inhalt1, BorderLayout.CENTER);
		this.container1.add(this.container1_inhalt2, BorderLayout.PAGE_END);
		
		
		this.container2_inhalt2.setLayout(flowlayout);
		
		
		this.zerstoererButton = new JButton("Zerstörer");
		this.fregatteButton = new JButton("Fregatte");
		this.korvetteButton = new JButton("Korvette");
		this.ubootButton= new JButton("Uboot");
				
		this.schiffAuswahltext= new JLabel("Bitte wählen Sie ein Schiff aus:");
		this.container2_inhalt1.add(this.schiffAuswahltext);
		
		this.container2_inhalt2.add(this.zerstoererButton);
		this.container2_inhalt2.add(this.fregatteButton);
		this.container2_inhalt2.add(this.korvetteButton);
		this.container2_inhalt2.add(this.ubootButton);
		
		
		
		
		this.container2.add(this.container2_inhalt1, BorderLayout.PAGE_START);
		this.container2.add(this.container2_inhalt2, BorderLayout.CENTER);
		
		
		this.schiffAnzahltext= new JLabel("Bitte geben Sie die Anzahl an Schiffen ihres Schifftyps ein:");
		this.container3_inhalt1.add(this.schiffAnzahltext);
		
		this.schiffAnzahleingabe= new JTextField("           ");
		this.container3_inhalt2.add(schiffAnzahleingabe);
		
		this.container3.setLayout(new BorderLayout());
		
		this.container3.add(this.container3_inhalt1, BorderLayout.PAGE_START);
		this.container3.add(this.container3_inhalt2, BorderLayout.CENTER);
		
		
		
		this.getContentPane().add(this.container1);
		this.getContentPane().add(this.container2);
		this.getContentPane().add(this.container3);
		
	
		
		
	}
	
	
	

	public static void main(String[] args){
		new SpielEinstellungen();
	}
	
	
	
	
}
