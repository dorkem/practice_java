package java_practice.koreaClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public String getCurrentTime() {
        return LocalTime.now().format(formatter);
    }
}
