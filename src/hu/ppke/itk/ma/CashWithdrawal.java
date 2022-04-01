package hu.ppke.itk.ma;

public class CashWithdrawal extends Thread{

    private final BankAccount account;
    private final ATM atm;
    private final int amount;

    /**
     * The Constructor of the CashWithdrawal
     * @param account The account who withdrawal money
     * @param atm The ATM
     * @param amount How much money
     */
    public CashWithdrawal(BankAccount account, ATM atm, int amount){
        this.account = account;
        this.atm = atm;
        this.amount = amount;
    }

    /**
     * Run the cash withdrawal process
     *  - Take money from the ATM
     *  - Take away that much money from the Account
     */
    @Override
    public void run(){
        try{
            synchronized (this.atm){
                if(this.account.getBalance() >= this.amount) {
                    try {
                        this.atm.takeCash(this.amount, this.account.getAccountName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (this.account){
                try {
                    this.account.cashWithdrawal(this.amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NotEnoughCashInTheATMException | NotEnoughBalanceException e){
            System.err.println(e.getMessage());
        }
    }
}
