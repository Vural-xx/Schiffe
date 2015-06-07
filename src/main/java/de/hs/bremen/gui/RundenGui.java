package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hs.bremen.controller.MainController;



public class RundenGui extends JPanel {

	
	private JPanel container;
	private JPanel container1;
	private JPanel container2;
	private JPanel[] tablist;
	
	private JButton button1;
	
	private JTextArea textArea;
	private JLabel textLabel2;
	private JTextField textField;
	private JLabel textLabel1;
	private JTabbedPane tab;
	private SpielerfeldGUI spielerfeld;
	private MainController mainController;

	
	
	
	public RundenGui(MainController mainController){
		this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	private void initComponents(){
		textLabel1 = new JLabel("Wähle das Schiff zum feuern");
		textLabel2 = new JLabel("Spieler 1");
		
		button1 = new JButton("Fertig");
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 800, 800); //x, y, width, height
		textArea.setBorder(new LineBorder(Color.BLACK));
		
		textField = new JTextField();
		textField.setColumns(2);
		
		tab = new JTabbedPane();
		
		/*
		for(int i = 0; i<mainController.getSpieler().length; i++){
			if(!mainController.getSpieler()[i].isIstDran()){
				tablist[i] = new JPanel();
				tab.addTab("Spieler " + i, tablist[i]);
				
			}
			
		}*/
		
		JPanel container4 = new JPanel();
		tab.addTab("Spieler 2", container4);
		
		spielerfeld = new SpielerfeldGUI(300, 30);
		
		container4.add(spielerfeld);
		JPanel panel2 = new JPanel();
		tab.add("Spieler 3", panel2);
		
		
		container = new JPanel();
		container.add(textLabel1);	

		//Schiffeauswahl
		container1 = new JPanel();
		container1.setLayout(new BoxLayout(container1, BoxLayout.PAGE_AXIS));
		container1.add(textLabel1);
		container1.add(textField);
		container1.add(Box.createRigidArea(new Dimension(0, 0)));
		container1.add(Box.createVerticalGlue());
		container1.add(button1);
		
		//Spielfelder Spieler & Gegner
		container2 = new JPanel();
		container2.setLayout(new BoxLayout(container2, BoxLayout.PAGE_AXIS));
		container2.add(tab);
		container2.add(textLabel2);
		container2.add(spielerfeld);
		
		this.setLayout(new BorderLayout(5,5));
		this.add(container,BorderLayout.PAGE_START);
		this.add(container1, BorderLayout.WEST);
		this.add(container2, BorderLayout.CENTER);
		this.add(tab, BorderLayout.EAST);
		


	}
	
	public void setActionListener(ActionListener l){
		button1.addActionListener(l);
		
	}
		
}
