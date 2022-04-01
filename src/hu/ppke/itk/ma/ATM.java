package hu.ppke.itk.ma;

public class ATM {

    int cash;

    /**
     * The constructor of the ATM
     */
    public ATM(){
        this(0);
    }

    /**
     * The constructor of the ATM with cash param
     * @param cash How much cash
     */
    public ATM(int cash){
        this.cash = cash;
    }

    /**
     * When someone enters money to the ATM, the cash inside te machine will increase
     * @param cash How much cash
     * @throws InterruptedException Because of sleep
     */
    public void enterCash(int cash) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Cash in the ATM: " + this.cash + "+" + cash + " = " + (this.cash + cash));
        this.cash += cash;

    }

    /**
     * When someone takes out cash from the ATM, the cash inside te machine will decrease
     * @param cash How much cash
     * @param accountName Who takes out the cash
     * @throws InterruptedException Because of sleep
     * @throws NotEnoughCashInTheATMException Because the ATM doesn't have enough cash
     */
    public void takeCash(int cash, String accountName) throws InterruptedException, NotEnoughCashInTheATMException {
        Thread.sleep(2000);
        if (this.cash<cash) throw new NotEnoughCashInTheATMException(accountName, this.cash);
        System.out.println("Cash in the ATM: " + this.cash + "-" + cash + " = " + (this.cash - cash));
        this.cash -= cash;
    }
}
