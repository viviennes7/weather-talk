package messenger;

import http.HttpClient;

public interface Messenger {
    void send(HttpClient httpClient, Message message);
}
