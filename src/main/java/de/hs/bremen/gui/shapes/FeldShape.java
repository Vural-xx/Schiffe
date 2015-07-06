package de.hs.bremen.gui.shapes;

import java.awt.Color;
import java.awt.Rectangle;

public class FeldShape extends Rectangle{

	
	/**
	 * SerialVersionUID zum Speichern und Lesen
	 */
	private static final long serialVersionUID = 8363210663687042404L;
	
	/**
	 * Farbe
	 */
	Color color;
	
	/**
	 * Farbe des Quadrat
	 * @param c: Farbe
	 */
	public FeldShape(Color c){
		color = c;
		
	}
	
	/**
	 * Konstruktor
	 * @param x Zeile
	 * @param y Spalte
	 * @param width Breite
	 * @param height Höhe
	 */
	public FeldShape(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	/**
	 * Konstruktor
	 * @param x Zeile
	 * @param y Spalte
	 * @param width Breite
	 * @param height Höhe
	 * @param c Farbe
	 */
	public FeldShape(int x, int y, int width, int height, Color c){
		super(x, y, width, height);
		setColor(c);
	}

	/**
	 * Getter Farbe
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter Farbe
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	
}
