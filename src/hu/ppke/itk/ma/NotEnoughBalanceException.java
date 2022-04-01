package hu.ppke.itk.ma;

public class NotEnoughBalanceException extends Exception{
    /**
     * Exception for the case, when the account doesn't have enough balance for the transaction
     * @param source The source account
     * @param balance The balance of the source account
     * @param needed The cost of the transaction
     */
    public NotEnoughBalanceException(String source, int balance, int needed){
        super(source + "'s transaction has failed:" +
                "\n - " + source + "'s balance: " + balance + " Ft" +
                "\n - Transaction cost: " + needed + " Ft"
        );
    }
}
