package http;

import java.io.*;
import java.net.*;
import java.util.Map;

import static java.lang.String.format;

public class BasicHttpClient implements HttpClient {

    @Override
    public int post(String address, Map<String, String> params) {
        URL url = this.getUrl(address);
        HttpURLConnection postConnection = this.getPostConnection(url);
        this.write(postConnection, params);
        return this.getResponseCode(postConnection);
    }

    private URL getUrl(String address) {
        URL url;
        try {
            url = new URL(address);
        } catch (java.net.MalformedURLException e) {
            throw new MalformedURLException();
        }
        return url;
    }

    private HttpURLConnection getPostConnection(URL url) {
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        } catch (IOException e) {
            //Fixme 구체적으로 뭘잡아야 되지?
            throw new RuntimeException();
        }

        return connection;

    }

    private void write(URLConnection connection, Map params) {
        try (OutputStream os = connection.getOutputStream();
             BufferedWriter writer = this.getBufferedWriter(os)) {

            writer.write(this.getQuery(params));
        } catch (IOException e) {
            //Fixme 구체적으로 뭘잡아야 되지?
            throw new IllegalStateException("HTTP 요청 중 오류가 발생하였습니다.");
        }
    }

    private BufferedWriter getBufferedWriter(OutputStream os) throws UnsupportedEncodingException {
        return new BufferedWriter(new OutputStreamWriter(os, UTF8));
    }

    private String getQuery(Map<String, Object> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (String key : params.keySet()) {
            if (first) first = false;
            else result.append("&");

            try {
                result.append(URLEncoder.encode(key, UTF8));
                result.append("=");
                result.append(URLEncoder.encode(params.get(key).toString(), UTF8));
            } catch (UnsupportedEncodingException e) {
                throw new UTF8EncodingException();
            }
        }
        return result.toString();
    }

    private int getResponseCode(HttpURLConnection postConnection) {
        int responseCode;

        try {
            responseCode = postConnection.getResponseCode();
        } catch (IOException e) {
            throw new IllegalStateException("responseCode를 읽어들일 수 없습니다.");
        }

        return responseCode;
    }

    static public class MalformedURLException extends RuntimeException {
        public MalformedURLException() {
            super("URL 형식이 아닙니다.");
        }
    }

    static public class UTF8EncodingException extends RuntimeException {
        public UTF8EncodingException() {
            super(format("URLencoder가 %s로 인코딩 중 문제가 발생하였습니다.", UTF8));
        }
    }
}
