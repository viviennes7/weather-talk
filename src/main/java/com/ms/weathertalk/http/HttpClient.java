package com.ms.weathertalk.http;

import java.util.Map;

public interface HttpClient {

    HttpResponse get(String address);
    HttpResponse get(String address, Object param);
    HttpResponse get(String address, Object param, Map<String, String> headers);

    HttpResponse post(String address);
    HttpResponse post(String address, Object param);
    HttpResponse post(String address, Object param, Map<String, String> headers);

}