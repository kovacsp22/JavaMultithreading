package hu.ppke.itk.ma;

public class BankTransfer extends Thread{

    private final BankAccount sourceAccount;
    private final BankAccount targetAccount;
    private final int amount;
    private final int fee;

    /**
     * The Constructor of the BankTransfer class
     * @param sourceAccount The source account
     * @param targetAccount The target account
     * @param amount How much money
     */
    public BankTransfer(BankAccount sourceAccount, BankAccount targetAccount, int amount){
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.fee = amount/100;
    }

    /**
     * Run the transfer process
     * - It takes away some money from the source account and add to target account
     */
    @Override
    public void run() {
        try {
            synchronized (this.sourceAccount){
                try {
                    this.sourceAccount.transferMoney(this.amount, this.fee);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (this.targetAccount){
                try {
                    this.targetAccount.receiveMoney(this.amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NotEnoughBalanceException e){
            System.err.println(e.getMessage());
        }
    }
}
