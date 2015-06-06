package de.hs.bremen.gui;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TabbedPaneUI;



public class Spielfeld extends JFrame {
	private final int width = 800;
	private final int height= 600;
	
	private Toolkit toolkit;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu hilfe;
	private JMenuItem newGameItem;
	private JMenuItem loadGameItem;
	private JMenuItem saveItem;
	private JMenuItem info;
	private JMenuItem version;
	
	private JPanel container;
	private JPanel container1;
	private JPanel container2;
	
	private JButton button1;
	
	private JTextArea textArea;
	private JLabel textLabel2;
	private JTextField textField;
	private JLabel textLabel1;
	private JTabbedPane tab;
	GameFrame spiel = new GameFrame();
	
	//startseite
	private JLabel textLabel3;
	private JLabel textLabel4;
	private JPanel container5;
	private JPanel container6;
	private JLabel bildLabel;
	private JLabel bildLabel1;
	private JLabel bildLabel2;
	private JLabel bildLabel3;
	
	
	
	public Spielfeld(){
		initComponents();
		initListeners();
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
		
		/*//Design Schiffe setzen
		textLabel3 = new JLabel("Herzlich Willkommen bei Schiffe versenken!");
		textLabel4 = new JLabel("Spieler 1 ist dran. Bitte setze deine Schiffe");
		button1 = new JButton("Fertig");//doppelt
		textField = new JTextField();	//doppelt
		textField.setColumns(2);
		
		bildLabel = new JLabel();
		bildLabel.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\submarine_0.gif"));
		bildLabel1 = new JLabel();
		bildLabel1.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\destroyer_0.gif"));
		bildLabel2 = new JLabel();
		bildLabel2.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\battleship_0.gif"));
		bildLabel3 = new JLabel();
		bildLabel3.setIcon(new ImageIcon("C:\\Users\\Christin\\Desktop\\Battelship\\src\\sprites\\carrier_0.gif"));
		
		container5 = new JPanel();
		container5.setLayout(new BoxLayout(container5, BoxLayout.PAGE_AXIS));
		container5.add(bildLabel);
		container5.add(bildLabel1);
		container5.add(bildLabel2);
		container5.add(bildLabel3);
		container5.add(Box.createRigidArea(new Dimension(0, 0)));
		container5.add(Box.createVerticalGlue());
		container5.add(button1);
		
		container6 = new JPanel();
		container6.setLayout(new BoxLayout(container6, BoxLayout.PAGE_AXIS));
		container6.add(textLabel4);
		container6.add(spiel.squares);
		

		
		getContentPane().setLayout(new BorderLayout(5,5));
		this.add(textLabel3, BorderLayout.NORTH);
		this.add(container6, BorderLayout.CENTER);
		this.add(container5, BorderLayout.EAST);
		
		//this.add(bildLabel, BorderLayout.SOUTH);*/
		
		
		

		
		textLabel1 = new JLabel("Wähle das Schiff zum feuern");
		textLabel2 = new JLabel("Spieler 1");
		
		button1 = new JButton("Fertig");
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 800, 800); //x, y, width, height
		textArea.setBorder(new LineBorder(Color.BLACK));
		
		textField = new JTextField();
		textField.setColumns(2);
		
		tab = new JTabbedPane();

		JPanel container4 = new JPanel();
		tab.addTab("Spieler 2", container4);
		
		container4.add(spiel.squares);
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
		container2.add(textArea);
		
		this.setLayout(new BorderLayout(5,5));
		this.add(container,BorderLayout.PAGE_START);
		this.add(container1, BorderLayout.WEST);
		this.add(container2, BorderLayout.CENTER);
		this.add(tab, BorderLayout.EAST);
		

		


		
		// Menu bauen
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Menü");
		hilfe = new JMenu("Hilfe");
		saveItem = new JMenuItem("Speichern");
		newGameItem = new JMenuItem("Neues Spiel");
		loadGameItem = new JMenuItem("Spiel laden");
		info = new JMenuItem("Anleitung");
		version = new JMenuItem("Version");
		
		
		fileMenu.add(newGameItem);
		fileMenu.add(loadGameItem);
		fileMenu.add(saveItem);
		hilfe.add(info);
		hilfe.add(version);
		
		menuBar.add(fileMenu);
		menuBar.add(hilfe);
		setJMenuBar(menuBar);

		
	/*	textArea = new JTextArea();
		textArea.setBounds(100, 100, 200,300);
		textArea.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(textArea, BorderLayout.CENTER);*/

	}
	
	private void initListeners(){
		//Erstellt eine Aktion für Neues Spiel (action Dialog)
		this.newGameItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Test");
			}
			
		});
		
		this.info.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "<html><body>So geht das Spiel:<br>Die Schiffe haben folgende Werte:<br>Zerstörer: Länge: 5 & Feuerstärke: 3<br>Fregatte: __Länge: 4 & Feuerstärke: 2<br>Korvette:   Länge: 3 & Feuerstärke: 1<br>Uboot: Länge: 2 & Feuerstärke: 1<br>Die Schiffe schießen nur HORIZONTAL!<br>Gewonnen hat der Spieler der noch ein Schiff hat.<br>VIEL SPAß!</body></html>");
			}
			
		});
		
		this.version.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "<html><body>VFC Developers<br>Schiffe Versenken Version: 1.0<br>Build Id: 123456789</body></html>");
			}
			
		});
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
