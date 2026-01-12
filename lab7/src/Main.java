
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();

        new DepositThread(acc).start();
        new DepositThread(acc).start();

        new WithdrawThread(acc).start();
        new WithdrawThread(acc).start();

        Kitchen kitchen = new Kitchen();

        for (int i = 0; i < 3; i++) {
            new Cook(kitchen).start();
        }

        for (int i = 0; i < 4; i++) {
            new Waiter(kitchen).start();
        }

        new TimePrinterThread("T1").start();
        new TimePrinterThread("T2").start();
        new TimePrinterThread("T3").start();

        new Thread(new TimePrinterRunnable("R1")).start();
        new Thread(new TimePrinterRunnable("R2")).start();
        new Thread(new TimePrinterRunnable("R3")).start();
    }
}