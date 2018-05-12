package com.ms.weathertalk.weather;

import com.jayway.jsonpath.JsonPath;
import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ms.weathertalk.weather.RainCode.NO_RAIN;

public class Rain {
    private static final String URL = "https://api2.sktelecom.com/weather/current/minutely";
    private final HttpClient httpClient;
    private final String apiKey;

    public Rain(String apiKey) {
        this(apiKey, new OkayHttpClient());
    }

    public Rain(String apiKey, HttpClient httpClient) {
        Objects.requireNonNull("apiKey가 반드시 필요합니다.");
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    public Detail get() {
        Map<String, String> headers = new HashMap<>();
        headers.put("appKey", this.apiKey);
        HttpResponse response = this.httpClient.get(URL, WeatherParams.seoul(), headers);
        String skyCode = JsonPath.read(response.getBody(), "$.weather.minutely[0].sky.code");
        String rainfall = JsonPath.read(response.getBody(), "$.weather.minutely[0].rain.sinceMidnight");
        return new Detail(RainCode.get(skyCode), Double.parseDouble(rainfall));
    }

    @Data
    @AllArgsConstructor
    static public class Detail {
        private RainCode rainCode;
        private Double rainfall;

        public boolean isRain() {
            return this.rainCode != NO_RAIN || this.rainfall > 0;
        }
    }
}
