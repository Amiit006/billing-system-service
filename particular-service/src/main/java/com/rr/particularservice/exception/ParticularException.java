package com.rr.particularservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParticularException extends Exception {

    private String exception;
    private HttpStatus status;;

    public ParticularException(String exception, HttpStatus exceptionCode) {
        this.exception = exception;
        this.status = exceptionCode;
    }
}
