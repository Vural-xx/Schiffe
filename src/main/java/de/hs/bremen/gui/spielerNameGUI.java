package de.hs.bremen.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class spielerNameGUI extends JPanel {
	
	private JLabel spielerNametext;
	
	
	public JPanel container1_inhalt1;
	
	
	public spielerNameGUI(){
		
		initComponents();
		setVisible(true);
		
	}
	
	
	
	public void initComponents(){
		
		this.setLayout(new GridLayout(0,1,0,0));
		JPanel container1_inhalt1 = new JPanel();
		
		spielerNametext= new JLabel("Bitte wählen Sie die Spieleranzahl aus:");
		container1_inhalt1.add(spielerNametext);
		
		this.add(container1_inhalt1);
		
		
	}
	

	public void test1(boolean visible){
		this.setVisible(visible);
		initComponents();
	}
	
	

}
