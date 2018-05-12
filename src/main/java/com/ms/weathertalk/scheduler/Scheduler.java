package com.ms.weathertalk.scheduler;

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
    public static final int SINGLE_POOL_SIZE = 1;

    private final ScheduledExecutorService scheduler;

    public Scheduler() {
        this.scheduler = Executors.newScheduledThreadPool(SINGLE_POOL_SIZE);
    }

    public void execute(int hour, int minute, int second, Runnable command) {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));

        ZonedDateTime nextExecutionTime;
        nextExecutionTime = now
                        .withHour(hour)
                        .withMinute(minute)
                        .withSecond(second);

        if (this.isOverDay(now, nextExecutionTime))
            nextExecutionTime = nextExecutionTime.plusDays(ONE_DAY);

        scheduler.scheduleAtFixedRate(command, this.getInitialExecutionTime(now, nextExecutionTime), ONE_DAY_AS_SECOND, SECONDS);
    }

    private long getInitialExecutionTime(ZonedDateTime now, ZonedDateTime nextExecutionTime) {
        Duration duration = Duration.between(now, nextExecutionTime);
        return duration.getSeconds();
    }

    private boolean isOverDay(ZonedDateTime zonedNow, ZonedDateTime sevenHour) {
        return zonedNow.compareTo(sevenHour) > 0;
    }

}