package com.ms.weathertalk.weather;

import org.junit.Test;

import static com.ms.weathertalk.weather.RainCode.CLOUD_AND_RAIN_OR_SNOW;
import static com.ms.weathertalk.weather.RainCode.NO_RAIN;
import static org.assertj.core.api.Assertions.assertThat;

public class RainCodeTest {

    @Test
    public void getMaybeRain_존재하는_경우() {
        RainCode rainCode = RainCode.get("SKY_A10");
        assertThat(rainCode).isEqualByComparingTo(CLOUD_AND_RAIN_OR_SNOW);
        assertThat(rainCode.getContent()).isEqualTo("흐리고 비 또는 눈");
    }

    @Test
    public void getMaybeRain_존재하지_않는_경우() {
        RainCode rainCode = RainCode.get("SKY_A01");
        assertThat(rainCode).isEqualTo(NO_RAIN);
        assertThat(rainCode.getCode()).isEqualTo("SKY_A00");
        assertThat(rainCode.getContent()).isEqualTo("비 안옴");
    }
}