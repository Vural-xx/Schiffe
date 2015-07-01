package de.hs.bremen.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import de.hs.bremen.common.Settings;
import de.hs.bremen.common.SpielerRemote;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spieler;

public class GameClient {
	SpielerRemote remote;
	
	public GameClient(){
		try {
			Registry registry = LocateRegistry.getRegistry(Settings.host,Settings.RMI_PORT);
			remote = (SpielerRemote) registry.lookup(Settings.RMI_ID); 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void createGame(int anzahlSpieler, Spieler spieler){
		try {
			remote.createGame(anzahlSpieler, spieler);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void joinGame(Spieler spieler){
		try {
			remote.joinGame(spieler);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<Actor> getEnemys(Spieler spieler){
		ArrayList<Actor> actors = null;
		try {
			actors = remote.getEnemys(spieler.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}
	
	public boolean nameVergeben(Spieler spieler){
		boolean vergeben = false;
		try {
			vergeben = remote.nameVergeben(spieler.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vergeben;
	}
}
