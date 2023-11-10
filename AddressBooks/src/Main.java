import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(addressBook, scanner);
        int choice;

        do {
            System.out.println("\nAddressBooks Menu:");
            System.out.println("1. Add new contact");
            System.out.println("2. Find a contact by name");
            System.out.println("3. Display contacts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.addContact();
                    break;
                case 2:
                    controller.findContact();
                    break;
                case 3:
                    controller.displayContacts();
                    break;
                case 4:
                    System.out.println("Exiting the Address Book. Thank You!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }
}
