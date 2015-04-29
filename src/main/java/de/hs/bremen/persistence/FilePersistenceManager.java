package de.hs.bremen.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(
					new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(
					new BufferedWriter(
						new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}
}
