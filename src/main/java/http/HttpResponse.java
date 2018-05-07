package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
public class HttpResponse <T>{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final int code;
    private final String body;
    private final Map<String, List<String>> headers;

    private HttpResponse(Builder<T> builder) {
        this.code = builder.code;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    static public class Builder <T>{
        private final int code;
        private String body;
        private Map<String, List<String>> headers;

        public Builder(int code) {
            this.code = code;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder headers(Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public HttpResponse<T> build() {
            return new HttpResponse<>(this);
        }

    }

    public T bodyToClass(Class clazz) {
        T t;
        try {
            t = OBJECT_MAPPER.readValue(this.body, new TypeReference<T>() {});
        } catch (IOException e) {
            throw new IllegalStateException("ObjectMapper에서 String을 Object로 변환 중 오류가 발생했습니다.");
        }
        return t;
    }
}
