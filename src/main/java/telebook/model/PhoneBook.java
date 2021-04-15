package telebook.model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;

public class PhoneBook implements Iterable<Contact> {
    private static final String NAME_COLUMN = "Name";
    private static final String NUMBER_COLUMN = "Number";

    private Map<String, Contact> phoneBook = new TreeMap<>();

    public PhoneBook() {
    }


    public void setPhoneBook(Map<String, Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public boolean add(String name, String number) {
        if (name == null || number == null) {
            throw new NullPointerException("Name and number cant be null");
        }
        if (name.isEmpty() || number.isEmpty()) {
            throw new IllegalArgumentException("Name and number cant be empty");
        }
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new Contact(name, number));
            return true;
        } else {
            return false;
        }

    }

    public boolean delete(String name) {
        return phoneBook.remove(name) != null;
    }

    public List<Contact> findContactsByNameSegment(String name) {
        List<Contact> foundContacts = new ArrayList<>();
        for (var entry : phoneBook.entrySet()) {
            if (entry.getKey().contains(name)) {
                foundContacts.add(entry.getValue());
            }
        }
        if (foundContacts.isEmpty()) {
            throw new IllegalArgumentException("Contacts containing given name can't be found, it does not exist or given name is incorrect");
        }
        return foundContacts;
    }

    public List<Contact> findContactsByNumber(String number) {
        List<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : phoneBook.values()) {
            if (contact.getNumber().contains(number)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    @Override
    public Iterator<Contact> iterator() {
        return phoneBook.values().iterator();
    }


    public static void configurePhoneBookColumns(TableView<Contact> table) {
        TableColumn<Contact, String> nameColumn = new TableColumn<>(NAME_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));

        TableColumn<Contact, String> numberColumn = new TableColumn<>(NUMBER_COLUMN);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        table.getColumns().add(nameColumn);
        table.getColumns().add(numberColumn);
    }
}
