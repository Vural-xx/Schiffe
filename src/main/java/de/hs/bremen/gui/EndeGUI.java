package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EndeGUI extends JPanel {

	private JPanel container;

	
	private JLabel textLabel2;
	private JLabel textLabel1;
	private JLabel bildLabel;


	
	public EndeGUI(){
		initComponents();
		setVisible(true);
	}

	/*public static void main(String[] args){
		
		new EndeGUI();
	}*/
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
		this.setLayout(new BorderLayout(5,5));
		this.add(textLabel1,BorderLayout.PAGE_START);
		this.add(container, BorderLayout.CENTER);
		setVisible(true);
	}
		
}
