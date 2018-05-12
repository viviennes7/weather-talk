package com.ms.weathertalk.messenger;

import com.ms.weathertalk.common.PrivateKey;
import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlackMessenger implements Messenger {
    private static final String URL = "https://hooks.slack.com/services";

    private final String apiKey;

    private final HttpClient httpClient;

    public SlackMessenger(String apiKey) {
        this(apiKey, new OkayHttpClient());
    }

    public SlackMessenger(String apiKey, HttpClient httpClient) {
        Objects.requireNonNull("apiKey가 반드시 필요합니다.");
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse send(Map<String, String> messageForm) {
        return this.httpClient.post(URL + this.apiKey, messageForm, new HashMap<>());
    }

    HttpClient getHttpClient() {
        return this.httpClient;
    }

}
