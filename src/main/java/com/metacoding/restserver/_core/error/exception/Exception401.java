package com.metacoding.restserver._core.error.exception;

import lombok.Getter;

@Getter
public class Exception401 extends RuntimeException {

    public Exception401(String message) {
        super(message);
    }
}