package weather;

import lombok.Data;

@Data
public class WeatherParams {
    private int version;
    private String city;
    private String country;
    private String village;
    private int stnid;

    public static WeatherParams seoul() {
        WeatherParams seoul = new WeatherParams();
        seoul.version = 2;
        seoul.city = "서울";
        seoul.country = "강남구";
        seoul.village = "삼성동";
        seoul.stnid = 108;
        return seoul;
    }
}