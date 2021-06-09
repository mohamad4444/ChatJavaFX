package gruppe15.chatJavaFX.client;

import gruppe15.chatJavaFX.common.serverIF.ChatIF;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Client {
	public final String SERVER_URL = "rmi://localhost:1099/Server";
	String username;
	public LinkedList<String> chats;
	public ChatIF server_stub;
	/**
	 * Constructor for client
	 * 
	 * @throws RemoteException       server connection problems
	 * @throws NotBoundException     One of server services is not bound
	 * @throws MalformedURLException wrong url is given
	 */
	public Client(String username) throws MalformedURLException, NotBoundException, RemoteException {
		this.username=username;
		this.chats=new LinkedList<>();
		server_stub = (ChatIF) Naming.lookup(SERVER_URL);
		Thread t = new Thread(){
			public void run(){
				while(true){
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						chats=server_stub.getChats();
					} catch (RemoteException e) {
						e.printStackTrace();
					}

				}
			}

		};
		t.start();
	}
	public void sendMessage(String message) throws RemoteException {
		server_stub.sendChat(message,this.username);
	}



}
