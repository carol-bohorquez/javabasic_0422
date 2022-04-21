package controllers;

import data.Data;
import models.SavingsAccount;
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

    private void withdrawMoney() {
        Optional<User> optionalUser = authenticateUser();
        if (optionalUser.isEmpty()) {
            views.print("Authentication failed");
            return;
        }
        User user = optionalUser.get();
        SavingsAccount account = user.getBankAccount();
        double amount = views.getAmount("Enter the amount to withdraw");
        String status = account.withdrawFromBalance(amount);
        views.printTransactionStatus(status, amount);
    }

    private void depositMoney() {
        Optional<SavingsAccount> optionalAccount = data.bank.getAccountWithId(views.getAccountId());
        if (optionalAccount.isEmpty()) {
            views.print("Account does not exist");
            return;
        }
        SavingsAccount account = optionalAccount.get();
        double amount = views.getAmount("Enter the amount to deposit");
        String status = account.depositMoney(amount);
        views.printTransactionStatus(status, amount);
    }

    private void transferMoney() {
        Optional<User> optionalUser = authenticateUser();
        if (optionalUser.isEmpty()) {
            views.print("Authentication failed");
            return;
        }
        User user = optionalUser.get();

        Optional<SavingsAccount> optionalAccount = data.bank.getAccountWithId(views.getAccountId());
        if (optionalAccount.isEmpty()) {
            views.print("Account does not exist");
            return;
        }
        SavingsAccount accountToDeposit = optionalAccount.get();
        SavingsAccount userAccount = user.getBankAccount();
        if (userAccount == accountToDeposit) {
            views.print("You cannot deposit to your own account");
            return;
        }

        double amount = views.getAmount("What is the amount to transfer?");

        if (userAccount.isValidTransfer(amount)) {
            userAccount.transfers(amount);
            accountToDeposit.depositMoney(amount);
            views.print("Transfer successful");
        } else {
            views.print("Transfer is not valid");
        }
    }
}
