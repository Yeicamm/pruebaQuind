package com.technical.test.quind.hexagonal.infrastructure.adapter.exepcion;

import org.springframework.http.HttpStatus;

public class ClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus errorCode;
    private String errorMessage;

    public ClientException(HttpStatus errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ClientException() {
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
