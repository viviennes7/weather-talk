package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class OkayHttpClient implements HttpClient {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient okHttpClient;

    private ObjectMapper objectMapper;

    public OkayHttpClient() {
        this(new OkHttpClient());
    }

    public OkayHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public HttpResponse get(String address, Map<String, String> params, Map<String, String> headers) {
        Headers okHttpHeaders = Headers.of(headers);
        Request request = new Request.Builder()
                .url(address)
                .headers(okHttpHeaders)
                .build();

        Response response = this.getResponse(request);

        return this.convertHttpResponse(response);
    }

    @Override
    public HttpResponse post(String address, Map<String, String> params, Map<String, String> headers) {
        RequestBody body = RequestBody.create(JSON, this.getJsonParams(params));
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();

        Response response = this.getResponse(request);

        return this.convertHttpResponse(response);
    }

    private HttpResponse convertHttpResponse(Response response) {
        return new HttpResponse
                .Builder<>(response.code())
                .headers(response.headers().toMultimap())
                .body(this.getBody(response))
                .build();
    }

    private Response getResponse(Request request) {
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new NetworkException(e.getMessage());
        }
        return response;
    }

    private String getBody(Response response) {
        String body = null;
        try {
            body = response.body().string();
        } catch (IOException e) {
            //TODO 명시적으로 해줘~
            e.printStackTrace();
        }
        return body;
    }

    private String getJsonParams(Map<String, String> params) {
        try {
            return this.objectMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Map을 JSON으로 변환 중 문제가 발생했습니다.");
        }
    }
}
