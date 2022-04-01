package hu.ppke.itk.ma;

import java.util.Random;

public class BankAccount{

    private final String accountName;
    private final String accountNumber;
    private int balance;

    /**
     * The constructor without account name
     */
    public BankAccount(){
        this("unknown");
    }

    /**
     * The constructor with account name
     * @param accountName Name of the account
     */
    public BankAccount(String accountName){
        this.accountName = accountName;
        this.accountNumber =  this.createAccountNumber();
        this.balance = 0;
    }

    /**
     * Get the information of the process what this account made
     * @param process What kind of process
     * @param amount How much money
     * @return The information of the process
     */
    private String getBalanceChangeInfo(String process, Integer amount){
        return "Account: " + this.accountNumber + " (" + this.accountName + ") - " + process + " " + amount + " Ft" ;
    }

    /**
     * Add some many to this account
     * @param amount How much money
     */
    public void addMoney(int amount){
        this.balance += amount;
        System.out.println(this.getBalanceChangeInfo("add", amount));
    }

    /**
     * Transfer money to another account
     *  - It takes away same money from this account
     * @param amount How much money
     * @param fee How much fee
     * @throws InterruptedException Because of sleep
     * @throws NotEnoughBalanceException  Because of the balance is not enough
     */
    public void transferMoney(int amount, int fee) throws InterruptedException, NotEnoughBalanceException {
        Thread.sleep(1000);
        if(this.balance < amount + fee)
            throw new NotEnoughBalanceException(this.accountName, this.balance, amount + fee);
        this.balance -= amount + fee;
        System.out.println(this.getBalanceChangeInfo("transfer", amount));
    }

    /**
     * Receive money from another account
     *  - It adds some money to this account
     * @param amount How much money
     * @throws InterruptedException Because of sleep
     */
    public void receiveMoney(int amount) throws InterruptedException {
        Thread.sleep(1000);
        balance += amount;
        System.out.println(this.getBalanceChangeInfo("receive", amount));
    }

    /**
     * Create an account number for this account
     * @return The new account number
     */
    private String createAccountNumber(){
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 8; j++) str.append(random.nextInt(10));
            if(i != 2) str.append('-');
        }
        return str.toString();
    }
}
