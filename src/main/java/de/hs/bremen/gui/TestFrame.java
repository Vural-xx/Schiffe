package de.hs.bremen.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TestFrame extends JFrame {
	
	private final int width = 800;
	private final int height= 600;
	
	private Toolkit toolkit;

	/**
	 * 
	 */
	private static final long serialVersionUID = -141405179048039263L;
	
	public  TestFrame() {
		initComponents();
		
	}

	private void initComponents() {
		int x;
		int y;
		setTitle("Schiffe versenken");
		
		toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		// Mitte des Bildschirms errechnen
		x =(int) ((d.getWidth() - width)/2);
		y =(int) ((d.getHeight() - height)/2);
		
		// Größe und Position des Fensters
		setBounds(x, y, width, height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

}
