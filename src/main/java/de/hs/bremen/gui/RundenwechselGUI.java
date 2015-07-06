package de.hs.bremen.gui;


import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hs.bremen.controller.MainController;


/**
 * Bildschirm beim Spielerwechel in der Runde
 * @author Christin
 *
 */
public class RundenwechselGUI extends JPanel {

	private JLabel textLabel1;
	public MainController mainController;
	
	/**
	 * Konstruktor
	 * @param mainController
	 */
	public RundenwechselGUI(MainController mainController){
		this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	/**
	 * Erzeugt die GUI zum Spielerwechseln in der Runde
	 */
	private void initComponents(){
		textLabel1 = new JLabel("Spielerwechsel");
		textLabel1.setHorizontalAlignment(JLabel.CENTER);
		
		this.setLayout(new BorderLayout(25,25));
		this.add(textLabel1,BorderLayout.PAGE_START);

	}
}
