package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.Map;

@Data
public class HttpResponse <T>{
    private final int code;
    private final T body;
    private final Map<String, String> headers;

    private HttpResponse(Builder<T> builder) {
        this.code = builder.code;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    static public class Builder <T>{
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        private final int code;
        private T body;
        private Map<String, String> headers;

        public Builder(int code) {
            this.code = code;
        }

        public Builder body(String body) {
            this.body = this.stringToClass(body);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpResponse<T> build() {
            return new HttpResponse<>(this);
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
}
