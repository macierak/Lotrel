package com.lotrel.ltserwer.lotrelCommons.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException() {
        super("You do not have permission to perform this operation!");
    }
}
