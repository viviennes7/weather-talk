package weather;

import com.jayway.jsonpath.JsonPath;
import common.PrivateKey;
import http.HttpClient;
import http.HttpResponse;
import http.OkayHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Rain {
    private static final String SKT_WEATHER_API_URL = "https://api2.sktelecom.com/weather/current/minutely";
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
        headers.put("appKey", PrivateKey.WEATHER_API_KEY);
        HttpResponse response = this.httpClient.get(SKT_WEATHER_API_URL, WeatherParams.seoul(), headers);
        String skyCode = JsonPath.read(response.getBody(), "$.weather.minutely[0].sky.code");
        return RainCode.getMaybeRain(skyCode);
    }
}
