package models;

import java.util.UUID;

import static utils.Utils.GenerateUniqueID;

public class User {
    private String username;
    private String password;
    private String id;
    private BankAccount account;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID().toString();
        this.account = new BankAccount();
    }

    public String toString() {
        return username + " " + password + " " + id;
     }
}
