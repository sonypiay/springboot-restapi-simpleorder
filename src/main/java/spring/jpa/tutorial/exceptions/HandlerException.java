package spring.jpa.tutorial.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import spring.jpa.tutorial.utils.WebErrorResponse;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebErrorResponse> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        WebErrorResponse.<String>builder()
                                .errors(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebErrorResponse> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebErrorResponse.<String>builder()
                        .errors(exception.getReason())
                        .build()
                );
    }
}
