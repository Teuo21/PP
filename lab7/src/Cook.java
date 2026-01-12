import java.util.Random;

class Cook extends Thread {
    private Kitchen kitchen;
    private static int pizzaCounter = 0;

    public Cook(Kitchen k) { this.kitchen = k; }

    @Override
    public void run() {
        try {
            while (true) {
                int id;
                synchronized (Cook.class) {
                    id = ++pizzaCounter;
                }

                int ingredients = 3 + new Random().nextInt(5);
                Pizza pizza = new Pizza(id, ingredients);

                kitchen.addPizza(pizza);

                Thread.sleep(5 + ingredients * 2);
            }
        } catch (Exception ignored) {}
    }
}