package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Spielfeldmodus;

public class EndeGUI extends JFrame {

	private JPanel container;

	
	private JLabel textLabel2;
	private JLabel textLabel1;
	private JLabel bildLabel;
	private JButton button;

	private MainController mainController;

	
	public EndeGUI(){
		//this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	public static void main(String[] args){
		
		new EndeGUI();
	}
	private void initComponents(){
		
		
		textLabel1 = new JLabel("Das Spiel ist zuende");
		textLabel1.setHorizontalAlignment(JLabel.CENTER);
		textLabel1.setFont(new Font("Arial", Font.BOLD, 18));
		container = new JPanel();
		container.add(textLabel1);
		textLabel2 = new JLabel("Herzlichen Glückwunsch, Spieler: hat gewonnen");
		textLabel2.setForeground(Color.RED);
		bildLabel = new JLabel();
		bildLabel.setIcon(new ImageIcon("src/main/image/animiertes-schiff.gif"));
		container.add(bildLabel);
		button = new JButton("Neues Spiel starten");
		container.add(button);
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel1,BorderLayout.PAGE_START);
		this.add(container, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setActionListener(ActionListener EndeListener){
		button.addActionListener(EndeListener);
	}
		
}
