package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.gui.shapes.FeldShape;
import de.hs.bremen.gui.shapes.Squares;
import de.hs.bremen.model.Feld;
import de.hs.bremen.model.Position;
import de.hs.bremen.model.Schiff;

public class SpielerfeldGUI extends JPanel implements java.awt.event.MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206943112708931485L;
	private MainController mainController;
	private List<FeldShape> squares = new ArrayList<FeldShape>();
	private int spielfeldGroesse;
	private int feldgroesse;
	private boolean horizontal = true;
	private int laenge = 3;

	public SpielerfeldGUI(int spielfeldGroesse, int feldgroesse) {
		this.spielfeldGroesse = spielfeldGroesse;
		this.feldgroesse = feldgroesse;
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
	
	public SpielerfeldGUI(int spielfeldGroesse, int feldgroesse, MainController mainController) {
		this.spielfeldGroesse = spielfeldGroesse;
		this.feldgroesse = feldgroesse;
		this.mainController = mainController;
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

	public int getSpielfeldGroesse() {
		return spielfeldGroesse;
	}

	public void setSpielfeldGroesse(int spielfeldGroesse) {
		this.spielfeldGroesse = spielfeldGroesse;
	}

	public int getFeldgroesse() {
		return feldgroesse;
	}

	public void setFeldgroesse(int feldgroesse) {
		this.feldgroesse = feldgroesse;
	}

	public void addSquare(int x, int y, int width, int height) {
		FeldShape rect = new FeldShape(x, y, width, height);
		squares.add(rect);
	}

	public void fillSquare(int x, int y, int width, int height, Color c) {
		FeldShape rect = new FeldShape(x, y, width, height, c);
		squares.add(rect);
	}


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
	

	public boolean innerhalbSpielfeld(int x ,int y){
		if(horizontal){
			return x +laenge  <=spielfeldGroesse/getFeldgroesse()  && y < spielfeldGroesse/getFeldgroesse();
		}else{
			return x < spielfeldGroesse/getFeldgroesse() && y+laenge <= spielfeldGroesse/getFeldgroesse();
		}	
	}
	
	public void drawSpielfeld(){
		for(Schiff s: mainController.getCurrentSpieler().getSpielfeld().getSchiffe()){
			for(Feld f: s.getFelder()){
				fillSquare(f.getPosition().getPositonX()*getFeldgroesse(), f.getPosition().getPositionY()*getFeldgroesse(), getFeldgroesse(), getFeldgroesse(), Color.RED);
			}
		}
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
		int xPosition =ev.getX()/getFeldgroesse();
		int yPosition  = ev.getY()/getFeldgroesse();
		System.out.println("X-Position " + xPosition);
		System.out.println("Y-Position " + yPosition);
		System.out.println("Bin drin");
		
		if(ev.getButton() == 1 && innerhalbSpielfeld(xPosition,yPosition)){
			this.laenge = mainController.getAusgewähltesSchiff().getLaenge();
			for (int i = 0; i < laenge; i++){
				if(horizontal){
					mainController.getCurrentSpieler().getSpielfeld().platziereSchiff(mainController.getAusgewähltesSchiff(), new Position(xPosition+1, yPosition+1), horizontal);
					fillSquare((i+xPosition)*getFeldgroesse(),yPosition*getFeldgroesse(),getFeldgroesse(),getFeldgroesse(),Color.RED);
				}else{
					fillSquare(xPosition*getFeldgroesse(),(i+yPosition)*getFeldgroesse(),getFeldgroesse(),getFeldgroesse(),Color.RED);
				}
			}
			repaint();	
		}
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		if(ev.getButton() == 3){
			System.out.println("Test");
			horizontal = !horizontal;
		}
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
