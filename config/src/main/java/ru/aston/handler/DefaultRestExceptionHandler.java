package ru.aston.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.PasswordGenerateTimeException;
import ru.aston.exception.LoginConflictException;

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

    @ExceptionHandler(value = MailSendException.class)
    public ResponseEntity<CustomExceptionResponse> handleMailException(MailSendException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handle(ex));
    }

    @ExceptionHandler(value = PasswordGenerateTimeException.class)
    public ResponseEntity<CustomExceptionResponse> handlePasswordGenerateException(PasswordGenerateTimeException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.error(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(handle(ex));
    }

    @ExceptionHandler(value = LoginConflictException.class)
    public ResponseEntity<CustomExceptionResponse> handleLoginConflictException(LoginConflictException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(handle(ex));
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
