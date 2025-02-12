package org.example.exception;

import lombok.Getter;


@Getter
public class CodeAbleException extends RuntimeException{
    private final int code;

    public CodeAbleException( int code, String message) {
        super(message);
        this.code = code;
    }

}
