package http;

import okhttp3.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OkayHttpClientTest {

    private OkayHttpClient okayHttpClient;

    @Mock
    private OkHttpClient okHttpClient;

    @Before
    public void setup() {
        okayHttpClient = new OkayHttpClient(okHttpClient);
    }

    @Test
    public void get() throws IOException {
//        ResponseBody responseBody = ResponseBody.create(MediaType.parse("text/plain"), "Hello World");
//        Request request = new Request.Builder()
//                .url("http://www.test.com")
//                .build();
//
//        Response mockResponse = new Response
//                .Builder()
//                .request(request)
//                .protocol(Protocol.HTTP_1_1)
//                .message("message")
//                .code(200)
//                .body(responseBody)
//                .build();
//
//        given(this.okHttpClient.newCall(request).execute())
//                .willReturn(mockResponse);
//
//        HttpResponse httpResponse = this.okayHttpClient.get("www.test.com", new HashMap<>(), null);
//
//        assertThat(httpResponse.getCode()).isEqualTo(200);
//        assertThat(httpResponse.getHeaders()).isNull();
    }

    @Test
    public void post() {
    }
}