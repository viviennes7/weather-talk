package com.ms.weathertalk;

import com.ms.weathertalk.common.APIConfig;
import com.ms.weathertalk.messenger.Messenger;
import com.ms.weathertalk.messenger.TelegramMessenger;
import com.ms.weathertalk.scheduler.Scheduler;
import com.ms.weathertalk.weather.Rain;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WeatherTalk {
    private final Scheduler scheduler;

    public WeatherTalk() {
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
        private final Messenger telegramMessenger;
        private final Rain rain;

        public WeatherTalkRunner() {
            this.rain = new Rain(APIConfig.WEATHER_API_KEY);
            this.telegramMessenger = new TelegramMessenger(APIConfig.TELEGRAM_API_KEY, "595055476");
        }

        @Override
        public void run() {
            Rain.Detail rain = this.rain.get();
            String message = rain.getDefaultMeesage();


            if (rain.isRain()) {
                log.info("\n" + message);
                this.send(message);
            } else {
                log.info(message);
            }
        }

        private void send(String message) {
            Map<String, String> messageForm = new HashMap<>();
            messageForm.put("text", message);
            messageForm.put("parse_mode", "Markdown");
            this.telegramMessenger.send(messageForm);
        }
    }
}
