import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SensorEvent {
    private final String sensorId;
    private final String type;
    private final String time;

    public SensorEvent(String sensorId, String type) {
        this.sensorId = sensorId;
        this.type = type;
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    @Override
    public String toString() {
        return String.format("[%s] Sensor: %s | Type: %s", time, sensorId, type);
    }
}