package gruppe15.chatJavaFX.common.serverIF;



import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ChatIF extends Remote {

	void sendChat(String text, String username) throws RemoteException;
	LinkedList<String> getChats() throws RemoteException;
}