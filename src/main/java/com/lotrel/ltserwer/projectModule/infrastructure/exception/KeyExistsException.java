package com.lotrel.ltserwer.projectModule.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeyExistsException extends RuntimeException {

    public KeyExistsException(String key) {
        super("Key "+ key +" currently exists in database");
    }
}
