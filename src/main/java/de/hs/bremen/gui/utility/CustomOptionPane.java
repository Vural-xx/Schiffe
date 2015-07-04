package de.hs.bremen.gui.utility;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomOptionPane extends JOptionPane implements DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1480094556271464541L;
	
	public CustomOptionPane() {
		super();
	}

	
	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("Teeest");
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		System.out.println("Moin");
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("Tschüss");
		
	}

	
}
