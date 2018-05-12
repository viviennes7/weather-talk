package com.ms.weathertalk.weather;

import lombok.Data;

@Data
public class WeatherParams {
    private int version;
    private int stnid;
    private String city;
    private String country;
    private String village;

    public WeatherParams() {
        this.version = 2;
        this.stnid = 108;
    }

    public static WeatherParams seoul() {
        WeatherParams seoul = new WeatherParams();
        seoul.city = "서울";
        seoul.country = "강남구";
        seoul.village = "삼성동";
        return seoul;
    }
}