package views;

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
        System.out.println("1. Create a user");
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
    public String getPassword() {
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
}
