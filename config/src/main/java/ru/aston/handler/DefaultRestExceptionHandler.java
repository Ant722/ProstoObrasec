package ru.aston.handler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.exception.EmployeeNotFoundException;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class DefaultRestExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CustomExceptionResponse> handleConstraintViolationException(
            ConstraintViolationException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<CustomExceptionResponse> handleEmployeeNotFoundException(
            EmployeeNotFoundException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handle(ex));
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<CustomExceptionResponse> handleValidationException(
            ValidationException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private CustomExceptionResponse handle(Exception ex) {
        return new CustomExceptionResponse(ex.getMessage());
    }

    @Data
    @NoArgsConstructor
    public static class CustomExceptionResponse {

        private final Instant time = Instant.now();
        private String errorMessage;

        public CustomExceptionResponse(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
