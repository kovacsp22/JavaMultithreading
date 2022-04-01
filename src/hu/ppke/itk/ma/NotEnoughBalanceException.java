package hu.ppke.itk.ma;

public class NotEnoughBalanceException extends Exception{
    /**
     * Exception for the case, when the account doesn't have enough balance
     * @param source The source account
     * @param balance The balance of the source account
     * @param needed The cost of the process
     */
    public NotEnoughBalanceException(String source, int balance, int needed){
        super(source + "'s process has failed:" +
                "\n - " + source + "'s balance: " + balance + " Ft" +
                "\n - Process cost: " + needed + " Ft"
        );
    }
}
