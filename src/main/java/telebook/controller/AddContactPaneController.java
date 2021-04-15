package telebook.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddContactPaneController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private Button addButton;

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getNumberTextField() {
        return numberTextField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void initialize() {

    }

}
