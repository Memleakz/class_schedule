/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lawar15
 */
public class ClassScheduleClient extends Application{
   	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Client/GUI/ClientFXML.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Class scheduling system");
	}

	public static void main(String[] args) {
		launch(args);
	}

} 

