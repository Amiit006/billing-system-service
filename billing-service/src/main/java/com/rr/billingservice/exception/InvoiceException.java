package com.rr.billingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvoiceException extends Exception {

    private String exception;
    private HttpStatus status;;

    public InvoiceException(String exception, HttpStatus exceptionCode) {
        this.exception = exception;
        this.status = exceptionCode;
    }
}
