package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame{
	
	
	
	private Toolkit t;
	private int x=0,y=0,width=1280, height=500;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu hilfe;
	private JMenuItem newGameItem;
	private JMenuItem loadGameItem;
	private JMenuItem saveItem;
	private JMenuItem info;
	private JMenuItem version;
	public JButton weiterSpielerName;
	public JButton weiterSchiffAuswahl;
	public JPanel cards;

	public SpielerAuswahlGUI spielerAuswahlGUI;
	public spielerNameGUI spielerNameGUI;
	public schiffeAuswahlGUI schiffeAuswahlGUI;
	public SchiffSetzenGui schiffSetzenGui;
	
	
	public MainFrame(){
		
		
		
		setTitle("Schiffe versenken");
		t=Toolkit.getDefaultToolkit();
		Dimension d= t.getScreenSize();
		
		x = (int)((d.getWidth() - width)/2);
		y = (int)((d.getHeight() - height)/2);
		setBounds(x,y,width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Fixed JFrame
        setResizable(false);
        
        initMenu();
		//initComponents();
		
		setVisible(true);
	}
	
	public void initComponents(){
		
		schiffSetzenGui = new SchiffSetzenGui();
		spielerAuswahlGUI= new SpielerAuswahlGUI();
		spielerNameGUI= new spielerNameGUI();
		schiffeAuswahlGUI= new schiffeAuswahlGUI();
		
		
		getContentPane().setLayout(new GridBagLayout());
		JPanel container= new JPanel();
		cards= new JPanel();
		container.setLayout(new CardLayout());
		
		weiterSpielerName= new JButton("weiter 1!");
		weiterSchiffAuswahl= new JButton("weiter 2!");

		container.add(spielerAuswahlGUI, "1");
		container.add(spielerNameGUI, "2");
		container.add(schiffeAuswahlGUI, "3");
		
		weiterSpielerName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int zahlSpieler = spielerAuswahlGUI.getA();
			spielerNameGUI.createNameFenster(zahlSpieler);
			spielerAuswahlGUI.setVisible(false);
			spielerNameGUI.setVisible(true);
				
			}
		});
		
		weiterSchiffAuswahl.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				spielerNameGUI.setVisible(false);
				schiffeAuswahlGUI.setVisible(true);
					
				}
			});
			

		add(weiterSpielerName);
		add(weiterSchiffAuswahl);
		add(container );
		
		
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

		
	}
	
	private void initMenu(){
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
		initListeners();
		
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
		new MainFrame();
	}
	

}
