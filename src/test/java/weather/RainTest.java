package weather;

import org.junit.Before;
import org.junit.Test;

public class RainTest {
    private Rain rain;

    @Before
    public void setup() {
        this.rain = new Rain();
    }

    @Test
    public void isRainingToday() {
        this.rain.get();
    }
}