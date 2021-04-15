package telebook.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchContactsPaneController {

    @FXML
    private Label searchByLabel;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchByTextField;

    public Label getSearchByLabel() {
        return searchByLabel;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getSearchByTextField() {
        return searchByTextField;
    }


}
