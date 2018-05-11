package com.ms.weathertalk;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ms.weathertalk.messenger.SlackMessenger;
import com.ms.weathertalk.scheduler.Scheduler;
import com.ms.weathertalk.weather.Rain;
import com.ms.weathertalk.weather.RainCode;

import static java.lang.String.format;

public class WeatherTalkClient {
    private final Scheduler scheduler;

    public WeatherTalkClient() {
        this.scheduler = new Scheduler();
    }

    public void execute() {
        scheduler.execute(7, 0, 0, new WeatherTalkRunner());
    }

    public class WeatherTalkRunner implements Runnable {
        private static final String SLACK_MESSAGE_KEY = "text";
        private final SlackMessenger slackMessenger;
        private final Rain rain;

        public WeatherTalkRunner() {
            this.rain = new Rain();
            this.slackMessenger = new SlackMessenger();
        }

        @Override
        public void run() {
            Optional<RainCode> maybeRain = rain.get();
            RainCode rainCode = maybeRain.orElse(RainCode.TEST);

            Map<String, String> messageForm = new HashMap<>();
            messageForm.put(SLACK_MESSAGE_KEY, format("`%s` 우산을 챙기세요.",  rainCode.getContent()));

            this.slackMessenger.send(messageForm);
        }
    }
}
