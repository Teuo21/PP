import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Dispatcher implements Runnable {
    private final SafeEventQueue eventQueue;
    private final JTextArea logArea;

    public Dispatcher(SafeEventQueue eventQueue, JTextArea logArea) {
        this.eventQueue = eventQueue;
        this.logArea = logArea;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                SensorEvent event = eventQueue.removeEvent();

                Thread.sleep(50);

                SwingUtilities.invokeLater(() -> {
                    logArea.append(event.toString() + "\n");
                    logArea.setCaretPosition(logArea.getDocument().getLength());
                });
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}