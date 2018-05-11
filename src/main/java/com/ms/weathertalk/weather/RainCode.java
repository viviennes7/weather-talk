package com.ms.weathertalk.weather;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum RainCode {
    TEST("TEST", "TEST"),
    MANY_CLOUD_AND_RAIN("SKY_A04", "구름많고 비"),
    MANY_CLOUD_AND_SNOW("SKY_A05", "구름많고 눈"),
    MANY_CLOUD_AND_RAIN_OR_SNOW("SKY_A06", "구름많고 비 또는 눈"),
    CLOUD_AND_RAIN("SKY_A08", "흐리고 비"),
    CLOUD_AND_SNOW("SKY_A09", "흐리고 눈"),
    CLOUD_AND_RAIN_OR_SNOW("SKY_A10", "흐리고 비 또는 눈"),
    THUNDER_AND_RAIN("SKY_A12", "뇌우/비"),
    THUNDER_AND_SNOW("SKY_A13", "뇌우/눈"),
    THUNDER_AND_RAIN_OR_RAIN("SKY_A14", "뇌우/비 또는 눈");

    private String code;
    private String content;

    //TODO 왜 앞에 private붙이면 경고문뜨지?
    private RainCode(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public static Optional<RainCode> getMaybeRain(String code) {
        return Arrays.stream(values())
                .filter(v -> code.equals(v.code))
                .findFirst();
    }
}