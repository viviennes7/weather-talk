package scheduler;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Scheduler {

    public static final String SEOUL_ZONE = "Asia/Seoul";
    public static final int ONE_DAY = 1;
    public static final int ONE_DAY_AS_SECOND = 24 * 60 * 60;
    public static final int DEFAULT_POOL_SIZE = 1;

    public void execute(int hour, int minute, int second, Runnable runnable) {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));

        ZonedDateTime nextExecutionTime;
        nextExecutionTime = now
                        .withHour(hour)
                        .withMinute(minute)
                        .withSecond(second);

        if (this.isOverDay(now, nextExecutionTime))
            nextExecutionTime = nextExecutionTime.plusDays(ONE_DAY);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(DEFAULT_POOL_SIZE);
        scheduler.scheduleAtFixedRate(runnable, this.getInitialExecutionTime(now, nextExecutionTime), ONE_DAY_AS_SECOND, SECONDS);

        //TODO 데몬쓰레드 계속 살리기
    }

    private long getInitialExecutionTime(ZonedDateTime now, ZonedDateTime nextExecutionTime) {
        Duration duration = Duration.between(now, nextExecutionTime);
        return duration.getSeconds();
    }

    private boolean isOverDay(ZonedDateTime zonedNow, ZonedDateTime sevenHour) {
        return zonedNow.compareTo(sevenHour) > 0;
    }
}