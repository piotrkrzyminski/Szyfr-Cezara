package es.hol.piotrkrzyminski.view;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.hol.piotrkrzyminski.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements Initializable{

	private Model model = new Model();
	private File file;
	private StringBuilder stringBuilder;
	private Scanner in;
	
	/**
	 * zmienne uzyte podczas przypisywania elementom interfejsu nazw definiujacych je w tym pliku
	 */
	@FXML
	private BorderPane node;
	@FXML
	private TextArea inputTextArea;
	@FXML
	private TextArea resultTextArea;
	@FXML
	private TextField keyTextField;
	@FXML
	private Button encryptButton;
	@FXML
	private Button saveButton;
	@FXML
	private MenuItem newMenuItem;
	@FXML
	private MenuItem openMenuItem;
	@FXML
	private MenuItem closeMenuItem;
	@FXML
	private Label infoLabel;
	@FXML
	private Label endInfo;
	
	/**
	 * akcja przypisana do przycisku Szyfruj
	 * sprawdza czy metoda isFieldsValueProper zrwaca prawde
	 * jesli tak to wysyla dane z pola tekstowego i klucz do pliku model.java
	 * wyswietl informacje o zakonczeniu szyfrowania
	 * w przeciwnym razie wyswietl infomacje o bledzie
	 * @param event
	 */
	@FXML
	void encryptButtonOnAction(ActionEvent event) {
		resultTextArea.setText(null);
		if(isFieldsValuesProper()) {
			setLabelInfo(infoLabel,null);
			resultTextArea.setText(model.encrypt(inputTextArea.getText(), keyTextField.getText()));
			setLabelInfo(endInfo, "Zakoñczono szyfrowanie");
		}
		else {
			setLabelInfo(infoLabel, "Wprowadzone dane s¹ niepoprawne");
		}
	}
	
	/**
	 * czysci wszytskie pola
	 * @param event
	 */
	@FXML
	void newFile(ActionEvent event) {
		inputTextArea.setText(null);
		resultTextArea.setText(null);
		keyTextField.setText(null);
		setLabelInfo(endInfo, null);
		setLabelInfo(infoLabel, null);
	}
	
	/**
	 * wczytuje ciag z pliku txt wybranego przez uzytkownika i wyswietla go w polu tekstowym
	 * @param event
	 */	
	@FXML
	void openFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Otwórz");
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);
		
		file = fileChooser.showOpenDialog((Stage) node.getScene().getWindow());
		
		if(file != null)
			inputTextArea.setText(getFileText());
	}
	
	/**
	 * opcja zamykania aplikacji z menu
	 * @param event
	 */
	@FXML
	void closeApp(ActionEvent event) {
		System.exit(0);
	}
	
	/**
	 * opcja zapisujaca zaszyfrowany juz ciag znakow do pliku w lokalizacji podanej przez uzytkownika
	 * @param event
	 */
	@FXML
	void saveFle(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz");
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);
		
		file = fileChooser.showSaveDialog((Stage) node.getScene().getWindow());
		
		if(file != null)
			saveFile(resultTextArea.getText(), file);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		infoLabel.setText("");
	}
	
	/**
	 * metoda sprawdza czy pole tekstowe nie jest puste i za pomoc¹ wyrazenia regularnego sprawdza poprawnosc wprowadzonego klucza
	 * @return
	 */
	private boolean isFieldsValuesProper() {
		Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]*$");
		Matcher matcher = pattern.matcher(keyTextField.getText());
		
		if(inputTextArea.getText() != null && matcher.find())
			return true;
		return false;
	}
	
	/**
	 * nadaj ciagowi tekstowemu okreslonego w argumencie tekst podany w argumencie
	 * @param label
	 * @param info
	 */
	private void setLabelInfo(Label label, String info) {
		label.setText(info);
	}
	
	/**
	 * metoda odpowiadajaca za pobranie zawartosci pliku wczytanego przez uzytkownika
	 * @return
	 */
	private String getFileText() {
		try {
			in = new Scanner(file);
			stringBuilder = new StringBuilder();
			
			while(in.hasNext()) {
				stringBuilder.append(in.next() + " ");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return stringBuilder.toString().trim();
	}

	/**
	 * metoda odpowiadajaca za zapisanie pliku  
	 * @param content
	 * @param file
	 */
	private void saveFile(String content, File file) {
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file.getAbsolutePath().toString());
			fileWriter.write(content);
			fileWriter.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
