class DepositThread extends Thread {
    private BankAccount account;
    public DepositThread(BankAccount acc) { this.account = acc; }

    public void run() {
        for (int i=0; i<5; i++) {
            account.deposit(100);
        }
    }
}

class WithdrawThread extends Thread {
    private BankAccount account;
    public WithdrawThread(BankAccount acc) { this.account = acc; }

    public void run() {
        for (int i=0; i<5; i++) {
            account.withdraw(80);
        }
    }
}