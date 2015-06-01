package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Rectangle;

public class Feld extends Rectangle{

	Color color;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8363210663687042404L;
	
	public Feld(Color c){
		color = c;
		
	}
	
	public Feld(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public Feld(int x, int y, int width, int height, Color c){
		super(x, y, width, height);
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
