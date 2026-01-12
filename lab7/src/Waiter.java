class Waiter extends Thread {
    private Kitchen kitchen;

    public Waiter(Kitchen k) { this.kitchen = k; }

    public void run() {
        try {
            while (true) {
                kitchen.removePizza();
                Thread.sleep(10);
            }
        } catch (Exception ignored) {}
    }
}