package weather;

import common.PrivateKey;
import http.HttpClient;
import http.HttpResponse;
import http.OkayHttpClient;

import java.util.HashMap;
import java.util.Map;

public class Rain {
    private static final String SKT_WEATHER_API_URL = "https://api2.sktelecom.com/weather/current/minutely";
    private final HttpClient httpClient;

    public Rain() {
        this.httpClient = new OkayHttpClient();
    }

    public Rain(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public boolean isRainingToday() {
        Map<String, String> headers = new HashMap<>();
        headers.put("appKey", PrivateKey.WEATHER_API_KEY);
        HttpResponse response = this.httpClient.get(SKT_WEATHER_API_URL, WeatherParams.seoul(), headers);
        String body = response.getBody();

        //TODO 작업여기부터 시작~

        return false;
    }
}
