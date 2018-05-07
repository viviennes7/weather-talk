package http;

import http.BasicHttpClient.MalformedURLException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicHttpClientTest {

    private HttpClient basicHttpClient;

    @Before
    public void setup() {
        this.basicHttpClient = new BasicHttpClient();
    }

    @Test
    public void post() {
        Map<String, String> params = new HashMap<>();
        params.put("test", "test");
        params.put("test1", "test1");

        int code = this.basicHttpClient.post("http://localhost:9000", params);

        assertThat(code).isEqualTo(200);
    }

    /*@Test(expected = NotFoundException.class)
    public void post_notfound_url() {
        this.basicHttpClient.post("http://test111.net", new HashMap<>());
    }*/

    @Test(expected = MalformedURLException.class)
    public void post_malformed_url() {
        this.basicHttpClient.post("abcdef", new HashMap<>());
    }

}