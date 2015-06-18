package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Spielfeldmodus;

public class RundenGUI extends JPanel {

	private JPanel container;
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	
	private JButton button1;
	
	private JLabel textLabel2;
	private JLabel textLabel1;
	private JTabbedPane tab;
	private SpielerfeldGUI spielerfeld;
	private MainController mainController;
	
	public RundenGUI(MainController mainController){
		this.mainController = mainController;
		initComponents();
		setVisible(true);
	}

	private void initComponents(){
		textLabel1 = new JLabel("Wähle das Schiff zum feuern");
		textLabel2 = new JLabel(mainController.getSpieler()[0].getName());
		button1 = new JButton("Fertig");
		spielerfeld = new SpielerfeldGUI(375, 15,mainController, Spielfeldmodus.SPIELER);
		spielerfeld.drawSpielfeld();
		
		tab = new JTabbedPane();
		SpielerfeldGUI spielerfeld2[] = new SpielerfeldGUI[mainController.getSpieler().length];
		for(int i = 0; i< mainController.getSpieler().length; i++){
			if(!mainController.getSpieler()[i].isIstDran()){
				spielerfeld2[i] = new SpielerfeldGUI(375, 15,mainController,Spielfeldmodus.GEGNER);
				spielerfeld2[i].drawGegnerSpielfeld();
				tab.addTab(mainController.getSpieler()[i].getName(), spielerfeld2[i]);
			}
		}	
		container = new JPanel();
		container.add(textLabel1);	

		//Schiffeauswahl
		container1 = new JPanel();
		container1.setLayout(new GridLayout(14,1,10,10));
		container1.setPreferredSize(new Dimension(100, 15));
		JButton zerstoerer[] = new JButton[1/*schiffeAuswahl().zerstoererEingabe.getText()*/];
		for(int i = 0; i!=1 /*schiffeAuswahl().zerstoererEingabe.getText()*/; i++){
			zerstoerer[i] = new JButton("Zerstörer");
			container1.add(zerstoerer[i]);
		}
		JButton fregatte[] = new JButton[1/*schiffeAuswahl().fregatteEingabe.getText()*/];
		for(int i = 0; i!=1 /*schiffeAuswahl().fregatteEingabe.getText()*/; i++){
			fregatte[i] = new JButton("Fregatte ");
			container1.add(fregatte[i]);
		}
		JButton korvette[] = new JButton[1/*schiffeAuswahl().korvetteEingabe.getText()*/];
		for(int i = 0; i!=1 /*schiffeAuswahl().korvetteEingabe.getText()*/; i++){
			korvette[i] = new JButton("Korvette");
			container1.add(korvette[i]);
		}
		JButton uboot[] = new JButton[1/*schiffeAuswahl().ubootEingabe.getText()*/];
		for(int i = 0; i!=1 /*schiffeAuswahl().ubootEingabe.getText()*/; i++){
			uboot[i] = new JButton("UBoot");
			container1.add(uboot[i]);
		}
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(Box.createVerticalGlue());
		container1.add(button1);
		
		//Spielfelder Spieler & Gegner
		container2 = new JPanel();
		container2.setLayout(new BoxLayout(container2, BoxLayout.PAGE_AXIS));
		container2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));//(top, left, bottom, right)
		container2.add(textLabel2);
		container2.add(spielerfeld);

		
		container3 = new JPanel();
		container3.setLayout(new GridLayout(1,2));
		container3.add(container2);
		container3.add(tab);
		
		this.setLayout(new BorderLayout(5,5));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));//(top, left, bottom, right)
		this.add(container,BorderLayout.PAGE_START);
		this.add(container1, BorderLayout.WEST);
		this.add(container3, BorderLayout.CENTER);
	}
	
	public void setActionListener(ActionListener l){
		button1.addActionListener(l);
		
	}
		
}
