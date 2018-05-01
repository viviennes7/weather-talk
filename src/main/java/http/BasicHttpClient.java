package http;

import java.io.*;
import java.net.*;
import java.util.Map;

public class BasicHttpClient implements HttpClient {

    @Override
    public int post(String address, Map params) {
        URL url = this.getUrl(address);
        HttpURLConnection postConnection = this.getPostConnection(url);
        this.write(postConnection, params);
        return this.getResponseCode(postConnection);
    }

    private int getResponseCode(HttpURLConnection postConnection) {
        int responseCode;

        try{
            responseCode = postConnection.getResponseCode();
        }catch (IOException e) {
            throw new IllegalStateException("responseCode를 읽어들일 수 없습니다.");
        }

        return responseCode;
    }

    private URL getUrl(String address) {
        URL url;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            //TODO e.printStackTrace(); 제거, throws 안하기
            throw new NotFoundException();
        }
        return url;
    }

    private HttpURLConnection getPostConnection(URL url) {
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        } catch (IOException e) {
            throw new IllegalStateException("HttpURLConnection 생성 중 오류가 발생하였습니다.");
        }

        return connection;

    }

    private void write(URLConnection connection, Map params) {

        try (OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8))) {
            writer.write(this.getQuery(params));
        } catch (IOException e) {
            throw new IllegalStateException("HTTP 요청 중 오류가 발생하였습니다.");
        }
    }


    private String getQuery(Map<String, Object> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (String key : params.keySet()) {
            if (first) first = false;
            else result.append("&");

            try {
                result.append(URLEncoder.encode(key, UTF_8));
                result.append("=");
                result.append(URLEncoder.encode(params.get(key).toString(), UTF_8));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("URLencoder가 UTF-8로 인코딩 중에 문제가 발생하였습니다.");
            }
        }
        return result.toString();
    }
}
