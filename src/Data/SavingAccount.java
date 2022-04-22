package Data;

public class SavingAccount extends Account{
    private static float withdrawTaxFix = 200;
    private static float withdrawTaxPercentage = 0.15f;

    private static float transferTaxFix = 100;
    private static float withdrawTaxLimit = 1000;

    public SavingAccount(String accountNumber, float balance){
        super(accountNumber,balance);
    }

    public SavingAccount(String accountNumber, float balance, float withdrawTaxFix, float withdrawTaxPercentage, float transferTaxFix){
        super(accountNumber,balance);
        this.transferTaxFix = transferTaxFix;
        this.withdrawTaxFix = withdrawTaxFix;
        this.withdrawTaxPercentage = withdrawTaxPercentage;
    }

    public boolean checkFundsTransfer(float amountToRetrieve){
        float totalTax = amountToRetrieve + this.transferTaxFix;
        return this.getBalance() >= totalTax ? true : false;
    }

    public void withdrawBalanceTransfer(float amount){
        this.setBalance(this.getBalance() - amount -this.transferTaxFix);
    }

    public boolean checkFundsWithdraw(float amount) {
        float totalWithdraw;
        if(amount >= this.withdrawTaxLimit){
             totalWithdraw = amount + withdrawTaxFix + amount*withdrawTaxPercentage;
        }else{
            totalWithdraw = amount + withdrawTaxFix;
        }
        return this.getBalance() >= totalWithdraw;
    }

    public void withdrawBalance(float amount) {
        float totalWithdraw;
        if(amount >= this.withdrawTaxLimit){
            totalWithdraw = amount + withdrawTaxFix + amount*withdrawTaxPercentage;
        }else{
            totalWithdraw = amount + withdrawTaxFix;
        }
        this.setBalance(this.getBalance() - totalWithdraw);
    }
}
