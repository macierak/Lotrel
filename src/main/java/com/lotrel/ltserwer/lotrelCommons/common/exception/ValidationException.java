package com.lotrel.ltserwer.lotrelCommons.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException() {
        super("Validation Exception!");
    }
    public ValidationException(String message) {
        super(message);
    }
}
