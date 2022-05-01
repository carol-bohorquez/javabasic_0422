package Data;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String password;
    private static int counterAccNumber = 0;
    public List<Account> accounts = new ArrayList<>();

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String createAccount() {
        Account newAccount = new Account(counterAccNumber);
        counterAccNumber++;
        this.accounts.add(newAccount);
        return "You have created an Account succesfully";
    }

    public String createAccountWithAmount(float amount) {
        Account newAccount = new Account(amount, counterAccNumber);
        this.accounts.add(newAccount);
        return "You have created an Account succesfully";
    }

    public String withdrawMoney(float amount, int accountNumber) {
        return accounts.get(accountNumber).withdrawMoney(amount);
    }

    public String depositMoney(float amount, int accountNumber) {
        return accounts.get(accountNumber).addMoney(amount);
    }


    public String transferMoney(float amount, int incomingAccount, int transferAcc) {
        return accounts.get(incomingAccount).addMoney(amount) + accounts.get(transferAcc).transferMoney(amount);
    }
}
