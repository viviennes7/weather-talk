package com.ms.weathertalk.messenger;

import com.ms.weathertalk.common.PrivateKey;
import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;

import java.util.Map;

public class SlackMessenger implements Messenger {

    private static final String URL = "https://hooks.slack.com/services";

    private static final String SLACK_API_KEY = PrivateKey.SLACK_API_KEY;

    private final HttpClient httpClient;

    public SlackMessenger() {
        this.httpClient = new OkayHttpClient();
    }

    public SlackMessenger(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse send(Map<String, String> params) {
        return this.httpClient.post(URL + SLACK_API_KEY, params, null);
    }

    HttpClient getHttpClient() {
        return this.httpClient;
    }

}
