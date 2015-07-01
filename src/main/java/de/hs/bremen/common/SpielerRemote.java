package de.hs.bremen.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import de.hs.bremen.model.Actor;
import de.hs.bremen.model.Spieler;
import de.hs.bremen.model.Spielfeld;
import de.hs.bremen.model.SpielfeldPublic;

public interface SpielerRemote extends Remote {

	public void createGame(int anzahlSpieler, Spieler spieler) throws RemoteException;
	public void joinGame(Spieler spieler) throws RemoteException;
	public void feuern(String name) throws RemoteException;
	public boolean nameVergeben(String name) throws RemoteException;
	public ArrayList<Actor> getEnemys(String spieler) throws RemoteException;
	public void updateEnemy(Spieler spieler) throws RemoteException;

}
