package MockData;

import Data.Bank;
import Data.SavingAccount;
import Data.User;

public class DataMockInit {
    public static Bank createDummyData(Bank bank){
        User user1 = new User("wilantury", "12345678998");
        User user2 = new User("Carlos", "12345678998");
        User user3 = new User("Rojas", "12345678998");
        SavingAccount savingAccount1 = new SavingAccount("298837827387327", 0);
        SavingAccount savingAccount2 = new SavingAccount("298837827387328", 400);
        SavingAccount savingAccount3 = new SavingAccount("298837827387329", 300);
        savingAccount1.setOwner(user1);
        savingAccount2.setOwner(user2);
        savingAccount3.setOwner(user3);
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        bank.addSavingAccount(savingAccount1);
        bank.addSavingAccount(savingAccount2);
        bank.addSavingAccount(savingAccount3);
        return bank;
    }
}
