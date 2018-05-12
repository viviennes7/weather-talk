package com.ms.weathertalk.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpResponseTest {
    @Test
    public void builder() {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("contents-type", singletonList("application/json"));

        HttpResponse response = new HttpResponse.Builder(200)
                .body("body")
                .headers(headers)
                .build();

        assertThat(response.getBody()).isEqualTo("body");
        assertThat(response.getHeaders()).isEqualTo(headers);
        assertThat(response.getCode()).isEqualTo(200);
    }
}