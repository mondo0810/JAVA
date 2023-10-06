package org.example;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    String username;
    String password;
    Repository repository = new Repository();
    List<User> users = repository.getData();
    Scanner scanner = new Scanner(System.in);

    public void home() {
        Menu.mainMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                createNewUser();
                break;
            default:
                break;
        }
    }

    public Controller() throws FileNotFoundException {

    }

    public void login() {
        boolean checkLogin = false;
        while (!checkLogin) {
            System.out.println("Enter your username");
            username = scanner.nextLine();
            for (int i = 0; i < users.size(); i++) {
                if (username.equals(users.get(i).getUsername())) {
                    System.out.println("Enter your password: ");
                    password = scanner.nextLine();
                    if (password.equals(users.get(i).getPassword())) {
                        loginSuccess();
                        checkLogin = true;
                        break;
                    } else {
                        loginFail();
                        break;
                    }
                }
            }
        }
    }

    public void loginFail() {
        System.out.println("Login failed");
        Menu.loginFail();
    }

    public void loginSuccess() {
        System.out.println("Welcome: " + username);
    }

    public void changePassword() {
    }

    public void changeUsername() {
    }

    public void changeEmail() {
    }

    public void forgotPassword() {
    }

    public void createNewUser() {
    }
}
