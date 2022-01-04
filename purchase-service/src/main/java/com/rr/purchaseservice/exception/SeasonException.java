package com.rr.purchaseservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SeasonException extends Exception {

    private String exception;
    private HttpStatus status;;

    public SeasonException(String exception, HttpStatus exceptionCode) {
        this.exception = exception;
        this.status = exceptionCode;
    }
}