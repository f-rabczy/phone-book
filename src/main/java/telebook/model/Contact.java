package telebook.model;

public class Contact implements Comparable<Contact> {
    private String contactName;
    private String number;


    public Contact(String contactName, String number) {
        this.contactName = contactName;
        this.number = number;
    }

    public Contact() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    @Override
    public String toString() {
        return "Contact{" + contactName +
                ", number=" + number +
                '}';
    }

    public String toCSV() {
        return contactName + ";" + number;
    }

    @Override
    public int compareTo(Contact contact) {
        return this.contactName.compareTo(contact.getContactName());
    }
}
