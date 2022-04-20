package models;

import java.util.Date;

public class BankAccount {
    private static int bankAccountsCounter = 0;
    private final int id;
    private final Date openingDate;
    private final double balance;

    public BankAccount() {
        bankAccountsCounter += 1;
        this.id = bankAccountsCounter;
        this.openingDate = new Date();
        this.balance = 0;
    }

    public String toString() {
        return "id is: " + this.id + "\nopeningDate is: " + this.openingDate + "\nthe balance in the account is : " + this.balance;
    }
}
