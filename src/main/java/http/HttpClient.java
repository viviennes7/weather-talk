package http;

import java.io.IOException;
import java.util.Map;

public interface HttpClient {
    String UTF8 = "UTF-8";

    HttpResponse get(String address, Map<String, String> params, Map<String, String> headers);

    HttpResponse post(String address, Map<String, String> params, Map<String, String> headers);
}