package de.hs.bremen.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.persistence.*;

/**
 * HauptFenster, diese Komponenten sind immer vorhanden
 * @author Christin
 *
 */
public class MainFrame extends javax.swing.JFrame{
	private Toolkit t;
	private int x=0,y=0,width=1072, height=600;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu hilfe;
	private JMenu ladeItem;
	private JMenuItem newGameItem;
	private JMenuItem saveItem;
	private JMenuItem info;
	private JMenuItem version;
	public JButton weiterSpielerName;
	public JButton weiterSchiffAuswahl;
	public JPanel cards;
	public SchiffeAuswahlGUI schiffeAuswahlGUI;
	public SchiffSetzenGUI schiffSetzenGui;
	private MainController mainController;
	
	
	/**
	 * Konstruktor
	 * @param mainController
	 */
	public MainFrame(MainController mainController){
		this.mainController = mainController;
		setTitle("Schiffe versenken");
		t=Toolkit.getDefaultToolkit();
		Dimension d= t.getScreenSize();
		
		x = (int)((d.getWidth() - width)/2);
		y = (int)((d.getHeight() - height)/2);
		setBounds(x,y,width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        setResizable(false);
        
        initMenu();
		
		setVisible(true);
	}

	
	/**
	 * Baut das Menü
	 */
	private void initMenu(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Menü");
		hilfe = new JMenu("Hilfe");
		saveItem = new JMenu("Speichern");
		newGameItem = new JMenuItem("Neues Spiel");
		ladeItem = new JMenu("Spiel laden");
		info = new JMenuItem("Anleitung");
		version = new JMenuItem("Version");
		
		fileMenu.add(newGameItem);
		fileMenu.add(ladeItem);
		fileMenu.add(saveItem);
		hilfe.add(info);
		hilfe.add(version);
		
		menuBar.add(fileMenu);
		menuBar.add(hilfe);
		setJMenuBar(menuBar);
		initListeners();
		
	}
	
	/**
	 * Erzeugt die Fenster beim neuen Spiel, der Anleitung und dem Infokasten
	 */
	private void initListeners(){
		this.newGameItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.setMainFrame(new MainFrame(mainController));
				mainController.startEinstellungController();

			}
			
		});
		
		this.info.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html><body>So geht das Spiel:<br>Die Schiffe haben folgende Werte:<br>Zerstörer: Länge: 5 und Feuerstärke: 3<br>Fregatte: Länge: 4 und Feuerstärke: 2<br>Korvette: Länge: 3 und Feuerstärke: 1<br>Uboot: Länge: 2 und Feuerstärke: 1<br>Die Schiffe schießen NUR Horizontal!<br>Gewonnen hat der Spieler der noch ein Schiff hat.<br>Viel Spaß bei Schiffe versenken!</body></html>");
			}
			
		});
		
		this.version.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html><body>VFC Developers<br>Schiffe Versenken Version: 1.0<br>Build Id: 123456789</body></html>");
			}
			
		});
	}
	
	/**
	 * Ermöglicht das Speichern und Laden
	 * @param speichern: zum Speichern des Spielstandes
	 * @param laden: zum Laden des Spielstandes
	 */
	public void setActionListener(ActionListener speichern, ActionListener laden){
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		JMenuItem[] ladeStand = new JMenuItem[4];
		for (int i = 0; i< listOfFiles.length; i++){
			ladeStand[i] = new JMenuItem(listOfFiles[i].getName());
			ladeItem.add(ladeStand[i]);
			ladeStand[i].addActionListener(laden);
			ladeStand[i].setName(listOfFiles[i].getName());
		}
		JMenuItem[] speicherStand = new JMenuItem[4];
		for (int i = 0; i< listOfFiles.length; i++){
			speicherStand[i] = new JMenuItem(listOfFiles[i].getName());
			speicherStand[i].addActionListener(speichern);
			speicherStand[i].setName(listOfFiles[i].getName());
			saveItem.add(speicherStand[i]);
		}
		if(listOfFiles.length< 4){
			for(int i = listOfFiles.length; i < 4; i++){
				speicherStand[i] = new JMenuItem("Leer");
				speicherStand[i].addActionListener(speichern);
				speicherStand[i].setName("Leer");
				saveItem.add(speicherStand[i]);
			}
		}
	}
}
