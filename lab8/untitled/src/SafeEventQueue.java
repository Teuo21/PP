import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SafeEventQueue {
    private final Queue<SensorEvent> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public void addEvent(SensorEvent event) {
        lock.lock();
        try {
            queue.add(event);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public SensorEvent removeEvent() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
}