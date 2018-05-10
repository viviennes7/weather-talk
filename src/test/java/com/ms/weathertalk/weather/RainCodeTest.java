package com.ms.weathertalk.weather;

import java.util.Optional;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RainCodeTest {

    @Test
    public void getMaybeRain_존재하는_경우() {
        Optional<RainCode> rainCode = RainCode.getMaybeRain("SKY_A10");
        assertThat(rainCode.isPresent()).isTrue();
        assertThat(rainCode.get()).isEqualTo(RainCode.CLOUD_AND_RAIN_OR_SNOW);
    }

    @Test
    public void getMaybeRain_존재하지_않는_경우() {
        Optional<RainCode> rainCode = RainCode.getMaybeRain("SKY_A01");
        assertThat(rainCode.isPresent()).isFalse();
    }
}