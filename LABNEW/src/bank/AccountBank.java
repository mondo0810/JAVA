package bank;

import java.text.DecimalFormat;
import java.util.Scanner;

public class AccountBank {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance;
    private double savingBalance;

    Scanner input    = new Scanner(System.in);
    DecimalFormat  moneyFormat   = new DecimalFormat("'$'###,##0.00");

    public AccountBank(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public  void  getCheckingWithdrawInput(){};
    public  void  getCheckingDepositInput(){};
    public  void  getSavingWithdrawInput(){};
    public void getSavingDepositInput(){};
    public  void getTransderInput(){};

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
