package src.pretender.timeSheduler;

import java.time.LocalDateTime;

public class WorkTimeScheduler {

    public static boolean timeNotPassed(LocalDateTime start , int seconds) {
        return LocalDateTime.now().minusSeconds(seconds).isBefore(start);
    }
}
