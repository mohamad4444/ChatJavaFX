package gruppe15.chatJavaFX.client.gui.controllers;

import gruppe15.chatJavaFX.client.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

	@FXML
	private TextArea chatarea;

	@FXML
	private TextField sendTextArea;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		new Thread(){
			@Override
			public void run(){
				while(true){
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					chatarea.setText("");  // clear text area
					LinkedList<String> temp= (LinkedList<String>) MainGUI.client.chats.clone();
					for (String c: temp){
						chatarea.appendText(c + " ");
					}//end for
				}
			}
		}.start();
	}

	@FXML
	void enterPressed(ActionEvent event) throws RemoteException {
		MainGUI.client.sendMessage(sendTextArea.getText());
		sendTextArea.setText("");
	}

	@FXML
	void sendButtonClicked(ActionEvent event) throws RemoteException {
		MainGUI.client.sendMessage(sendTextArea.getText());
		sendTextArea.setText("");
	}


}
