package org.example;

import java.io.IOException;
import java.util.Scanner;

public class LoginConsole {
    LoginController loginController = new LoginController();
    Users user = new Users();
    private final Scanner sc;

    public LoginConsole() throws IOException {
        this.sc = new Scanner(System.in);
    }

    private int menu() {
        System.out.println("Menu");
        System.out.println("1. Login with statement");
        System.out.println("2. Login with preparedStatement");
        System.out.println("0. Exit");
        int choice = readInt(0, 3);
        return choice;
    }

    public void start() {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    loginStatement();
                    break;
                case 2:
                    loginPrepareStatement();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return choice;
}

    private void loginStatement() {
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        user.setUsername(username);
        user.setPassword(password);
        String result = loginController.loginStatementController(user);
        System.out.println(result);
    }

    private void loginPrepareStatement() {
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        user.setUsername(username);
        user.setPassword(password);
        String result = loginController.loginPreparedStatementController(user);
        System.out.println(result);
    }

}
