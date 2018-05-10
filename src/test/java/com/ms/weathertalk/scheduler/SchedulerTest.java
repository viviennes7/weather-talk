package com.ms.weathertalk.scheduler;

public class SchedulerTest {

    public static void main(String[] args)  {
        Scheduler scheduler = new Scheduler();
        scheduler.execute(16, 46, 0, () -> System.out.println("Hello World !!!"));
    }
}