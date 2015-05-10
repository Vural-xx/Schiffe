package de.hs.bremen.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.hs.bremen.model.Spiel;
import de.hs.bremen.model.Spieler;

public class ObjectPersistenceManager implements PersistenceManager {
	
	/**
	 * Stream zum Schreiben.
	 */
	ObjectOutputStream outputStream= null;
	/**
	 * Stream zum Lesen.
	 */
	ObjectInputStream inputStream = null;

	@Override
	public void openForReading(String datenquelle) throws IOException {
		inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(datenquelle)));		
	}

	@Override
	public void openForWriting(String datenquelle) throws IOException {
		outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datenquelle)));
		
	}
	
	public void spielstandSpeichern(Spiel spiel){
		try {
			openForWriting("spiel");
			outputStream.writeObject(spiel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(outputStream !=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public Spiel spielstandLaden(){
		Spiel spiel = null;
		try {
			openForReading("spiel");
			spiel = (Spiel)inputStream.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(outputStream !=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return spiel;
	}
	
	public static void main(String[] args) {
		Spiel spiel = new Spiel();
		Spieler spieler[] = new Spieler[2];
		spieler[0] = new Spieler("Horst");
		spieler[1] = new Spieler("Hartmut");
		spiel.setSpieler(spieler);
		ObjectPersistenceManager pm = new ObjectPersistenceManager();
		try {
			pm.openForWriting("spiel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pm.spielstandSpeichern(spiel);
		Spiel geladenesSpiel = pm.spielstandLaden();
		for(Spieler s: geladenesSpiel.getSpieler()){
			System.out.println("Name - " + s.getName() );
		}
		
	}
}
