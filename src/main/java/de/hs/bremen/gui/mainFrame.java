package de.hs.bremen.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class mainFrame extends javax.swing.JFrame{
	
	
	
	private Toolkit t;
	private int x=0,y=0,width=1280, height=768;
	
	public mainFrame(){
		
		
		
		setTitle("Spieleinstellungen");
		t=Toolkit.getDefaultToolkit();
		Dimension d= t.getScreenSize();
		
		x = (int)((d.getWidth() - width)/2);
		y = (int)((d.getHeight() - height)/2);
		setBounds(x,y,width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Fixed JFrame
        setResizable(false);
        
		initComponents();
		
		
		setVisible(true);
	}
	
	public void initComponents(){
		getContentPane().setLayout(new GridBagLayout());

		add(new HilfsGUI());

		
	}
	

	
	
	
	public static void main(String[] args){
		new mainFrame();
	}
	

}
