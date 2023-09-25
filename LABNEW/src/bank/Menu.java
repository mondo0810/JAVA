package bank;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Menu {
    // format input data
    Scanner mInput = new Scanner(System.in) ;
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, AccountBank> data = new HashMap<Integer, AccountBank>();

    public void getLogin() throws IOException {
        int cusNumber = 0;
        int pinNumber = 0;
        boolean flag = false;

        while (!flag) {
            try {
                System.out.println("Enter cus number");
                cusNumber = mInput.nextInt();
                System.out.println("Enter pin number");
                pinNumber = mInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    AccountBank acc = (AccountBank) pair.getValue();
                    if (cusNumber == acc.getCustomerNumber() && pinNumber == acc.getPinNumber()) {
                        getAccountType(acc);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("\n Wrong customer number or pin number");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Enter number");
                mInput.next();
            }
        }

    }

    public void createAccount() throws IOException {
        boolean flag = false;
        int cNo = 0;
        while (!flag) {
            try {
                System.out.println("Enter customer number");
                cNo = mInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    flag = true;
                }
                if (!flag) {
                    System.out.println("This customer number is already");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid customer number");
                mInput.next();
            }
        }
        System.out.println("\n Enter PIN:");
        int pin = mInput.nextInt();
        data.put(cNo, new AccountBank(cNo, pin));
        System.out.println("\n Your new account has been created");
        System.out.println("\n Redirect to login ....");
        getLogin();

    }

    public void getAccountType(AccountBank acc) {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("Choice your account type");
                System.out.println("\n1. Checking Account");
                System.out.println("\n2. Saving Account");
                System.out.println("\n3: Exit");
                int choice = mInput.nextInt();
                switch (choice) {
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        flag = true;
                        break;
                    default:
                        System.out.println("\nInvalid choice");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void getSaving(AccountBank acc) {
    }

    public void getChecking(AccountBank acc) {
        boolean flag = false;
        while (!flag) {
            System.out.println("\n Checking account");
            System.out.println("\n1. View Balance");
            System.out.println("\n2. Withdraw");
            System.out.println("\n3. Deposit");
            System.out.println("\n4. Transfer");
            System.out.println("\n5. Exit");
            int choice = mInput.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n Checking Account Balance " + moneyFormat.format(acc.getCheckingBalance()));
                    break;
                case 2:
                    acc.getCheckingWithdrawInput();
                    break;
                case 3:
                    acc.getCheckingDepositInput();
                    break;
                case 4:
                    System.out.print("Enter customer number: ");
                    int customerNumber = mInput.nextInt();
                    Iterator it = data.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        AccountBank recipientAccount = (AccountBank) pair.getValue();

                        if (customerNumber == recipientAccount.getCustomerNumber()) {
                            flag = true;
                            recipientAccount.getTransferInput(recipientAccount);
                            break;
                        }
                    }

                    if (!flag) {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 5:
                    flag =true;
                    break;
                default:
                    System.out.println("\n Invalid choice");
                    mInput.next();
            }
        }
    }

    public void mainMenu() throws IOException {
        data.put(1, new AccountBank(123, 123, 1000, 3000));
        data.put(2, new AccountBank(321, 1000, 1000, 3000));

        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("1-Login");
                System.out.println("2-Create Account");
                System.out.println("\nEnter your choice");
                int choice = mInput.nextInt();
                switch (choice) {
                    case 1:
                        getLogin();
                        flag = true;
                        break;
                    case 2:
                        createAccount();
                        flag = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
