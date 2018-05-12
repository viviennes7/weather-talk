package com.ms.weathertalk.weather;

import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RainTest {

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        this.rain = new Rain("apiKey", httpClient);
    }

    private Rain rain;

    @Test
    public void get_비오는_경우() {
        HttpResponse response = new HttpResponse
                .Builder(200)
                .body("{\"weather\": {\"minutely\": [{\"sky\": {\"code\": \"SKY_A04\",\"name\": \"구름많고 비\"},\"rain\": {\"sinceMidnight\": \"22.00\"}}]}}")
                .build();

        given(this.httpClient.get(anyString(), any(WeatherParams.class), anyMap()))
                .willReturn(response);

        Rain.Detail detail = this.rain.get();

        assertThat(detail.getRainCode()).isEqualTo(RainCode.MANY_CLOUD_AND_RAIN);
        assertThat(detail.getRainfall()).isEqualTo(22.00);
    }


    @Test
    public void get_비안오는_경우() {
        HttpResponse response = new HttpResponse
                .Builder(200)
                .body("{\"weather\": {\"minutely\": [{\"sky\": {\"code\": \"SKY_A02\",\"name\": \"구름조금\"},\"rain\": {\"sinceMidnight\": \"0.00\"}}]}}")
                .build();

        given(this.httpClient.get(anyString(), any(WeatherParams.class), anyMap()))
                .willReturn(response);

        Rain.Detail detail = this.rain.get();
        assertThat(detail.getRainCode()).isEqualTo(RainCode.NO_RAIN);
        assertThat(detail.getRainfall()).isEqualTo(0.00);
    }

    public static class DetailTest {

        @Test
        public void isRain_현재날씨만_비옴() {
            Rain.Detail detail = new Rain.Detail(RainCode.CLOUD_AND_RAIN, 0.0);
            assertThat(detail.isRain()).isTrue();
        }

        @Test
        public void isRain_현재날씨는_비안오지만_강우량은_있는_경우() {
            Rain.Detail detail = new Rain.Detail(RainCode.NO_RAIN, 1.0);
            assertThat(detail.isRain()).isTrue();
        }

        @Test
        public void isRain_비_안오는_경우() {
            Rain.Detail detail = new Rain.Detail(RainCode.NO_RAIN, 0.0);
            assertThat(detail.isRain()).isFalse();
        }
    }
}