package de.hs.bremen.gui;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestGui extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 357861578548217419L;

	public TestGui(){
		this.setLayout(new GridBagLayout());
		JButton jButton = new JButton();
		jButton.setText("Hallo");
		this.add(jButton);
		setBounds(100, 100, 500, 500);
		setVisible(true);
		repaint();
	}
}
