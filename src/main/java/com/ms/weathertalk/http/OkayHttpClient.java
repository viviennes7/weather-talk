package com.ms.weathertalk.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class OkayHttpClient implements HttpClient {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String EMPTY = "";

    private OkHttpClient okHttpClient;

    private ObjectMapper objectMapper;

    public OkayHttpClient() {
        this(new OkHttpClient());
    }

    public OkayHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public HttpResponse get(String address, Object param, Map<String, String> headers) {
        Map<String, String> paramMap = this.objectMapper.convertValue(param, new TypeReference<Map<String, String>>(){});
        Request request = new Request.Builder()
                .url(address + this.getQuery(paramMap))
                .headers(Headers.of(headers))
                .build();

        return this.convertHttpResponse(this.getResponse(request));
    }

    @Override
    public HttpResponse post(String address, Object param, Map<String, String> headers) {
        Map<String, String> paramMap = this.objectMapper.convertValue(param, new TypeReference<Map<String, String>>(){});
        RequestBody body = RequestBody.create(JSON, this.getJsonParams(paramMap));
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .headers(Headers.of(headers))
                .build();

        return this.convertHttpResponse(this.getResponse(request));
    }

    private HttpResponse convertHttpResponse(Response response) {
        return new HttpResponse
                .Builder(response.code())
                .headers(response.headers().toMultimap())
                .body(this.getBody(response))
                .build();
    }

    private Response getResponse(Request request) {
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new NetworkException(e.getMessage());
        }
        return response;
    }

    private String getBody(Response response) {
        String body;
        try {
            body = response.body().string();
        } catch (IOException e) {
            throw new IllegalStateException("body를 가져오는 중 문제가 발생했습니다. ::: " + e.getMessage());
        }
        return body;
    }

    private String getJsonParams(Map<String, String> params) {
        try {
            return this.objectMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Map을 JSON으로 변환 중 문제가 발생했습니다. ::: " + e.getMessage());
        }
    }

    private String getQuery(Map<String, String> param) {
        if(param == null){
            log.debug("param이 없습니다.");
            return EMPTY;
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (String key : param.keySet()) {
            if (first) {
                result.append("?");
                first = false;
            } else {
                result.append("&");
            }

            result.append(key);
            result.append("=");
            result.append(param.get(key));
        }
        return result.toString();
    }
}
