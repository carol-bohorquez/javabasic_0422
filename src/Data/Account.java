package Data;

import java.time.Instant;


public class Account {
    private String accountNumber;
    private float balance;
    private Instant openDate;
    private User owner;

    public Account(String accountNumber, float balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openDate = Instant.now();
    }

    public Account(String accountNumber){
        this.balance = 0;
        this.accountNumber = accountNumber;
        this.openDate = Instant.now();
    }

    //Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Instant getOpenDate() {
        return openDate;
    }

    public void addMoney(float amount){
        this.balance += amount;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return this.owner;
    }
}
