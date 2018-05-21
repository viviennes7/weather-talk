package com.ms.weathertalk.messenger;

import com.ms.weathertalk.http.HttpClient;
import com.ms.weathertalk.http.HttpResponse;
import com.ms.weathertalk.http.OkayHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TelegramMessengerTest {

    private TelegramMessenger telegramMessenger;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        this.telegramMessenger = new TelegramMessenger("apiKey", "chatId", httpClient);
    }

    @Test
    public void twoConstructor() {
        TelegramMessenger slackMessenger = new TelegramMessenger("apiKey", "chatId");
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(OkayHttpClient.class);
    }

    @Test
    public void threeConstructor() {
        TelegramMessenger slackMessenger = new TelegramMessenger("apiKey", "chatId", new OkayHttpClient());
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(OkayHttpClient.class);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_apiKey_null_확인() {
        new TelegramMessenger(null, "");
    }

    @Test(expected = NullPointerException.class)
    public void constructor_chatId_null_확인() {
        new TelegramMessenger("", null);
    }

    @Test
    public void send_성공() {
        this.send(new HttpResponse.Builder(200).build());
    }

    @Test
    public void send_서버에러() {
        this.send(new HttpResponse.Builder(500).build());
    }

    private void send(HttpResponse httpResponse) {
        given(this.httpClient.post(anyString(), anyMap(), any()))
                .willReturn(httpResponse);

        Map<String, String> params = new HashMap<>();
        params.put("text", "Hello World");
        HttpResponse response = this.telegramMessenger.send(params);
        assertThat(response.getCode()).isEqualTo(httpResponse.getCode());
    }
}