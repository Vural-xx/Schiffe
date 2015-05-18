package de.hs.bremen.persistence;

import helper.IO;

import java.io.File;

import de.hs.bremen.model.Spiel;

public class SpielstandManager {

	public boolean spielstaendeVorhanden(){
		File folder = new File("src/temp");
		File[] listOfFiles = folder.listFiles();
		return listOfFiles!= null;
	}
	
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
		
		return opm.spielstandLaden(listOfFiles[spielstand-1].getName());
	
	}
	
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
		
		System.out.println("Bitte geben Sie den Namen des Speicherstandes an");
		spielstandName = IO.readString();
		if(spielstand >= listOfFiles.length){
			opm.spielstandSpeichern(spiel, spielstandName);
		}else{
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
