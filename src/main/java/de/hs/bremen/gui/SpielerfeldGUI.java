package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.hs.bremen.controller.MainController;
import de.hs.bremen.enums.Feldstatus;
import de.hs.bremen.enums.Spielfeldmodus;
import de.hs.bremen.gui.shapes.FeldShape;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Feld;
import de.hs.bremen.model.Position;
import de.hs.bremen.model.Schiff;

public class SpielerfeldGUI extends JPanel implements java.awt.event.MouseListener{

	private static final long serialVersionUID = -9206943112708931485L;
	private MainController mainController;
	private Spielfeldmodus spielfeldmodus;
	private List<FeldShape> squares = new ArrayList<FeldShape>();
	private int spielfeldGroesse;
	private int feldgroesse;
	private boolean horizontal = true;
	private Actor gegner;

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
	
	public SpielerfeldGUI(int spielfeldGroesse, int feldgroesse, MainController mainController, Spielfeldmodus spielfeldmodus) {
		this.spielfeldGroesse = spielfeldGroesse;
		this.feldgroesse = feldgroesse;
		this.mainController = mainController;
		this.spielfeldmodus =spielfeldmodus;
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
	
	public Actor getGegner() {
		return gegner;
	}

	public void setGegner(Actor gegner) {
		this.gegner = gegner;
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
	

	public boolean innerhalbSpielfeld(int x ,int y, int laenge){
		if(horizontal){
			return x +laenge  <=spielfeldGroesse/getFeldgroesse()  && y < spielfeldGroesse/getFeldgroesse();
		}else{
			return x < spielfeldGroesse/getFeldgroesse() && y+laenge <= spielfeldGroesse/getFeldgroesse();
		}	
	}
	
	public void drawSpielfeld(){
		Feld[][] felder = mainController.getCurrentSpieler().getSpielfeld().getFelder();
		for(int i = 0 ; i < felder.length; i++){
			for (int j = 0 ; j < felder[i].length; j++){
				if(felder[i][j].getFeldstatus() !=Feldstatus.WASSER){
				fillSquare(((felder[i][j].getPosition().getPositonX()-1)*getFeldgroesse())+1, ((felder[i][j].getPosition().getPositionY()-1)*getFeldgroesse())+1, getFeldgroesse()-2, getFeldgroesse()-2, felder[i][j].getGuiInhalt());
				}
			}
		}
		repaint();
	}
	
	public void drawGegnerSpielfeld(){
		Feld[][] felder = gegner.getSpielfeldPublic().getFelder();
		for(int i = 0 ; i < felder.length; i++){
			for (int j = 0 ; j < felder[i].length; j++){
				if(i==0 && j ==0 || i==1 && j==1){
				}
				if(felder[i][j].getFeldstatus() != Feldstatus.WASSER){
					fillSquare(((felder[i][j].getPosition().getPositonX()-1)*getFeldgroesse())+1, ((felder[i][j].getPosition().getPositionY()-1)*getFeldgroesse())+1, getFeldgroesse()-2, getFeldgroesse()-2,felder[i][j].getGuiInhalt());
				}
		    }
		}
		repaint();
	}
	
	public void schiffeSetzen(int xPosition, int yPosition, int mouseButton){
		int ausrichtung;
		if(horizontal){
			ausrichtung = 1;
		}else{
			ausrichtung = 2;
		}
		if(mainController.getAusgewähltesSchiff() == null){
			JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Schiff welches Sie setzen wollen.");
		}else if(mainController.getSchiffeSetzenController().getAnzahlUngesetzteSchiffe(mainController.getAusgewähltesSchiff().getName()) == 0){
			JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein anderes Schiff.");
		}else{
			int laenge = mainController.getAusgewähltesSchiff().getLaenge();
			boolean schiffPlatzierbar = innerhalbSpielfeld(xPosition,yPosition, laenge) && mainController.getCurrentSpieler().getSpielfeld().schiffPlazierbar(mainController.getAusgewähltesSchiff(), new Position(xPosition+1, yPosition+1), ausrichtung);
			if(mouseButton == 1 && schiffPlatzierbar&& !mainController.getAusgewähltesSchiff().isPlatziert()){
				mainController.getAusgewähltesSchiff().setPlatziert(true);
				mainController.getCurrentSpieler().getSpielfeld().platziereSchiff(mainController.getAusgewähltesSchiff(), new Position(xPosition+1, yPosition+1), horizontal);
				for (int i = 0; i < laenge; i++){
					if(horizontal){
						fillSquare(((i+xPosition)*getFeldgroesse())+1,(yPosition*getFeldgroesse())+1,getFeldgroesse()-2,getFeldgroesse()-2,mainController.getAusgewähltesSchiff().getFarbe());
					}else{
						fillSquare((xPosition*getFeldgroesse())+1,((i+yPosition)*getFeldgroesse())+1,getFeldgroesse()-2,getFeldgroesse()-2,mainController.getAusgewähltesSchiff().getFarbe());
					}
				}
				mainController.getSchiffeSetzenController().getSchiffSetzenGui().schiffGesetzt();
				repaint();	
			}else if(!schiffPlatzierbar){
				JOptionPane.showMessageDialog(null, "Das Schiff darf hier nicht gesetzt werden. Bitte wähle einen anderen Punkt");
			}
		}
	}
	
	public void feuern(int xPosition, int yPosition, int mouseButton){
		Schiff getroffen = null;
		int feuerstaerke = mainController.getAusgewähltesSchiff().getFeuerstaerke();
		Position[] positionen = new Position[feuerstaerke];
		if(mainController.getAusgewähltesSchiff() == null){
			JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Schiff mit dem Sie feuern möchten.");
		}else{
			if(mouseButton == 1 && innerhalbSpielfeld(xPosition,yPosition,feuerstaerke)){
				for (int i = 0; i < feuerstaerke; i++){
					positionen[i] = new Position((xPosition+i)+1, yPosition+1);
					getroffen = gegner.getSpielfeld().getSchiffByPosition(new Position(xPosition+i+1, yPosition+1));
					System.out.println("X = " + xPosition+i + " " + yPosition+1);
					fillSquare(((i+xPosition)*getFeldgroesse())+1,(yPosition*getFeldgroesse())+1,getFeldgroesse()-2,getFeldgroesse()-2,mainController.getAusgewähltesSchiff().getFarbe());
				}
				if(getroffen != null){
					JOptionPane.showMessageDialog(null, "Sie haben ein Schiff von Spieler " + gegner.getName()+ " getroffen");
				}
				mainController.getAusgewähltesSchiff().feuern(positionen, gegner.getSpielfeldPublic());
				gegner.trefferUebertragung();
				mainController.getRundenController().gefeuert();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent ev) {
		int xPosition =ev.getX()/getFeldgroesse();
		int yPosition  = ev.getY()/getFeldgroesse();
		if(spielfeldmodus == Spielfeldmodus.SETZEN){
			schiffeSetzen(xPosition,yPosition,  ev.getButton());
		}else if(spielfeldmodus == Spielfeldmodus.GEGNER){
			feuern(xPosition,yPosition,ev.getButton());
		}	
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		if(ev.getButton() == 3){
			if(mainController.getSchiffeSetzenController().getSchiffSetzenGui().ausrichtungText.getText().equals("Ihr Schiff ist horizontal ausgerichtet.")){
				mainController.getSchiffeSetzenController().getSchiffSetzenGui().ausrichtungText.setText("Ihr Schiff ist vertikal ausgerichtet.");
				mainController.getSchiffeSetzenController().getSchiffSetzenGui().ausrichtungText.revalidate();
			} else {
				mainController.getSchiffeSetzenController().getSchiffSetzenGui().ausrichtungText.setText("Ihr Schiff ist horizontal ausgerichtet.");
				mainController.getSchiffeSetzenController().getSchiffSetzenGui().ausrichtungText.revalidate();
			}
			horizontal = !horizontal;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
}
