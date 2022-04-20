package models;

import java.util.UUID;

import static utils.Utils.GenerateUniqueID;

public class User {
    String username;
    private String password;
    private String id;
    BankAccount account;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID().toString();
        this.account = new BankAccount();
    }

    public String getBankAccountData() {
        return this.account.toString();
    }

    public String toString() {
        return "Username: " + username + " with ID: " + id + "\nBank account info: " + getBankAccountData();
     }

     public boolean isValidPassword(String password) {
        return password.equals(this.password);
     }

    public BankAccount getBankAccount() {
        return this.account;
    }
}
