package scheduler;

import org.junit.Before;
import org.junit.Test;

public class SchedulerTest {

    private Scheduler scheduler;

    @Before
    public void setup() {
        this.scheduler = new Scheduler();
    }

    @Test
    public void start() throws InterruptedException {
        scheduler.execute(23, 8, 0, () -> System.out.println("Hello World"));
        Thread.sleep(1000 * 1000);
    }
}