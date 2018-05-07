package http;

import java.util.Map;

public interface HttpClient {
    String UTF8 = "UTF-8";

    HttpResponse get(String address, Object param, Map<String, String> headers);

    HttpResponse get(String address, Map<String, String> param, Map<String, String> headers);

    HttpResponse post(String address, Object param, Map<String, String> headers);

    HttpResponse post(String address, Map<String, String> param, Map<String, String> headers);
}