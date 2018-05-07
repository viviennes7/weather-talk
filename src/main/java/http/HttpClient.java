package http;

import java.util.Map;

public interface HttpClient {
    String UTF8 = "UTF-8";

    int get(String address);
    int get(String address, Map<String, String> params);
    int get(String address, Map<String, String> params, Map<String, String> headers);

    int post(String address);
    int post(String address, Map<String, String> params);
    int post(String address, Map<String, String> params, Map<String, String> headers);
}