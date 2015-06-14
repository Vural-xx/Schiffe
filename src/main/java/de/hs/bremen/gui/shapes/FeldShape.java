package de.hs.bremen.gui.shapes;

import java.awt.Color;
import java.awt.Rectangle;

public class FeldShape extends Rectangle{

	Color color;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8363210663687042404L;
	
	public FeldShape(Color c){
		color = c;
		
	}
	
	public FeldShape(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public FeldShape(int x, int y, int width, int height, Color c){
		super(x, y, width, height);
		setColor(c);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
