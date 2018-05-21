package com.ms.weathertalk.messenger;

import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;

import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

public class TelegramMessenger implements Messenger{

    private static final String URL = "https://api.telegram.org/bot%s/sendMessage";

    private final String apiKey;

    private final String chatId;

    private final HttpClient httpClient;

    public TelegramMessenger(String apiKey, String chatId) {
        this(apiKey, chatId, new OkayHttpClient());
    }

    public TelegramMessenger(String apiKey, String chatId, HttpClient httpClient) {
        Objects.requireNonNull(apiKey,"apiKey가 반드시 필요합니다.");
        Objects.requireNonNull(chatId, "chatId가 반드시 필요합니다.");
        this.apiKey = apiKey;
        this.chatId = chatId;
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse send(Map<String, String> messageForm) {
        messageForm.put("chat_id", this.chatId);
        return this.httpClient.post(format(URL, apiKey), messageForm);
    }

    HttpClient getHttpClient() {
        return this.httpClient;
    }
}
