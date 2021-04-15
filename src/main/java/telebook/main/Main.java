package telebook.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static telebook.controller.MainPaneController.phoneBook;
import static telebook.io.DataReader.readContacts;
import static telebook.io.DataReader.saveContacts;

public class Main extends Application {
    private static final String FILE_NAME = "contacts.csv";

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void init() throws Exception {
        phoneBook.setPhoneBook(readContacts(FILE_NAME));
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Phone Book");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        saveContacts(FILE_NAME, phoneBook);
    }
}
