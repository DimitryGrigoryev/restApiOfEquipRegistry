package org.example.configuration;

import org.example.exception.CodeAbleException;
import org.example.exception.EquipTypeNotFoundException;
import org.example.exception.EquipmentNotFoundException;
import org.example.exception.ModelNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
public class ErrorControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    public final PropertyResolverUtils propertyResolverUtils;

    public ErrorControllerAdvice(PropertyResolverUtils propertyResolverUtils) {
        this.propertyResolverUtils = propertyResolverUtils;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(LocalDateTime.now(), propertyResolverUtils.resolve("api.registry.server.error", Locale.getDefault()), 1));
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException (ModelNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(LocalDateTime.now(), propertyResolverUtils.resolve(exception.getMessage(),Locale.getDefault()), exception.getCode()));
    }
    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEquipmentNotFoundException (EquipmentNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(LocalDateTime.now(), propertyResolverUtils.resolve(exception.getMessage(),Locale.getDefault()), exception.getCode()));
    }

    @ExceptionHandler(EquipTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDishTypeNotFoundException(EquipTypeNotFoundException exception) {
        return handleCodeAbleException(exception);
    }

    protected ResponseEntity<ErrorResponse> handleCodeAbleException(CodeAbleException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(exception));
    }

    protected ErrorResponse body(CodeAbleException exception) {
        return body(message(exception.getMessage()), exception.getCode());
    }

    private ErrorResponse body(String message, Integer code) {
        return new ErrorResponse(LocalDateTime.now(), message, code);
    }

    private String message(String property) {
        return this.propertyResolverUtils.resolve(property, Locale.getDefault());
    }

    public record ErrorResponse(LocalDateTime timeStamp, String message, int code){
    }
}
