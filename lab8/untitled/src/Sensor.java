import java.util.Random;

public class Sensor implements Runnable {
    private final String id;
    private final String type;
    private final SafeEventQueue eventQueue;
    private volatile boolean isPaused = true;
    private final Random random = new Random();

    public Sensor(String id, String type, SafeEventQueue eventQueue) {
        this.id = id;
        this.type = type;
        this.eventQueue = eventQueue;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (!isPaused) {
                    SensorEvent event = new SensorEvent(id, type);
                    eventQueue.addEvent(event);

                    Thread.sleep(1000 + random.nextInt(2000));
                } else {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}