package telebook.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import telebook.model.Contact;
import telebook.model.PhoneBook;

import java.io.IOException;
import java.util.List;

import static telebook.model.PhoneBook.configurePhoneBookColumns;

public class MainPaneController {

    public static PhoneBook phoneBook = new PhoneBook();

    @FXML
    private TableView<Contact> contactsTable;

    @FXML
    private Button addContactButton;

    @FXML
    private Button deleteContactButton;

    @FXML
    private Button findByNumberButton;

    @FXML
    private Button findByNameButton;

    @FXML
    private TextField messageTextField;

    public void initialize() {
        configurePhoneBookColumns(contactsTable);
        importContactsFromPhoneBook();
        configureDeleteContactButton();
        configureAddContactButton();
        configureFindByNumberButton();
        configureFindByNameButton();
    }

    private void loadResults(List<Contact> foundContacts) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        configureAndDisplayStage("/fxml/foundResultsPane.fxml", loader, "Found Contacts");
        FoundResultsPaneController controller = loader.getController();
        ObservableList<Contact> items = controller.getFoundContactsTable().getItems();
        items.addAll(foundContacts);
    }

    public void configureFindByNameButton() {
        findByNameButton.setOnAction(mouseClick -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                configureAndDisplayStage("/fxml/searchPane.fxml", loader, "Find");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SearchContactsPaneController controller = loader.getController();
            searchByNameSegment(controller);
        });
    }

    public void configureAndDisplayStage(String resource, FXMLLoader loader, String sceneTitle) throws IOException {
        loader.setLocation(getClass().getResource(resource));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }

    public void configureFindByNumberButton() {
        findByNumberButton.setOnAction(mouseClick -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/searchPane.fxml"));
            try {
                configureAndDisplayStage("/fxml/searchPane.fxml", loader, "Find");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SearchContactsPaneController controller = loader.getController();
            searchByNumberSegment(controller);
        });
    }

    private void searchByNameSegment(SearchContactsPaneController controller) {
        controller.getSearchByLabel().setText("Name segment:");
        controller.getSearchButton().setOnAction(mouseClick -> {
            String segmentToSearchBy = controller.getSearchByTextField().getText();
            List<Contact> contactsByNameSegment = phoneBook.findContactsByNameSegment(segmentToSearchBy);
            try {
                loadResults(contactsByNameSegment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void searchByNumberSegment(SearchContactsPaneController controller) {
        controller.getSearchByLabel().setText("Number segment:");
        controller.getSearchButton().setOnAction(mouseClick -> {
            String segmentToSearchBy = controller.getSearchByTextField().getText();
            List<Contact> contactsByNumberSegment = phoneBook.findContactsByNumber(segmentToSearchBy);
            try {
                loadResults(contactsByNumberSegment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void configureDeleteContactButton() {
        deleteContactButton.setOnAction(mouseClick -> {
            Contact contactToDelete = contactsTable.getSelectionModel().getSelectedItem();
            ObservableList<Contact> items = contactsTable.getItems();
            items.remove(contactToDelete);
            if (phoneBook.delete(contactToDelete.getContactName())) {
                showMessage("Contact successfully deleted");
            } else {
                showMessage("Contact has not been chosen");
            }

        });
    }

    private void importContactsFromPhoneBook() {
        ObservableList<Contact> items = contactsTable.getItems();
        for (Contact contact : phoneBook) {
            items.add(contact);
        }
    }

    private void addContactToPhoneBook(Contact contact) {
        phoneBook.add(contact.getContactName(), contact.getNumber());
        contactsTable.getItems().add(contact);
    }


    public void configureAddContactButton() {
        addContactButton.setOnAction(mouseClick -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                configureAndDisplayStage("/fxml/addContactPane.fxml", loader, "Add Contact");
            } catch (IOException e) {
                e.printStackTrace();
            }
            AddContactPaneController controller = loader.getController();
            configureAddButton(controller);
        });
    }


    public void configureAddButton(AddContactPaneController controller) {
        Contact newContact = new Contact();
        controller.getAddButton().setOnAction(mouseClick -> {
            if (!controller.getNameTextField().getText().isEmpty() && !controller.getNumberTextField().getText().isEmpty()) {
                String name = controller.getNameTextField().getText();
                String number = controller.getNumberTextField().getText();
                newContact.setContactName(name);
                newContact.setNumber(number);
                addContactToPhoneBook(newContact);
                controller.getNameTextField().clear();
                controller.getNumberTextField().clear();
            }
        });
    }

    private void showMessage(String message) {
        messageTextField.setText(message);
    }

}
