package de.hs.bremen.gui.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Squares extends JPanel {
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 3518123363196992970L;
	
	/**
	 * bevorzugte Breite
	 */
	private static final int PREF_W = 300;
	
	/**
	 * bevorzugte Höhe
	 */
	private static final int PREF_H = PREF_W;
	
	/**
	 * Arraylist der Quadrate
	 */
	private List<FeldShape> squares = new ArrayList<FeldShape>();

	/**
	 * Fügt die Quadrate hinzu
	 * @param x Zeile
	 * @param y Spalte
	 * @param width Breite
	 * @param height Höhe
	 */
	public void addSquare(int x, int y, int width, int height) {
		FeldShape rect = new FeldShape(x, y, width, height);
		squares.add(rect);
	}

	/**
	 * Füllt die Quadrate mit Farbe
	 * @param x Zeile
	 * @param y Spalte
	 * @param width Breite
	 * @param height Höhe
	 * @param c Farbe
	 */
	public void fillSquare(int x, int y, int width, int height, Color c) {
		FeldShape rect = new FeldShape(x, y, width, height, c);
		squares.add(rect);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (FeldShape rect : squares) {
			if(rect.getColor() != null){
				g2.setColor(rect.getColor());
				g2.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getHeight(), (int)rect.getWidth());
			}else{
				g2.draw(rect);
			}
		}
	}

}
