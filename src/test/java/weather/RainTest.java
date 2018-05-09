package weather;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import http.HttpClient;
import http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RainTest {

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        this.rain = new Rain(httpClient);
    }

    private Rain rain;

    @Test
    public void get_비오는_경우() {
        HttpResponse<Object> response = new HttpResponse
                .Builder(200)
                .body("{\"weather\":{\"minutely\":[{\"sky\":{\"code\":\"SKY_A04\",\"name\":\"구름많고 비\"}}]}}")
                .build();

        given(this.httpClient.get(anyString(), any(WeatherParams.class), anyMap()))
                .willReturn(response);

        Optional<RainCode> rainCode = this.rain.get();

        assertThat(rainCode.isPresent()).isTrue();
        assertThat(rainCode.get()).isEqualTo(RainCode.MANY_CLOUD_AND_RAIN);
    }


    @Test
    public void get_비안오는_경우() {
        HttpResponse<Object> response = new HttpResponse
                .Builder(200)
                .body("{\"weather\":{\"minutely\":[{\"sky\":{\"code\":\"SKY_A02\",\"name\":\"구름조금\"}}]}}")
                .build();

        given(this.httpClient.get(anyString(), any(WeatherParams.class), anyMap()))
                .willReturn(response);

        Optional<RainCode> rainCode = this.rain.get();
        assertThat(rainCode.isPresent()).isFalse();
    }
}