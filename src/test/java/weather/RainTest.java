package weather;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RainTest {
    private Rain rain;

    @Before
    public void setup() {
        this.rain = new Rain();
    }

    @Test
    public void isRainingToday() {
        this.rain.isRainingToday();
    }
}