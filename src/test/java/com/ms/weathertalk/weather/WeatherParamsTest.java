package com.ms.weathertalk.weather;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherParamsTest {

    @Test
    public void seoul() {
        WeatherParams seoul = WeatherParams.seoul();
        assertThat(seoul.getStnid()).isEqualTo(108);
        assertThat(seoul.getVersion()).isEqualTo(2);
        assertThat(seoul.getCity()).isEqualTo("서울");
        assertThat(seoul.getCountry()).isEqualTo("강남구");
        assertThat(seoul.getVillage()).isEqualTo("삼성동");
    }
}