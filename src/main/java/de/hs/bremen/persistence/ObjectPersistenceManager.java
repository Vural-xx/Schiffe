package de.hs.bremen.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.hs.bremen.model.Spiel;

public class ObjectPersistenceManager implements PersistenceManager {
	
	/**
	 * Stream zum Schreiben.
	 */
	ObjectOutputStream outputStream= null;
	
	/**
	 * Stream zum Lesen.
	 */
	ObjectInputStream inputStream = null;

	/* (non-Javadoc)
	 * @see de.hs.bremen.persistence.PersistenceManager#openForReading(java.lang.String)
	 */
	@Override
	public void openForReading(String datenquelle) throws IOException {
		inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(datenquelle)));		
	}

	/* (non-Javadoc)
	 * @see de.hs.bremen.persistence.PersistenceManager#openForWriting(java.lang.String)
	 */
	@Override
	public void openForWriting(String datenquelle) throws IOException {
		outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datenquelle)));
		
	}
	
	/**
	 * Speichert den Spielstand
	 * @param spiel: Welches Spiel gespeichert werden soll
	 * @param datenquelle: Wie das Spiel heißt
	 */
	public void spielstandSpeichern(Spiel spiel,String datenquelle){
		try {
			openForWriting("src/temp/"+datenquelle);
			outputStream.writeObject(spiel);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(outputStream !=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Läd einen gespeicherten Spielstand
	 * @param datenquelle: die Name des Spielstand
	 * @return
	 */
	public Spiel spielstandLaden(String datenquelle){
		Spiel spiel = null;
		try {
			openForReading("src/temp/"+datenquelle);
			spiel = (Spiel)inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(outputStream !=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return spiel;
	}
	
}
