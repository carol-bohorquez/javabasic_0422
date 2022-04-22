package Runner;

import Data.Bank;
import Data.SavingAccount;
import Data.User;
import Helper.Utils;
import MockData.DataMockInit;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String option="";
        Bank bank;
        bank = Utils.createBankView();
        bank = DataMockInit.createDummyData(bank);
        System.out.println("Bank name: " + bank.getName());
        while(!option.equals("e")){
            option = Utils.getOptionsAdminMenu(bank.getName());
            switch (option){
                case "1":
                    manageOption1(bank);
                    break;
                case "2":
                    manageOption2(bank);
                    break;
                case "3":
                    Utils.listUsersView(bank);
                    break;
                case "4":
                    Utils.listBankAccountsView(bank);
                    break;
                case "5":
                    manageOption5(bank);
                    break;
                case "6":
                    manageOption6(bank);
                    break;
                case "7":
                    manageOption7(bank);
                    break;
            }
        }
    }

    private static void manageOption7(Bank bank) {
        if(bank.getAccounts().size() > 0){
            Utils.performGetMoney(bank);
        }else {
            System.out.println("There aren't accounts yet");
        }
    }

    private static void manageOption6(Bank bank) {
        if(bank.getAccounts().size() > 0){
            Utils.performAddMoney(bank);
        }else {
            System.out.println("There aren't accounts yet");
        }
    }

    private static void manageOption5(Bank bank) {
        if(bank.getAccounts().size() > 1){
            Utils.performTransferView(bank);
        }else {
            System.out.println("There isn't enough accounts to perform a transfer");
        }
    }

    private static void manageOption2(Bank bank) {
        ArrayList<User> users;
        User user;
        try{
            users = bank.getUsers();
            if(users.size() > 0){
                SavingAccount savingAccount = Utils.createAccountView();
                bank.addSavingAccount(savingAccount);
                user = Utils.addOwnerToAccountView(users);
                savingAccount.setOwner(user);
            }else {
                System.out.println("There isn't users yet, please add an user first");
            }
        }catch (Exception e){
            System.out.println("There was an error creating the Account :(");
        }
    }

    private static void manageOption1(Bank bank) {
        User user = Utils.createUserView();
        bank.addUser(user);
    }
}

