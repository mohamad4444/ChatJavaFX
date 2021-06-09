package gruppe15.chatJavaFX.server;

import gruppe15.chatJavaFX.common.serverIF.ChatIF;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;

public class Server extends UnicastRemoteObject implements ChatIF {
	private static final long serialVersionUID = 1002020651075595249L;
	public static LinkedList<String> chats;
	public static final String url = "rmi://localhost:1099/Server";

	/**
	 *Constructor for server
	 * @throws RemoteException server connection problems
	 * @throws MalformedURLException wrong url formation
	 * @throws AlreadyBoundException lobby already bound
	 */
	public Server() throws RemoteException, MalformedURLException, AlreadyBoundException {
		Naming.rebind(url, this);
		chats = new LinkedList<>();
		System.err.println("Server ready");

	}

	/**Unbind RemoteInterfaces from server side from registry
	 * @throws RemoteException server connection problems
	 * @throws MalformedURLException url is wrong
	 * @throws NotBoundException not bound exception
	 */
	public void unbindAll() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind(url);
		System.err.println("Server unbound");
	}

	@Override
	public void sendChat(String text, String username){
		this.chats.add(username+":"+text+"\n");
	}

	@Override
	public LinkedList<String> getChats() throws RemoteException {
		return this.chats;
	}
}
