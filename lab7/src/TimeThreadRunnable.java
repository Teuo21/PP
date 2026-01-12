class TimePrinterRunnable implements Runnable {

    private String name;

    public TimePrinterRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(name + ": " + java.time.LocalTime.now());
                Thread.sleep(10_000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}