package Data;

import java.time.LocalDate;

public class Account {
    public int accNumber;
    public LocalDate creationDate;
    public float balance;

    public Account (int accNumber) {
        this.accNumber = accNumber;
        this.creationDate = LocalDate.now();
        this.balance = 0;
    }

    public Account (float balance, int accNumber) {
        this.accNumber = accNumber;
        this.creationDate = LocalDate.now();
        this.balance = balance;
    }

    public String addMoney(float amount) {
        if (amount < 0) {
            return "Sorry you can´t deposit negative number";
        } else {
            this.balance += amount;
            return "You added " + amount + " to your balance. Your new balance is " + this.balance;
        }
    }

    public String withdrawMoney(float amount) {
        if (amount < 0) {
            return "Sorry you can´t withdraw a negative number";
        } else if ( amount < 1000 && this.isWithdrawValid(amount)) {
            this.balance = this.balance - Tax.whithdrawFix - amount;
        } else if (amount >= 1000 && this.isWithdrawValid(amount)) {
            this.balance = this.balance - Tax.whithdrawFix - amount - (amount * Tax.withdrawPorcentage);
        }
        return "You withdrawed " + amount + " from your balance. Your new balance is " + this.balance;
    }

    public String transferMoney(float amount) {
        if (amount < 0) {
            return "Sorry you can´t transfer a negative number";
        } else {
            this.balance = this.balance - Tax.transfer - amount;
        }
        return "You transfered " + amount + " from your balance. Your new balance is " + this.balance;
    }

    private Boolean isWithdrawValid (float amount) {
        return (amount < 1000) ?
                (this.balance - Tax.withdrawPorcentage - amount) < 0 :
                (this.balance - Tax.withdrawPorcentage - amount - (amount * Tax.withdrawPorcentage) < 0);
    }
}
