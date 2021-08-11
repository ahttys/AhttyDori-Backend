package com.ahttys.server.util.error.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private int status;

    public CustomException(String msg, int status) {
        super(msg);
        this.status = status;
    }
}
