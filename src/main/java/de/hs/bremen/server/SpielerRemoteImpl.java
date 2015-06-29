package de.hs.bremen.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.hs.bremen.common.SpielerRemote;
import de.hs.bremen.model.Spielfeld;

public class SpielerRemoteImpl extends UnicastRemoteObject implements SpielerRemote {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1053124899062121374L;

	protected SpielerRemoteImpl() throws RemoteException {
		super();
	}

	private String name;
	private Spielfeld spielfeldPublic;

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public Spielfeld getSpielfeldPublic() throws RemoteException {
		return spielfeldPublic;
	}

	@Override
	public void setSpielfeldPublic(Spielfeld spielfeld) throws RemoteException {
		spielfeldPublic = spielfeld;
	}

}
