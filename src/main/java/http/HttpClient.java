package http;

import java.util.Map;

public interface HttpClient {
    String UTF_8 = "UTF-8";

    int post(String url, Map<String, Object> params);
}