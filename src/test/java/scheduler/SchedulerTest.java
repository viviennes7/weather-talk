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
        scheduler.execute(9, 47, 0, () -> System.out.println("Hello World ::: 47"));
        scheduler.execute(9, 48, 0, () -> System.out.println("Hello World ::: 48"));
    }
}