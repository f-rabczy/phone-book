package telebook.io;

import telebook.model.Contact;
import telebook.model.PhoneBook;

import java.io.*;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataReader {


    public static TreeMap<String, Contact> readContacts(String fileName) throws IOException {
        TreeMap<String, Contact> contactsFromFile = new TreeMap<>();
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Unable to read file");
        }
        if (file.length() != 0) {
            try (
                    var reader = new BufferedReader(new FileReader(file))
            ) {
                List<String> collect = reader.lines().collect(Collectors.toList());
                for (String s : collect) {
                    Contact tempContact = csvToContact(s);
                    contactsFromFile.put(tempContact.getContactName(), tempContact);
                }
            }
        }
        return contactsFromFile;
    }


    private static Contact csvToContact(String csv) {
        String[] split = csv.split(";");
        String name = split[0];
        String number = split[1];
        return new Contact(name, number);
    }

    public static void saveContacts(String fileName, PhoneBook phoneBook) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            try (
                    var writer = new BufferedWriter(new FileWriter(file))
            ) {
                for (Contact contact : phoneBook) {
                    writer.write(contact.toCSV());
                    writer.newLine();
                }
            }
        }

    }
}
/*
 public static TeleBook read() {
        TeleBook book = null;
        try {
            var buffReader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = buffReader.lines()
                    .map(line -> line.split(";"))
                    .map(split -> new Contact(split[0], split[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
            book = new TeleBook(new TreeMap<>(contacts));
        } catch (FileNotFoundException e) {
            //ignore, just create empty TeleBook
        }
        return book != null? book : new TeleBook();
    }
 */