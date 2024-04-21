package org.example.exception;

public class ModelNotFoundException extends CodeAbleException{
    public ModelNotFoundException() {
        super(2, "api.registry.model.by-name.api-responses.404.description");
    }
}
