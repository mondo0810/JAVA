import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> findSimilarContacts(String partialName) {
        List<Contact> similarContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(partialName.toLowerCase())) {
                similarContacts.add(contact);
            }
        }
        return similarContacts;
    }


    public String displayContacts() {
        if (contacts.isEmpty()) {
            return "No contacts to display.";
        } else {
            StringBuilder result = new StringBuilder(String.format("%-20s %-20s %-30s %-15s \n", "Name", "Company", "Email", "Phone"));
            result.append("------------------------------------------------------------------------------ \n");
            for (Contact contact : contacts) {
                result.append(String.format("%-20s %-20s %-30s %-15s \n", contact.getName(), contact.getCompany(), contact.getEmail(), contact.getPhone()));
            }

            return result.toString();
        }
    }


}
