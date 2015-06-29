package de.hs.bremen.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.hs.bremen.common.Settings;

public class GameServer {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		SpielerRemoteImpl remoteImpl = new SpielerRemoteImpl();
		Registry registry = LocateRegistry.createRegistry(Settings.RMI_PORT);
		registry.bind(Settings.RMI_ID,remoteImpl);
		System.out.println("Server is started ...");
	}

}
