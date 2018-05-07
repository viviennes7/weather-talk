package http;

public class NetworkException extends RuntimeException {
    public NetworkException(String message) {
        super("Network 연결 중 문제가 발생하였습니다. ::: " + message);
    }
}
