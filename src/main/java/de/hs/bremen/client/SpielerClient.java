package de.hs.bremen.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.hs.bremen.common.Settings;
import de.hs.bremen.common.SpielerRemote;

public class SpielerClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost",Settings.RMI_PORT);
		SpielerRemote remote =  (SpielerRemote) registry.lookup(Settings.RMI_ID);
		remote.setName("Hamster");
		System.out.println(remote.getName());
		

	}

}
