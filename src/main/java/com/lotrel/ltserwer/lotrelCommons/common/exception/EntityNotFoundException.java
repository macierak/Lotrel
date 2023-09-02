package com.lotrel.ltserwer.lotrelCommons.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Requested entity was not found in Database");
    }

    public EntityNotFoundException(Class ob) {
        super("Entity: " + ob.getName() + " was not found in Database");
    }


}
