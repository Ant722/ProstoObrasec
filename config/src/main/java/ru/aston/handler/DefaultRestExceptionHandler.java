package ru.aston.handler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.LoginConflictException;
import ru.aston.exception.PasswordGenerateTimeException;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class DefaultRestExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CustomExceptionResponse> handleConstraintViolationException(
            ConstraintViolationException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handle(ex));
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        FieldError message = ex.getBindingResult().getFieldError();
        CustomExceptionResponse cer = new CustomExceptionResponse(
                message != null ? message.getDefaultMessage() : "Unknown error at MethodArgumentNotValidException");
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<CustomExceptionResponse> handleDateTimeParseException(DateTimeParseException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handle(ex));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<CustomExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handle(ex));
    }


    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<CustomExceptionResponse> handleValidationException(
            ValidationException ex) {
        String exceptionMessage = ex.getMessage();
        log.error(exceptionMessage);
        log.trace(exceptionMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handle(ex));
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
