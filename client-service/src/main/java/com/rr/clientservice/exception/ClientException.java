package com.rr.clientservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException extends Exception {
    private String exception;
    private HttpStatus status;;

    public ClientException(String exception, HttpStatus exceptionCode) {
        this.exception = exception;
        this.status = exceptionCode;
    }
}
