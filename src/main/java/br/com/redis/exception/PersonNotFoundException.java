package br.com.redis.exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends RuntimeException {

    private final String fieldError;
    private final HttpStatus status;

    public PersonNotFoundException(final String message, final String fieldError, final HttpStatus status) {
        super(message);
        this.fieldError = fieldError;
        this.status = status;
    }

    public String getFieldError() {
        return fieldError;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
