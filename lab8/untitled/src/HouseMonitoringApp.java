import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HouseMonitoringApp extends JFrame {
    private final ExecutorService executor;
    private final SafeEventQueue eventQueue;
    private final JTextArea logArea;

    public HouseMonitoringApp() {
        super("House Sensor Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        eventQueue = new SafeEventQueue();

        executor = Executors.newFixedThreadPool(5);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        Dispatcher dispatcher = new Dispatcher(eventQueue, logArea);
        executor.execute(dispatcher);

        createSensorAndButton(buttonPanel, "S1", "Motion");
        createSensorAndButton(buttonPanel, "S2", "Temperature");
        createSensorAndButton(buttonPanel, "S3", "Door/Window");
        createSensorAndButton(buttonPanel, "S4", "Smoke");

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSensorAndButton(JPanel panel, String id, String type) {
        Sensor sensor = new Sensor(id, type, eventQueue);
        executor.execute(sensor);
        JButton toggleBtn = new JButton("Start " + id);
        toggleBtn.addActionListener(e -> {
            boolean currentPauseState = sensor.isPaused();
            sensor.setPaused(!currentPauseState);

            if (sensor.isPaused()) {
                toggleBtn.setText("Start " + id);
                toggleBtn.setBackground(null);
            } else {
                toggleBtn.setText("Pause " + id);
                toggleBtn.setBackground(Color.GREEN);
            }
        });

        panel.add(toggleBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HouseMonitoringApp::new);
    }
}