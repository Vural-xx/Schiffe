package de.hs.bremen.persistence;

import helper.ConsoleColor;
import helper.IO;

import java.io.File;

import de.hs.bremen.model.Spiel;

/**
 * Klasse die Methoden zum Speichern und Laden beinhaltet.
 * @author vural
 *
 */
public class SpielstandManager {

	/**
	 * Zeigt an, ob es schon Spielstände gibt.
	 * @return
	 */
	public boolean spielstaendeVorhanden(){
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		return listOfFiles!= null;
	}
	
	/**
	 * Zeigt an ob ein bestimmter Spielstandname schon vergeben wurde.
	 * Nicht beachtet wird der Name des Spielstandes der den index vom Übergabeparameter index hat.
	 * @param name: Name des Spielstandes der überprüft werden soll.
	 * @param index: Index des Spielstandes dessen Name außer Acht gelassen werden soll.
	 * @return: Ob Spielstandname bereits vergeben ist
	 */
	public boolean spielstandVorhanden(String name, int index){
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		for(int i = 0 ; i <listOfFiles.length; i++){
			if(listOfFiles[i].getName().equals(name) && i !=index){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Das Lademenü zum Laden von bereits vorhandenen Spielständen.
	 * @return: Spiel das geladen wurde
	 */
	public Spiel ladeMenu(){
		int spielstand;
		String ladeMenu = "";
		System.out.println("Bitte geben Sie an welchen Spielstand Sie laden möchten.");
		ObjectPersistenceManager opm = new ObjectPersistenceManager();
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			ladeMenu = ladeMenu +"Stand " +(i+1)+ ": " + listOfFiles[i].getName()+ " \t";
		}
		System.out.println(ladeMenu);
		spielstand = IO.readInt();
		while(spielstand <1 || spielstand >listOfFiles.length){
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Falsche Eingabe versuchen Sie es erneut:"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			spielstand = IO.readInt();
		}
		
		return opm.spielstandLaden(listOfFiles[spielstand-1].getName());
	
	}
	
	/**
	 * Das Menü zum Speichern des jetzigen Spielzustandes.
	 * @param spiel: Spielobjekt welches gespeichert werden soll.
	 */
	public void speicherMenu(Spiel spiel){
		int spielstand;
		String spielstandName;
		String saveMenu = "";
		System.out.println("Bitte geben Sie an welchen Spielstand sie überschreiben oder neu schreiben möchten");
		ObjectPersistenceManager opm = new ObjectPersistenceManager();
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			saveMenu = saveMenu +"Stand " +(i+1)+ ": " + listOfFiles[i].getName()+ " \t";
		}
		
		if(listOfFiles.length< 4){
			for(int i = listOfFiles.length; i < 4; i++){
				saveMenu = saveMenu +"Stand " +(i+1)+ ": LEER \t";
			}
		}
		System.out.println(saveMenu);
		spielstand = IO.readInt();
		while(spielstand <1 || spielstand >4){
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"Falsche Eingabe versuchen Sie es erneut:"+ConsoleColor.ANSI_RESET);
			System.out.println(ConsoleColor.ANSI_RED+"========================================"+ConsoleColor.ANSI_RESET);
			spielstand = IO.readInt();
		}
		
		System.out.println("Bitte geben Sie den Namen des Speicherstandes an");
		spielstandName = IO.readString();
		if(spielstand >= listOfFiles.length){
			while (spielstandVorhanden(spielstandName, listOfFiles.length)){
				System.out.println(ConsoleColor.ANSI_RED+"Es gibt bereits einen Spielstand mit diesem Namen."+ConsoleColor.ANSI_RESET);
				System.out.println("Geben Sie einen anderen Namen an");
				spielstandName = IO.readString();
			}
			opm.spielstandSpeichern(spiel, spielstandName);
		}else{
			while (spielstandVorhanden(spielstandName, spielstand-1)){
				System.out.println(ConsoleColor.ANSI_RED+"Es gibt bereits einen Spielstand mit diesem Namen."+ConsoleColor.ANSI_RESET);
				System.out.println("Geben Sie einen anderen Namen an");
				spielstandName = IO.readString();
			}
			if(listOfFiles[spielstand-1].getName().equals(spielstandName)){
				opm.spielstandSpeichern(spiel, spielstandName);
			}else{
				listOfFiles[spielstand-1].delete();
				opm.spielstandSpeichern(spiel, spielstandName);
			}
		}
		System.out.println("Ihr Spielstand wurde erfolgreich gespeichert");
	}
}
