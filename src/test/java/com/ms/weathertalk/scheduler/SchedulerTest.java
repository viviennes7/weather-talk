package com.ms.weathertalk.scheduler;

import org.junit.Test;

public class SchedulerTest {

    @Test
    public void execute()  {
        Scheduler scheduler = new Scheduler();
        scheduler.execute(0, 0, 0, () -> System.out.println("Hello World !!!"));
    }
}