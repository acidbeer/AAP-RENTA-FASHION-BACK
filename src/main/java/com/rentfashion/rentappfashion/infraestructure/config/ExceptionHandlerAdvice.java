package com.rentfashion.rentappfashion.infraestructure.config;

import com.rentfashion.rentappfashion.domain.exception.BusinessRuleException;
import com.rentfashion.rentappfashion.domain.exception.NotFoundException;
import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(body("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> validation(ValidationException ex) {
        return ResponseEntity.badRequest().body(body("VALIDATION", ex.getMessage()));
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> business(BusinessRuleException ex) {
        return ResponseEntity.status(409).body(body("BUSINESS_RULE", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> generic(Exception ex) {
        return ResponseEntity.status(500).body(body("ERROR", "Error interno"));
    }

    private Map<String, Object> body(String code, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "code", code,
                "message", message
        );
    }
}
