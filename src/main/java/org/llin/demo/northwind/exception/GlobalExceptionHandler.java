package org.llin.demo.northwind.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catches validation logic failures, such as our duplicate email check.
     * Returns an HTTP 400 Bad Request or HTTP 409 Conflict based on business rules.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT; // 409 Conflict fits data uniqueness issues perfectly
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, status);
    }

    /**
     * Catches instances where downstream REST calls fail with a 409 Conflict status code.
     */
    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    public ResponseEntity<ErrorResponse> handleRestClientConflict(HttpClientErrorException.Conflict ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "The requested resource already exists (Database Constraint Violation).",
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, status);
    }

    /**
     * Catch-all fallback for any unexpected system crashes (HTTP 500 Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "An unexpected internal error occurred.", // Mask raw stacktrace detail from public visibility
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(error, status);
    }
}
