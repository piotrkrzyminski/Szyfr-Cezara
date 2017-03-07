package es.hol.piotrkrzyminski;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage; //glowne okno aplikacji
	private Parent parent; //zawartosc okna aplikacji
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		
		loadFXML();
		drawGUI();
	}
	
	/**
	 * pobierz plik fxml definiujacy wyglad sceny i przypisz go do zmiennej rootScene
	 * rzuc wyjatkiem w razie niepowodzenia w odczytaniu pliku
	 */
	private void loadFXML() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainFXML.fxml"));
		parent = (Parent) loader.load();
	}
	
	/**
	 * stwórz nowa scene i przypisz ja do okna aplikacji
	 * ustaw apliakcje na widoczna
	 */
	private void drawGUI() {
		Scene scene = new Scene(parent);
		primaryStage.setTitle("Szyfr Cezara");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
