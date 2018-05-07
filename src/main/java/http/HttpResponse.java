package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.util.Map;

@Data
@Getter
public class HttpResponse <T>{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private int code;
    private T body;
    private Map<String, String> headers;

    public HttpResponse(int code) {
        this.code = code;
    }

    public HttpResponse(int code, String body) {
        this(code);
        this.body = this.stringToClass(body);
    }

    public HttpResponse(int code, String body, Map<String, String> headers) {
        this(code, body);
        this.headers = headers;
    }

    private T stringToClass(String string) {
        T t;
        try {
            t = OBJECT_MAPPER.readValue(string, new TypeReference<T>() {});
        } catch (IOException e) {
            throw new IllegalStateException("ObjectMapper에서 String을 Object로 변환 중 오류가 발생했습니다.");
        }
        return t;
    }

}
