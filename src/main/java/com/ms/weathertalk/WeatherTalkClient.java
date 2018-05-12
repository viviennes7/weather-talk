package com.ms.weathertalk;

import com.ms.weathertalk.common.PrivateKey;
import com.ms.weathertalk.messenger.SlackMessenger;
import com.ms.weathertalk.scheduler.Scheduler;
import com.ms.weathertalk.weather.Rain;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Slf4j
public class WeatherTalkClient {
    private final Scheduler scheduler;

    public WeatherTalkClient() {
        this.scheduler = new Scheduler();
    }

    public void execute() {
        this.executeNow();
        this.scheduler.execute(7, 0, 0, new WeatherTalkRunner());
    }

    private void executeNow() {
        new WeatherTalkRunner().run();
    }

    public class WeatherTalkRunner implements Runnable {
        private static final String SLACK_MESSAGE_KEY = "text";
        private final SlackMessenger slackMessenger;
        private final Rain rain;

        public WeatherTalkRunner() {
            this.rain = new Rain(PrivateKey.WEATHER_API_KEY);
            this.slackMessenger = new SlackMessenger(PrivateKey.SLACK_API_KEY);
        }

        @Override
        public void run() {
            Rain.Detail rain = this.rain.get();
            String content = rain.getRainCode().getContent();

            if (rain.isRain()) {
                String message = format("현재 날씨는 `%s` 입니다.\n오늘 예상 강우량은 `%s` 입니다. \n우산을 챙기세요. ",
                        content, rain.getRainfall());
                log.info("\n" + message);
                this.send(message);
            } else {
                log.info("현재 날씨는 비가 오지 않습니다.");
            }
        }

        private void send(String message) {
            Map<String, String> messageForm = new HashMap<>();
            messageForm.put(SLACK_MESSAGE_KEY, message);
            this.slackMessenger.send(messageForm);
        }
    }
}
