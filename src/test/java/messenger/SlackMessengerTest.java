package messenger;

import http.BasicHttpClient;
import http.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SlackMessengerTest {

    private SlackMessenger slackMessenger;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        this.slackMessenger = new SlackMessenger(httpClient);
    }

    @Test
    public void noConstructor() {
        SlackMessenger slackMessenger = new SlackMessenger();
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(BasicHttpClient.class);
    }

    @Test
    public void oneConstructor() {
        SlackMessenger slackMessenger = new SlackMessenger(new BasicHttpClient());
        assertThat(slackMessenger.getHttpClient()).isInstanceOf(BasicHttpClient.class);
    }

    @Test
    public void send_성공() {
        this.send(200);
    }

    @Test
    public void send_서버에러() {
        this.send(500);
    }

    private void send(int i) {
        given(this.httpClient.post(anyString(), anyMap()))
                .willReturn(i);

        Map<String, String> params = new HashMap<>();
        params.put("text", "Hello World");
        int response = this.slackMessenger.send(params);
        assertThat(response).isEqualTo(i);
    }
}