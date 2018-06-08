package com.ms.weathertalk.http;

import static java.lang.String.format;

public class HttpException extends RuntimeException {
    public HttpException(int code, String message) {
        super(format("HTTP 호출에 실패했습니다. \nStatus Code ::: %s \n%s", code, message));
    }
}
