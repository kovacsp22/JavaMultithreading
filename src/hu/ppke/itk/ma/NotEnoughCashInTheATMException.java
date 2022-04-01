package hu.ppke.itk.ma;

public class NotEnoughCashInTheATMException extends Exception {
    /**
     * Exception for the case, when the ATM doesn't have enough cash inside
     * @param account The account name
     * @param cash Cash in the ATM
     */
    public NotEnoughCashInTheATMException(String account, int cash){
        super(account + "'s cash withdrawal has failed:\n - Cash in the ATM: " + cash );
    }
}
