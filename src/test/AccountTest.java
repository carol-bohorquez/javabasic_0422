package test;

import Data.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void constructorShouldInitializeFields(){
        //Arrange
        SavingAccount savingAccount = new SavingAccount("12345678", 20000);
        //Act
        String accountNumber = savingAccount.getAccountNumber();
        float balance = savingAccount.getBalance();
        //assert
        assertEquals("12345678", accountNumber);
        assertEquals(20000, balance, 0.1);
    }
}
