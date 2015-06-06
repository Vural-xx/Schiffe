package de.hs.bremen.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SchiffSetzenGui extends JPanel{

	private JLabel textLabel3;
	private JLabel textLabel4;
	private JPanel container5;
	private JPanel container6;
	private JLabel bildLabel;
	private JLabel bildLabel1;
	private JLabel bildLabel2;
	private JLabel bildLabel3;
	private JButton button1;
	private JButton button2;
	private JTextField textField;
	//GameFrame spiel = new GameFrame();
	
	public SchiffSetzenGui(){
		
		initComponents();

		setVisible(true);
		
}
	public void initComponents(){
		textLabel3 = new JLabel("Herzlich Willkommen bei Schiffe versenken!");
		textLabel4 = new JLabel("Spieler 1 ist dran. Bitte setze deine Schiffe");
		button1 = new JButton("Nächster Spieler");
		button2 = new JButton("Spiel beginnen");
		textField = new JTextField();	
		textField.setColumns(2);
		
		bildLabel = new JLabel();
		bildLabel.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\submarine_0.gif"));
		bildLabel1 = new JLabel();
		bildLabel1.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\destroyer_0.gif"));
		bildLabel2 = new JLabel();
		bildLabel2.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\battleship_0.gif"));
		bildLabel3 = new JLabel();
		bildLabel3.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\carrier_0.gif"));
		
		container5 = new JPanel();
		container5.setLayout(new BoxLayout(container5, BoxLayout.PAGE_AXIS));
		container5.add(bildLabel);
		container5.add(bildLabel1);
		container5.add(bildLabel2);
		container5.add(bildLabel3);
		container5.add(Box.createRigidArea(new Dimension(0, 0)));
		container5.add(Box.createVerticalGlue());
		container5.add(button1);
		container5.add(button2);
		
		container6 = new JPanel();
		container6.setLayout(new BoxLayout(container6, BoxLayout.PAGE_AXIS));
		container6.add(textLabel4);
		//container6.add(spiel.squares);
		

		
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel3, BorderLayout.NORTH);
		this.add(container6, BorderLayout.CENTER);
		this.add(container5, BorderLayout.EAST);
		
		//this.add(bildLabel, BorderLayout.SOUTH);
		
	}
	
	public void setActionListener(ActionListener l, ActionListener p){
		button1.addActionListener(l);
		button2.addActionListener(p);
		
	}
	
	
}
