package com.codeit.duckhu.user.exception;


import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Getter
public class UserException extends RuntimeException {
    private final Instant timestamp;
    private final UserErrorCode errorCode;
    private final Map<String,Object> details;

    public UserException(UserErrorCode errorCode, Map<String,Object> details) {
        super(errorCode.getMessage());
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = details;
    }
}
