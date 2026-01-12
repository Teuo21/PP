class BankAccount {

    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount
                + " | new balance = " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount
                    + " | new balance = " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw "
                    + amount + " but balance is too low");
        }
    }
}