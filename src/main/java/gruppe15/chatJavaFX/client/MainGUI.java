package gruppe15.chatJavaFX.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class MainGUI extends Application {
	public static Client client;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * start of application
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			client = new Client("user1");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxmls/Chat.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Exploding Kittens");
			Scene main = new Scene(root);
			primaryStage.setScene(main);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Stop of application
	 */
	@Override
	public void stop() throws IOException, NotBoundException,AlreadyBoundException {
		System.exit(0);
	}

}