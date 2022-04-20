package controllers;

import data.Data;
import models.BankAccount;
import models.User;
import views.Views;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class Controller implements Observer {
    private final Data data;
    private final Views views;

    public Controller(Data data, Views views) {
        this.data = data;
        this.views = views;
    }

    @Override
    public void update(Observable o, Object arg) {
        handleInput((String) arg);
    }

    private void handleInput(String input) {
        switch (input) {
            case "1" -> { listUsers(); }
            case "2" -> { createUser(); }
            case "3" -> { withdrawMoney(); }
            case "4" -> { depositMoney(); }
            case "5" -> { transferMoney(); }
            case "q", "Q" -> { views.kill(); }
        }
    }

    private void transferMoney() {
        Optional<User> optionalUser = authenticateUser();
        if (optionalUser.isEmpty()) {
            views.print("Authentication failed");
            return;
        }
        User user = optionalUser.get();
        Optional<BankAccount> optionalAccount = data.bank.getAccountWithId(views.getAccountId());
        if (optionalAccount.isEmpty()) {
            views.print("Account does not exist");
            return;
        }
        BankAccount userAccount = user.getBankAccount();
        BankAccount accountToDeposit = optionalAccount.get();
    }

    private void depositMoney() {
        Optional<BankAccount> optionalAccount = data.bank.getAccountWithId(views.getAccountId());
        if (optionalAccount.isEmpty()) {
            views.print("Account does not exist");
            return;
        }
        BankAccount account = optionalAccount.get();
        double amount = views.getAmount("Enter the amount to deposit");
        String status = account.depositMoney(amount);
        views.printTransactionStatus(status, amount);
    }

    private void withdrawMoney() {
        Optional<User> optionalUser = authenticateUser();
        if (optionalUser.isEmpty()) {
            views.print("Authentication failed");
            return;
        }
        User user = optionalUser.get();
        BankAccount account = user.getBankAccount();
        double amount = views.getAmount("Enter the amount to withdraw");
        String status = account.withdrawFromBalance(amount);
        views.printTransactionStatus(status, amount);
    }

    private Optional<User> authenticateUser() {
        String username = views.getUserName();
        String password;
        Optional<User> optionalUser = data.bank.getUserByUsername(username);
        if (optionalUser.isPresent()) {
            password = views.getPassword();
            User user = optionalUser.get();
            if (user.isValidPassword(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    private void listUsers() {
        if (data.bank.getUsers().size() == 0) {
            views.print("No users found");
        } else {
            for (User user : data.bank.getUsers()) {
                views.printUser(user);
            }
        }
    }

    private void createUser() {
        User user = new User(views.getUserName(), views.getPasswordFirsTime());
        data.bank.addUser(user);
        views.createdUser(user);
    }
}
