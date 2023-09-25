package bank;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    // format input data
    Scanner mInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, AccountBank> data = new HashMap<Integer, AccountBank>();

    public void getLogin() throws IOException {
        int cusNumber = 0;
        int pinNumber = 0;
        boolean flag = false;

        while (!flag) {
            try {
                System.out.println("hello");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Enter cus number");
            cusNumber = mInput.nextInt();
            System.out.println("Enter pin number");
            pinNumber = mInput.nextInt();
            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                AccountBank acc = (AccountBank) pair.getValue();
                if (data.containsKey(cusNumber) && data.containsKey(pinNumber)) {
                    flag = true;
                    break;
                }


            }
        }

    }

    public void createAccount() throws IOException {
    }

    public void getAccountType(AccountBank acc) {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("Choice your account type");
                System.out.println("\n1. Checking Account");
                System.out.println("\n2. Saving Account");
                System.out.println("\n3: Exit");
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
    }

    public void mainMenu() throws IOException {
        data.put(1, new AccountBank(11111, 1000, 1000, 3000);
        data.put(2, new AccountBank(11111, 1000, 1000, 3000);
    }
}
