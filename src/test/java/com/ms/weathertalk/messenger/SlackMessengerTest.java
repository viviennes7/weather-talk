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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SlackMessengerTest {

    private SlackMessenger slackMessenger;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        this.slackMessenger = new SlackMessenger("apiKey", httpClient);
    }

    @Test
    public void noConstructor() {
        SlackMessenger slackMessenger = new SlackMessenger("apiKey");
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(OkayHttpClient.class);
    }

    @Test
    public void oneConstructor() {
        SlackMessenger slackMessenger = new SlackMessenger("apiKey", new OkayHttpClient());
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(OkayHttpClient.class);
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
        HttpResponse response = this.slackMessenger.send(params);
        assertThat(response.getCode()).isEqualTo(httpResponse.getCode());
    }
}