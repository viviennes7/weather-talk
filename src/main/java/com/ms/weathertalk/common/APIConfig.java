package com.ms.weathertalk.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;

@Slf4j
public class APIConfig {
    public static final String WEATHER_API_KEY;
    public static final String SLACK_API_KEY;

    static {
        Map<String, String> apiKey = getApiKey();
        WEATHER_API_KEY = apiKey.get("WEATHER_API_KEY");
        SLACK_API_KEY = apiKey.get("SLACK_API_KEY");

        log.info(WEATHER_API_KEY);
        log.info(SLACK_API_KEY);
    }

    public static Map<String, String> getApiKey() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map<String, Map<String, String>> yml;
        try {
            File file = new File(".travis.yml");
            yml = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new IllegalStateException(".travis.yml을 파싱 중 문제가 발생했습니다.");
        }
        return yml.get("env");
    }

}
