package messenger;

import java.util.Map;

public interface Messenger {
    int send(Map<String, String> params);
}
