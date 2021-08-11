package com.ahttys.server.util;

import com.ahttys.server.util.error.ErrorResponse;
import com.ahttys.server.util.error.exceptions.CustomException;
import com.ahttys.server.util.error.exceptions.DuplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ErrorResponse> duplicationExceptionHandler(DuplicationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException ex) {
        ErrorResponse response = new ErrorResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }
}
