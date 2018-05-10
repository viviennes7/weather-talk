package http;

import java.util.Map;

public interface HttpClient {

    HttpResponse get(String address, Object param, Map<String, String> headers);

    HttpResponse post(String address, Object param, Map<String, String> headers);

}