package com.ahttys.server.util.error.exceptions;

import com.ahttys.server.util.error.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicationException extends RuntimeException {
    private ErrorCode errorCode;

    public DuplicationException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
