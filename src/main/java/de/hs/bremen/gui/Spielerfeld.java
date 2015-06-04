package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.hs.bremen.gui.shapes.Squares;

public class Spielerfeld extends JPanel implements java.awt.event.MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206943112708931485L;
	private List<Feld> squares = new ArrayList<Feld>();
	private int spielfeldGroesse;
	

	public Spielerfeld(int spielfeldGroesse, int feldgroesse) {
		this.spielfeldGroesse = spielfeldGroesse;
		addMouseListener(this);
		for(int i = 0; i <spielfeldGroesse/feldgroesse; i++){
			addSquare(0, i*feldgroesse, feldgroesse, feldgroesse);
			addSquare(i*feldgroesse, 0, feldgroesse, feldgroesse);
			if(i != 0){
				for (int j = 1; j < spielfeldGroesse/feldgroesse; j++){
					addSquare(j*feldgroesse, i*feldgroesse,feldgroesse,feldgroesse);
				}
			}
		}
		setVisible(true);
	}

	public void addSquare(int x, int y, int width, int height) {
		Feld rect = new Feld(x, y, width, height);
		squares.add(rect);
	}

	public void fillSquare(int x, int y, int width, int height, Color c) {
		Feld rect = new Feld(x, y, width, height, c);
		squares.add(rect);
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

	@Override
	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
		int xPosition =ev.getX()/30;
		int yPosition  = ev.getY()/30;
		System.out.println("X-Position " + xPosition);
		System.out.println("Y-Position " + yPosition);
		System.out.println("Bin drin");
		fillSquare(xPosition*30,(yPosition-1)*30,30,30,Color.RED);
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
