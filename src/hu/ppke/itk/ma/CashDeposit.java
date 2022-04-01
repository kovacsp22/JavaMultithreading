package hu.ppke.itk.ma;

public class CashDeposit extends Thread{

    private final BankAccount account;
    private final ATM atm;
    private final int amount;

    /**
     * The Constructor of the CashDeposit
     * @param account The account who deposit money
     * @param atm The ATM
     * @param amount How much money
     */
    public CashDeposit(BankAccount account, ATM atm, int amount){
        this.account = account;
        this.atm = atm;
        this.amount = amount;
    }

    /**
     * Run the cash deposit process
     *  - Enter money to the ATM
     *  - Give that much money to the Account
     */
    @Override
    public void run() {
        synchronized (this.atm){
            try {
                this.atm.enterCash(this.amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (this.account){
            try {
                this.account.cashDeposit(this.amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
