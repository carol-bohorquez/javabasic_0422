package views;

import models.User;

import java.util.Observable;
import java.util.Scanner;

public class Views  extends Observable {
    boolean running = true;
    Scanner scan = new Scanner(System.in);
    String input;

    public void run() {
        starting();
        do {
            printMenu();
            setInput(scan.next());
        } while(running);
    }

    private void setInput(String next) {
        this.input = next;
        setChanged();
        notifyObservers(input);
    }

    private void printMenu() {
        System.out.println("---- Menu ----");
        System.out.println("1. List all users");
        System.out.println("2. Create a user");
        System.out.println("3. Withdraw money from account");
        System.out.println("4. Add money to account");
        System.out.println("5. Transfer money from accounts");
        System.out.println("Q/q to exit the program");
        System.out.println("--------------");
    }

    public void kill() {
        System.out.println("Come back soon!");
        running = false;
    }

    private void starting() {
        System.out.println("Program initiating");
        System.out.println("Welcome to Globant Bank");
        System.out.println("Through this console you will be able to manage all of the users and their accounts");
    }

    public String getUserName() {
        System.out.println("Please enter the username");
        return scan.next();
    }

    public String getPasswordFirsTime() {
        boolean runUntilMatch = true;
        String password;
        do {
            System.out.println("Please enter the password");
            password = scan.next();
            System.out.println("Please confirm the password");
            String confirmation = scan.next();
            if (password.equals(confirmation)) { runUntilMatch = false; } else {
                System.out.println("Passwords did not match");
                System.out.println("Try again");
            }
        } while (runUntilMatch);
        return password;
    }

    public void createdUser(User user) {
        System.out.println("User has been created correctly!");
        printUser(user);
    }

    public void printUser(User user) {
        System.out.println(user.toString());
        System.out.println("--------------");
    }

    public void print(String str) {
        System.out.println(str);
    }

    public String getPassword() {
        System.out.println("Please enter the password");
        return scan.next();
    }

    public double getAmount(String withMessage) {
        double amount = 0;
        boolean isNotValid = true;

        do {
            try {
                System.out.println(withMessage);
                scan.nextLine();
                amount = scan.nextDouble();
                isNotValid = false;
            } catch (Exception e) {
                print("Must be a valid number (double)");
            }
        } while (isNotValid);

        return amount;
    }

    public void printTransactionStatus(String status, double amount) {
        System.out.println("Your transaction for: " + amount + " returned: ");
        System.out.println(status);
    }

    public int getAccountId() {
        System.out.println("Enter the id of the account");
        try {
            return scan.nextInt();
        } catch (Exception e) {
            System.out.println("It must be a valid account id");
        }
        return 0;
    }

}
