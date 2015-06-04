package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import de.hs.bremen.gui.shapes.Squares;

public class Spielerfeld extends JPanel implements java.awt.event.MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206943112708931485L;
	
	private Squares squares;
	private int spielfeldGroesse;
	
	public Spielerfeld(int spielfeldGroesse, int feldgroesse) {
		this.spielfeldGroesse = spielfeldGroesse;
		squares = new Squares();
		addMouseListener(this);
		for(int i = 0; i <=10; i++){
			squares.addSquare(0, i*30, 30, 30);
			squares.addSquare(i*30, 0, 30, 30);
			if(i != 0){
				squares.addSquare(30, i*30,30,30);
				squares.addSquare(60, i*30,30,30);
				squares.addSquare(90, i*30,30,30);
				squares.addSquare(120, i*30,30,30);
				squares.addSquare(150, i*30,30,30);
				squares.addSquare(180, i*30,30,30);
				squares.addSquare(210, i*30,30,30);
				squares.addSquare(240, i*30,30,30);
				squares.addSquare(270, i*30,30,30);
			}
		}
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
		int xPosition =ev.getX()/30;
		int yPosition  = ev.getY()/30;
		System.out.println("X-Position " + xPosition);
		System.out.println("Y-Position " + yPosition);
		System.out.println("Bin drin");
		squares.fillSquare(xPosition*30,(yPosition-1)*30,30,30,Color.RED);
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
