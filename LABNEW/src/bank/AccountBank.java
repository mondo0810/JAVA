package bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountBank {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance;
    private double savingBalance;

    Scanner input  = new Scanner(System.in);
    DecimalFormat  moneyFormat   = new DecimalFormat("'$'###,##0.00");

    public AccountBank(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public AccountBank(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public  void  getCheckingWithdrawInput(){
        System.out.println("Current account balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter the amount to withdraw from the checking account: $");
        double amount = input.nextDouble();

        if (amount > 0 && amount <= checkingBalance) {
            checkingBalance -= amount;
            System.out.println("Withdrawn $" + moneyFormat.format(amount) + " from the checking account.");
            System.out.println("Updated Checking Account Balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    };
    public  void  getCheckingDepositInput(){
        System.out.println("Current account balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter the amount to deposit into the checking account: $");
        try {
            double amount = input.nextDouble();
            if (amount > 0) {
                checkingBalance += amount;
                System.out.println("Deposited $" + moneyFormat.format(amount) + " into the checking account.");
                System.out.println("Updated Checking Account Balance: " + moneyFormat.format(checkingBalance));
            } else {
                System.out.println("Invalid amount.");
                input.next();
            }
        }
        catch (InputMismatchException e){
            System.out.println("Enter number");
        }

    };
    public void getSavingWithdrawInput() {
        System.out.print("Enter the amount to withdraw from the savings account: $");
        double amount = input.nextDouble();

        if (amount > 0 && amount <= savingBalance) {
            savingBalance -= amount;
            System.out.println("Withdrawn $" + moneyFormat.format(amount) + " from the savings account.");
            System.out.println("Updated Savings Account Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    public void getSavingDepositInput() {
        System.out.print("Enter the amount to deposit into the savings account: $");
        double amount = input.nextDouble();

        if (amount > 0) {
            savingBalance += amount;
            System.out.println("Deposited $" + moneyFormat.format(amount) + " into the savings account.");
            System.out.println("Updated Savings Account Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void getTransferInput(AccountBank recipientAccount) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the amount to transfer: $");
        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > checkingBalance) {
            System.out.println("Insufficient funds.");
        } else {
            checkingBalance -= amount;
            recipientAccount.checkingBalance += amount;

            System.out.println("Transferred $" + moneyFormat.format(amount) + " to customer "
                     + " successfully.");
            System.out.println("Updated Your Checking Account Balance: " + moneyFormat.format(checkingBalance));
        }
    }




    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }
}
