package org.example.exception;

public class ResponseDataIsNotValidException extends CodeAbleException{
    public ResponseDataIsNotValidException() {
        super(2, "api.registry.model.by-price-between.api-responses.404.description");
    }
}