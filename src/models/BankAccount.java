package models;

import java.util.Date;

public class BankAccount {
    private static int bankAccountsCounter = 0;
    private final int id;
    private final Date openingDate;
    private double balance;

    public BankAccount() {
        bankAccountsCounter += 1;
        this.id = bankAccountsCounter;
        this.openingDate = new Date();
        this.balance = 0;
    }

    public String toString() {
        return "id is: " + this.id + "\nopeningDate is: " + this.openingDate + "\nthe balance in the account is : " + this.balance;
    }

    public String withdrawFromBalance(double amount) {
        if (amount < 100) {
            return "Minimum amount to withdraw is $100";
        }
        double amountWithTaxes = amount + this.calculateTaxes(amount);
        if (amountWithTaxes < this.balance) {
            this.balance -= amountWithTaxes;
            return "Transaction approved";
        }
        return "You do not have enough balance to make this withdrawal";
    }

    private double calculateTaxes(double amount) {
        if (amount > 1000) {
            return 200 + (amount * 0.15);
        }
        return 200;
    }

    public int getId() {
        return this.id;
    }

    public String depositMoney(double amount) {
        if (amount < 10) {
            return "Minimum amount to deposit is: $10";
        }
        this.balance += amount;
        return "Transaction approved";
    }
}
