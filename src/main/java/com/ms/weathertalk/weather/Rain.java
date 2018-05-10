package com.ms.weathertalk.weather;

import com.jayway.jsonpath.JsonPath;
import com.ms.weathertalk.common.PrivateKey;
import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Rain {
    private static final String SKT_WEATHER_API_URL = "https://api2.sktelecom.com/com.ms.weather/current/minutely";
    private static final String WEATHER_API_KEY = PrivateKey.WEATHER_API_KEY;
    private final HttpClient httpClient;

    public Rain() {
        this.httpClient = new OkayHttpClient();
    }

    public Rain(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<RainCode> get() {
        Map<String, String> headers = new HashMap<>();
        headers.put("appKey", WEATHER_API_KEY);
        HttpResponse response = this.httpClient.get(SKT_WEATHER_API_URL, WeatherParams.seoul(), headers);
        String skyCode = JsonPath.read(response.getBody(), "$.weather.minutely[0].sky.code");
        return RainCode.getMaybeRain(skyCode);
    }
}
