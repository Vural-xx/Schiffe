package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class EndeGUI extends JPanel {

	private JPanel container;

	
	private JLabel textLabel2;
	private JLabel textLabel1;
	private JLabel bildLabel;
	private String gewinner;


	
	public EndeGUI(String gewinner){
		this.gewinner = gewinner;
		initComponents();
		setVisible(true);
	}

	/*public static void main(String[] args){
		
		new EndeGUI();
	}*/
	private void initComponents(){
		textLabel1 = new JLabel("Das Spiel ist zuende");
		textLabel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		textLabel1.setHorizontalAlignment(JLabel.CENTER);
		textLabel1.setFont(new Font("Arial", Font.BOLD, 18));
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		textLabel2 = new JLabel("Herzlichen Glückwunsch! "  + gewinner +  " hat alle Schiffe versenkt!");
		textLabel2.setAlignmentX(textLabel2.CENTER_ALIGNMENT);
		textLabel2.setForeground(Color.RED);
		textLabel2.setFont(new Font("Arial", Font.BOLD, 18));
		container.add(textLabel2);
		container.add(Box.createVerticalStrut(100));
		bildLabel = new JLabel();
		bildLabel.setAlignmentX(bildLabel.CENTER_ALIGNMENT);
		bildLabel.setIcon(new ImageIcon("src/main/image/animiertes-schiff.gif"));
		container.add(bildLabel);
		this.setLayout(new BorderLayout(20,20));
		this.add(textLabel1,BorderLayout.PAGE_START);
		this.add(container, BorderLayout.CENTER);
		setVisible(true);
	}
		
}
