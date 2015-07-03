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
import de.hs.bremen.gui.utility.CustomOptionPane;
import de.hs.bremen.model.Spiel;
import de.hs.bremen.persistence.*;
import de.hs.bremen.abstracts.AbstractController;;

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
		
		setVisible(true);
	}
	
	
		

		
	
	private void initMenu(){

		// Menu bauen
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Menü");
		hilfe = new JMenu("Hilfe");
		saveItem = new JMenu("Speichern");
		newGameItem = new JMenuItem("Neues Spiel");
		ladeItem = new JMenu("Spiel laden");
		info = new JMenuItem("Anleitung");
		version = new JMenuItem("Version");
		JMenuItem[] ladeStand = new JMenuItem[4];
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i< listOfFiles.length; i++){
			ladeStand[i] = new JMenuItem(listOfFiles[i].getName());
			ladeItem.add(ladeStand[i]);
			ladeStand[i].addActionListener(new LadeItemKlick());
			ladeStand[i].setName(listOfFiles[i].getName());
		}
		JMenuItem[] speicherStand = new JMenuItem[4];
		for (int i = 0; i< listOfFiles.length; i++){
			
			speicherStand[i] = new JMenuItem(listOfFiles[i].getName());
			speicherStand[i].addActionListener(new SpeichernItemKlick());
			speicherStand[i].setName(listOfFiles[i].getName());
			saveItem.add(speicherStand[i]);
		}
		if(listOfFiles.length< 4){
			for(int i = listOfFiles.length; i < 4; i++){
				speicherStand[i] = new JMenuItem("Leer");
				speicherStand[i].addActionListener(new SpeichernItemKlick());
				speicherStand[i].setName("Leer");
				saveItem.add(speicherStand[i]);
			}
		}
		
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
	
	class LadeItemKlick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem jMenuItem = (JMenuItem) e.getSource();
			System.out.println(jMenuItem.getName());
		}
		
	}
	
	class SpeichernItemKlick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem jMenuItem = (JMenuItem) e.getSource();
			JFrame frame = new JFrame("Spiel speichern");
		    String speicherName = CustomOptionPane.showInputDialog(frame, "Wie wollen Sie den Spielstand benennen?");
		    //System.exit(0);
			System.out.println(jMenuItem.getName());
			System.out.println(speicherName);
		}
		
	}
	
	private void initListeners(){
		//Erstellt eine Aktion für Neues Spiel (action Dialog)
		this.newGameItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Test");
				/*if(!PersistenceManager.spm.spielstaendeVorhanden()){
				}else{
					Spiel sp = PersistenceManager.spm.ladeMenu();
					this.runde = sp.getRunde()-1;
					setSpieler(sp.getSpieler());
				}*/
			}
			
		});
		
		this.ladeItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					
				
			}
			
		});
		
		this.saveItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("Spiel speichern");
			    String name = JOptionPane.showInputDialog(frame, "Wählen Sie 1-4 zur Speicherung des Spielstandes");
			    System.exit(0);
				SpielstandManager spielstandManager = new SpielstandManager();
				//spielstandManager.speicherMenu();
				System.exit(0);
			}
			
		});
		
		this.info.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "<html><body>So geht das Spiel:<br>Die Schiffe haben folgende Werte:<br>Zerstörer: Länge: 5 und Feuerstärke: 3<br>Fregatte: Länge: 4 und Feuerstärke: 2<br>Korvette: Länge: 3 und Feuerstärke: 1<br>Uboot: Länge: 2 und Feuerstärke: 1<br>Die Schiffe schießen NUR Horizontal!<br>Gewonnen hat der Spieler der noch ein Schiff hat.<br>Viel Spaß bei Schiffe versenken!</body></html>");
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
	

	
	
	

	

}
