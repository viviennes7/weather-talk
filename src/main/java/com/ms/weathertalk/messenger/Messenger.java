package com.ms.weathertalk.messenger;

import com.ms.weathertalk.http.HttpResponse;

import java.util.Map;

public interface Messenger {
    HttpResponse send(Map<String, String> params);
}
