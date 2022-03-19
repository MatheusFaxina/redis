package br.com.redis.handler;

import br.com.redis.exception.PersonNotFoundException;
import br.com.redis.handler.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<?> handlePersonNotFoundException(final PersonNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                Error.builder()
                        .codeStatus(ex.getStatus().value())
                        .field(ex.getFieldError())
                        .description(ex.getMessage())
                        .build()
        );
    }

}
