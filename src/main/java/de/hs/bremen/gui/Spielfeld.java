package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;



public class Spielfeld extends JFrame {
	private final int width = 800;
	private final int height= 600;
	
	private Toolkit toolkit;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newGameItem;
	private JMenuItem loadGameItem;
	private JPanel container;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	
	private JTextArea textArea;
	
	
	
	public Spielfeld(){
		initComponents();
	}

	private void initComponents(){
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
		
		button1 = new JButton("Button1");
		button2 = new JButton("Button 2");
		button3 = new JButton("Button");
		
		container = new JPanel();
		BoxLayout box = new BoxLayout(container, BoxLayout.Y_AXIS);
		
	
		getContentPane().setLayout(new BorderLayout(5,5));
		container.add(button1);
		container.add(button2);
		container.add(button3);
		getContentPane().add(container, BorderLayout.LINE_END);
		
		// Menu bauen
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newGameItem = new JMenuItem("Neues Spiel");
		loadGameItem = new JMenuItem("Spiel laden");
		
		fileMenu.add(newGameItem);
		fileMenu.add(loadGameItem);
		
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		textArea = new JTextArea();
		textArea.setBounds(100, 100, 200,300);
		textArea.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(textArea, BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Spielfeld().setVisible(true);
			}
		});
	}
}
