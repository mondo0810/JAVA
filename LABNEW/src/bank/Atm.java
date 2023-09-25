package bank;

import java.io.IOException;

public class Atm {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        System.out.println("\nWelcome to the Atm Application");
        menu.mainMenu();
    }
}
