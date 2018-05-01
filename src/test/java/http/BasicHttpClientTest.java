package http;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BasicHttpClientTest {

    private HttpClient basicHttpClient;

    @Before
    public void setup() {
        this.basicHttpClient = new BasicHttpClient();
    }

    @Test
    public void post() {
        Map<String, Object> params = new HashMap<>();
        params.put("test", "test");
        params.put("test1", "test1");

        int code = this.basicHttpClient.post("http://localhost:9000", params);

        assertThat(code, is(200));
    }

    /*@Test(expected = NotFoundException.class)
    public void post_notfound_url() {
        this.basicHttpClient.post("http://test111.net", new HashMap<>());
    }*/

    @Test(expected = IllegalStateException.class)
    public void post_malformed_url() {
        this.basicHttpClient.post("abcdef", new HashMap<>());
    }

}