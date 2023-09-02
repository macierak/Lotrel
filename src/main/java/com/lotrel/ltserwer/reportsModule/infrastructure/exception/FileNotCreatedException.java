package com.lotrel.ltserwer.reportsModule.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileNotCreatedException extends RuntimeException {

    public FileNotCreatedException(String name) {
        super("Csv " + name + " was not create.");
    }


}
