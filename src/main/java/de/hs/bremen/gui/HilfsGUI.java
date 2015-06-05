package de.hs.bremen.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HilfsGUI extends JPanel implements ActionListener {
	
	SpielerNameGUI spielerNameGUI = new SpielerNameGUI();
	SpielerAuswahlGUI spielerAuswahlGUI = new SpielerAuswahlGUI();
	public JButton weiter;
	JPanel container1;
	
	
	
	public HilfsGUI(){
		setLayout(new GridBagLayout());
		container1= new JPanel();
		
		weiter = new JButton("fortfahren");
		
		weiter.addActionListener(this);
		weiter.setPreferredSize(new Dimension(100,30));
		   GridBagConstraints c = new GridBagConstraints();
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.weightx = 1.0;
	        this.add(spielerAuswahlGUI, c);
	        this.add(spielerNameGUI, c);
	        c.weighty = 1.0;
	        // Temporary panel to fill the rest of the bigPanel
		
		

		
		container1.add(weiter, new BorderLayout());
		this.add(container1,c);
		setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == weiter)test();
		if(e.getSource() == weiter)System.out.println("test");
	}
	
public void test(){
		
		spielerAuswahlGUI.setVisible(false);
		spielerNameGUI.setVisible(true);
		
	}
	
	

}
