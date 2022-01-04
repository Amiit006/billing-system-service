package com.rr.purchaseservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PurchaseException extends Exception {

    private String exception;
    private HttpStatus status;;

    public PurchaseException(String exception, HttpStatus exceptionCode) {
        this.exception = exception;
        this.status = exceptionCode;
    }
}