package http;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("해당 URL을 찾을 수 없습니다.");
    }
}
