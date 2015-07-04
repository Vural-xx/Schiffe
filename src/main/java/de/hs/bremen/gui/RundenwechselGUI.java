package de.hs.bremen.gui;


import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hs.bremen.controller.MainController;


public class RundenwechselGUI extends JPanel {

	private JLabel textLabel1;
	public MainController mainController;
	
	public RundenwechselGUI(MainController mainController){
		this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	private void initComponents(){
		textLabel1 = new JLabel("Spielerwechsel");
		textLabel1.setHorizontalAlignment(JLabel.CENTER);
		
		this.setLayout(new BorderLayout(25,25));
		this.add(textLabel1,BorderLayout.PAGE_START);

	}
}
