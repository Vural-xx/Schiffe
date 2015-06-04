package de.hs.bremen.gui.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.hs.bremen.gui.Feld;

public class Squares extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3518123363196992970L;
	private static final int PREF_W = 300;
	private static final int PREF_H = PREF_W;
	private List<Feld> squares = new ArrayList<Feld>();

	public void addSquare(int x, int y, int width, int height) {
		Feld rect = new Feld(x, y, width, height);
		squares.add(rect);
	}

	public void fillSquare(int x, int y, int width, int height, Color c) {
		Feld rect = new Feld(x, y, width, height, c);
		squares.add(rect);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Feld rect : squares) {
			if(rect.getColor() != null){
				g2.setColor(rect.getColor());
				g2.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getHeight(), (int)rect.getWidth());
			}else{
				g2.draw(rect);
			}
		}
	}

}
