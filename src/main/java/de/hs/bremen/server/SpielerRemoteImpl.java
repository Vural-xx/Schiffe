package de.hs.bremen.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import de.hs.bremen.common.SpielerRemote;
import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spieler;
import de.hs.bremen.model.Spielfeld;
import de.hs.bremen.model.SpielfeldPublic;

public class SpielerRemoteImpl extends UnicastRemoteObject implements SpielerRemote {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1053124899062121374L;
	private ArrayList<Actor> spieler;
	private int anzahlSpieler;
	String message;
	
	protected SpielerRemoteImpl() throws RemoteException {
		super();
	}


	@Override
	public void feuern(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createGame(int anzahlSpieler, Spieler initializer) throws RemoteException {
		this.anzahlSpieler = anzahlSpieler;
		spieler.add(initializer);
	}

	@Override
	public void joinGame(Spieler spieler) throws RemoteException {
		if(this.spieler.size() != anzahlSpieler){
			this.spieler.add(spieler);
		}
	}

	@Override
	public ArrayList<Actor> getEnemys(String spieler) throws RemoteException {
		ArrayList<Actor> tempArray = new ArrayList<Actor>();
		for(int i = 0; i< tempArray.size(); i++){
			if(!this.spieler.get(i).getName().equals(spieler)){
				tempArray.add(this.spieler.get(i));
			}
		}
		return tempArray;
	}


	@Override
	public boolean nameVergeben(String name) throws RemoteException {
		boolean vergeben = false;
		for(Actor s: this.spieler ){
			if(s.getName().equals(name)){
				vergeben = true;
			}
		}
		return vergeben;
	}


	@Override
	public void updateEnemy(Spieler spieler) throws RemoteException {
		for(int i = 0; i< this.spieler.size(); i++){
			if(this.spieler.get(i).getName().equals(spieler.getName())){
				this.spieler.set(i, spieler);
			}
		}	
	}

}
