package messenger;

import http.HttpResponse;

import java.util.Map;

public interface Messenger {
    HttpResponse send(Map<String, String> params);
}
