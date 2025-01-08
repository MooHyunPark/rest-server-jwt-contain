package com.metacoding.restserver._core.error.exception;

import lombok.Getter;

@Getter
public class Exception500 extends RuntimeException {

    public Exception500(String message) {
        super(message);
    }
}