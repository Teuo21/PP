import java.util.LinkedList;
import java.util.List;

class Kitchen {

    private List<Pizza> pizzas = new LinkedList<>();
    private final int MAX_CAPACITY = 10;

    public synchronized void addPizza(Pizza p) throws InterruptedException {
        while (pizzas.size() == MAX_CAPACITY) {
            wait();
        }
        pizzas.add(p);
        System.out.println("Pizza nr " + p.getId() + " was added");
        notifyAll();
    }

    public synchronized Pizza removePizza() throws InterruptedException {
        while (pizzas.isEmpty()) {
            wait();
            Pizza p = pizzas.removeFirst();
            System.out.println("Pizza nr " + p.getId() + " was removed");
            notifyAll();
            return p;
        }
        return null;
    }
}