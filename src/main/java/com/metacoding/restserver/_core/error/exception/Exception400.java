package com.metacoding.restserver._core.error.exception;

import lombok.Getter;

@Getter
public class Exception400 extends RuntimeException {

    public Exception400(String message) {
        super(message);
    }
}