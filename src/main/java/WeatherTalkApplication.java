import http.OkayHttpClient;
import messenger.SlackMessenger;

import java.util.HashMap;
import java.util.Map;

public class WeatherTalkApplication {
    public static void main(String[] args) {
        SlackMessenger slackMessenger = new SlackMessenger(new OkayHttpClient());
        Map<String, String> params = new HashMap<>();
        params.put("text", "test입니다~");
        slackMessenger.send(params);
    }
}
