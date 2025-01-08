package com.metacoding.restserver._core.error.exception;

import lombok.Getter;

@Getter
public class Exception403 extends RuntimeException {

    public Exception403(String message) {
        super(message);
    }
}