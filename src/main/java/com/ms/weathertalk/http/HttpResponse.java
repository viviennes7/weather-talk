package com.ms.weathertalk.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HttpResponse {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final int code;
    private final String body;
    private final Map<String, List<String>> headers;

    private HttpResponse(Builder builder) {
        this.code = builder.code;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    static public class Builder {
        private final int code;
        private String body;
        private Map<String, List<String>> headers;

        public Builder(int code) {
            this.code = code;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder headers(Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }

    }
}
