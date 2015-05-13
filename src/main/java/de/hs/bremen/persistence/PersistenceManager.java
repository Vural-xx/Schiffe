package de.hs.bremen.persistence;

import java.io.IOException;

/**
 * Interface zum speichern von Objekten
 * @author vural
 *
 */
public interface PersistenceManager {

	/**
	 * Öffnet eine Datei zum Lesen.
	 * @param datenquelle: Datenquelle die gelesen werden soll.
	 * @throws IOException: Input/Output Exception
	 */
	public void openForReading(String datenquelle) throws IOException;
	
	/** Öffnet eine Datei zum Speichern.
	 * @param datenquelle: Datenqeulle in der gespeichert werden soll.
	 * @throws IOException: Input/Output Exception
	 */
	public void openForWriting(String datenquelle) throws IOException;
	
	
}
