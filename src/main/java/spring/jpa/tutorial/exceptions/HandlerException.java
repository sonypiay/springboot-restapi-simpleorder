package spring.jpa.tutorial.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import spring.jpa.tutorial.utils.WebErrorResponse;

import java.util.List;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebErrorResponse> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebErrorResponse.builder()
                        .message(exception.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebErrorResponse> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebErrorResponse.builder()
                        .message(exception.getReason())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<WebErrorResponse> badRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(WebErrorResponse.<String>builder()
                .message(exception.getMessage())
                .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<WebErrorResponse> notFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebErrorResponse.<String>builder()
                .message(exception.getMessage())
                .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebErrorResponse> validationRequestException(MethodArgumentNotValidException exception) {
        List<String> errorList = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.status(exception.getStatusCode()).body(
                WebErrorResponse.builder()
                        .errors(errorList)
                        .message("Missing Parameter Request")
                        .build()
        );
    }
}
