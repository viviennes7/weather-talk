package com.ms.weathertalk.http;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NetworkExceptionTest {

    @Test
    public void networkException() {
        NetworkException error = new NetworkException("ERROR");
        assertThat(error.getMessage()).isEqualTo("Network 연결 중 문제가 발생하였습니다. ::: ERROR");
    }
}