package telebook.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPaneController {

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem aboutMenuItem;


    public void initialize() {
        configureMenu();
    }

    private void configureMenu() {
        closeMenuItem.setOnAction(mouseClick -> Platform.exit());

        aboutMenuItem.setOnAction(mouseClick -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/aboutPane.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("PhoneBook v1.0 about");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
