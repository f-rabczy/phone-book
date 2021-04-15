package telebook.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import telebook.model.Contact;

import static telebook.model.PhoneBook.configurePhoneBookColumns;


public class FoundResultsPaneController {

    @FXML
    private TableView<Contact> foundContactsTable;

    public TableView<Contact> getFoundContactsTable() {
        return foundContactsTable;
    }

    public void initialize() {
        configurePhoneBookColumns(foundContactsTable);
    }

}
