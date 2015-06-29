package de.hs.bremen.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.hs.bremen.model.Spielfeld;

public interface SpielerRemote extends Remote {

	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
	
	public Spielfeld getSpielfeldPublic() throws RemoteException;
	public void setSpielfeldPublic(Spielfeld spielfeld) throws RemoteException;

}
