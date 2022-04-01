package hu.ppke.itk.ma;

import java.util.Random;

public class Main {

    /**
     * Self transaction test
     *
     * Thread 1: first -> first (100)
     * Thread 2: first -> first (200)
     * Thread 3: first -> first (300)
     * Thread 4: first -> first (400)
     * Thread 5: first -> first (500)
     * Thread 6: first -> first (600)
     * Thread 7: first -> first (700)
     * Thread 8: first -> first (800)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test1(BankAccount[] accounts){
        System.out.println("Test 1: Self transactions (x8)");
        for(int i = 0; i < 8; i++){
            BankAccount source = accounts[0];
            BankAccount target = accounts[0];
            int amount = (i + 1) * 100;

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * Back and forth transaction test
     *
     * Thread 1: first -> second (1.000)
     * Thread 2: second -> first (1.000)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test2(BankAccount[] accounts){
        System.out.println("Test 2: Back and forth transactions");
        for(int i = 0; i < 2; i++){
            BankAccount source = accounts[i];
            BankAccount target = accounts[(i + 1) % 2];
            int amount = 1000;

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * Circle transaction test
     *
     * Thread 1: first -> second (1.000)
     * Thread 2: second -> third (1.000)
     * Thread 3: third -> fourth (1.000)
     * Thread 4: fourth -> fifth (1.000)
     * Thread 5: fifth -> first (1.000)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test3(BankAccount[] accounts){
        System.out.println("Test 2: Circle transaction");
        for(int i = 0; i < accounts.length; i++){
            BankAccount source = accounts[i];
            BankAccount target = accounts[(i + 1) % accounts.length];
            int amount = 1000;

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * n:1 transaction test
     *
     * Thread 1: first -> fifth (1.000)
     * Thread 2: second -> fifth (1.000)
     * Thread 3: third -> fifth (1.000)
     * Thread 4: fourth -> fifth (1.000)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test4(BankAccount[] accounts){
        System.out.println("Test 4: All money to Edward");
        for(int i = 0; i < accounts.length-1; i++){
            BankAccount source = accounts[i];
            BankAccount target = accounts[accounts.length-1];
            int amount = 1000;

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * 1:n transaction test
     *
     * Thread 1: first -> second (100)
     * Thread 2: first -> third (100)
     * Thread 3: first -> fourth (100)
     * Thread 4: first -> fifth (100)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test5(BankAccount[] accounts){
        System.out.println("Test 5: Alice the dealer");
        for(int i = 1; i < accounts.length-1; i++){
            BankAccount source = accounts[0];
            BankAccount target = accounts[i];
            int amount = 100;

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * Order test
     *
     * Thread 1: first -> third (3.000)
     * Thread 2: second -> third (3.000)
     * Thread 3: third -> fourth (10.000)
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void test6(BankAccount[] accounts){
        System.out.println("Test 6: Is it normal?");

        (new BankTransfer(accounts[0], accounts[2], 3000)).start();
        (new BankTransfer(accounts[1], accounts[2], 3000)).start();
        (new BankTransfer(accounts[2], accounts[3], 10000)).start();
    }

    /**
     * Random transactions test
     *
     * Thread 1: first-fifth -> first-fifth (0-2.000)
     * Thread 2: first-fifth -> first-fifth (0-2.000)
     * Thread 3: first-fifth -> first-fifth (0-2.000)
     * Thread ...
     * @param accounts Bank accounts
     */
    @SuppressWarnings("unused")
    public static void randomTransactionsTest(BankAccount[] accounts){
        System.out.println("Random Test: Random transactions");
        Random random = new Random();
        for(int i = 0; i < random.nextInt(99)+1; i++){
            BankAccount source = accounts[random.nextInt(accounts.length)];
            BankAccount target = accounts[random.nextInt(accounts.length)];
            int amount = random.nextInt(2000);

            (new BankTransfer(source, target, amount)).start();
        }
    }

    /**
     * Withdrawal test
     *
     * Thread 1: first + ATM (...)
     * @param accounts Bank accounts
     * @param atm ATM
     * @param amount How much money
     */
    @SuppressWarnings("unused")
    public static void test7(BankAccount[] accounts, ATM atm, int amount){
        System.out.println("Test 7: ATM withdrawal");

        CashWithdrawal cashWithdrawal = new CashWithdrawal(accounts[0], atm, amount);
        cashWithdrawal.start();
    }

    /**
     * Deposit test
     *
     * Thread 1: first + ATM (1.000)
     * @param accounts Bank accounts
     * @param atm ATM
     */
    @SuppressWarnings("unused")
    public static void test8(BankAccount[] accounts, ATM atm){
        System.out.println("Test 8: ATM cash deposit");

        CashDeposit cashDeposit = new CashDeposit(accounts[0], atm, 1000);
        cashDeposit.start();
    }

    /**
     * Deposit and withdrawal test
     *
     * Thread 1: second + ATM (-200)
     * Thread 2: third + ATM (100)
     * Thread 3: third + ATM (-200)
     * Thread 4: fourth + ATM (100)
     * Thread 5: fourth + ATM (-200)
     * Thread 6: fifth + ATM (100)
     * @param accounts Bank accounts
     * @param atm ATM
     */
    @SuppressWarnings("unused")
    public static void test9(BankAccount[] accounts, ATM atm){
        System.out.println("Test 9: ATM cash deposit and withdrawal");
        for(int i = 1; i < 3; i++){

            CashWithdrawal cashWithdrawal = new CashWithdrawal(accounts[i], atm, 200);
            cashWithdrawal.start();
            CashDeposit cashDeposit = new CashDeposit(accounts[(i + 1)], atm, 100);
            cashDeposit.start();
        }
    }

    /**
     * The final test
     *
     * Thread 1: first -> second (1.000)
     * Thread 2: second + ATM-1 (2.000)
     * Thread 3: first + ATM-2 (1.000)
     * Thread 4: first -> third (100)
     * Thread 5: first -> fourth (100)
     * Thread 6: first -> fifth (100)
     * Thread 7: fourth -> fifth (800)
     * Thread 8: fourth + ATM-1 (2.000)
     * Thread 9: fifth + ATM-2 (2.000)
     * Thread 10: fifth + ATM-1 (-1.000)
     * Thread 11: third + ATM-1 (-1.000)
     * Thread 12: third -> third (150)
     * @param accounts Bank accounts
     * @param atm First ATM
     * @param atm2 Second ATM
     */
    @SuppressWarnings("unused")
    public static void test10(BankAccount[] accounts, ATM atm, ATM atm2){
        System.out.println("Test 10: The final test");

        (new BankTransfer(accounts[0], accounts[1], 1000)).start();
        (new CashDeposit(accounts[1], atm, 2000)).start();
        (new CashWithdrawal(accounts[0], atm2, 1000)).start();

        for(int i = 2; i < accounts.length-1; i++){
            BankAccount source = accounts[0];
            BankAccount target = accounts[i];
            int amount = 100;
            (new BankTransfer(source, target, amount)).start();
        }

        (new BankTransfer(accounts[3], accounts[4], 800)).start();
        (new CashDeposit(accounts[3], atm, 200)).start();
        (new CashDeposit(accounts[4], atm2, 2000)).start();
        (new CashDeposit(accounts[4], atm, 1000)).start();
        (new CashWithdrawal(accounts[2], atm, 1000)).start();
        (new BankTransfer(accounts[2], accounts[2], 150)).start();
    }



    public static void main(String[] args){

        BankAccount[] accounts = {
                new BankAccount("Alice"),
                new BankAccount("Bob"),
                new BankAccount(),
                new BankAccount(),
                new BankAccount("Edward")
        };

        int startingMoney = 5000;

        System.out.println("\nAdd some starting money to all account:");
        for(BankAccount account : accounts) account.addMoney(startingMoney);
        System.out.println();

        ///test1(accounts);
        ///test2(accounts);
        ///test3(accounts);
        ///test4(accounts);
        ///test5(accounts);
        ///test6(accounts);
        ///randomTransactionsTest(accounts);

        ATM emptyATM = new ATM();
        ATM busyATM = new ATM(3452900);

        ///test7(accounts, emptyATM, 1000);
        ///test7(accounts, busyATM, 7000);
        ///test7(accounts, busyATM, 5000);
        ///test8(accounts, emptyATM);
        ///test9(accounts, busyATM);
        test10(accounts, emptyATM, busyATM);

    }
}
