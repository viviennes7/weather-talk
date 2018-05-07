package messenger;

import common.PrivateKey;
import http.HttpClient;
import http.HttpResponse;
import http.OkayHttpClient;

import java.util.Map;

public class SlackMessenger implements Messenger {

    private static final String URL = "https://hooks.slack.com/services";

    private static final String WEBHOOK_KEY = PrivateKey.SLACK_API_KEY;

    private final HttpClient httpClient;

    public SlackMessenger() {
        this.httpClient = new OkayHttpClient();
    }

    public SlackMessenger(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse send(Map<String, String> params) {
        return this.httpClient.post(URL + WEBHOOK_KEY, params, null);
    }

    HttpClient getHttpClient() {
        return this.httpClient;
    }

}
