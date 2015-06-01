package de.hs.bremen.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements java.awt.event.MouseListener {
	public Squares squares;
	   public GameFrame() {
	      super("Game Frame");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      squares = new Squares();
	      getContentPane().add(squares);
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
	      pack();
	      setLocationRelativeTo(null);
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

	   public static void main(String[] args) {
	      new GameFrame();
	   }

	}

	class Squares extends JPanel {
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
	
	