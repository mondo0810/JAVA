import java.util.List;
import java.util.Scanner;

public class Controller {
    private AddressBook addressBook;
    private Scanner scanner;

    public Controller(AddressBook addressBook, Scanner scanner) {
        this.addressBook = addressBook;
        this.scanner = scanner;
    }

    public void addContact() {
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Company: ");
        String company = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, company, email, phone);
        addressBook.addContact(contact);

        System.out.println("Contact added successfully!");
    }

    public void findContact() {
        scanner.nextLine();
        System.out.print("Enter the name to find: ");
        String name = scanner.nextLine();

        List<Contact> similarContacts = addressBook.findSimilarContacts(name);
        if (similarContacts.isEmpty()) {
            System.out.println("Not found.");
        } else {
            System.out.println("Similar Contacts:");
            for (Contact contact : similarContacts) {
                System.out.println("Name: " + contact.getName() + " | Phone: " + contact.getPhone());
            }
        }
    }

    public void displayContacts() {
        System.out.println(addressBook.displayContacts());
    }
}
