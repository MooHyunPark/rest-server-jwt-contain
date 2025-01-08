package com.metacoding.restserver._core.error.exception;

import lombok.Getter;

@Getter
public class Exception404 extends RuntimeException {

    public Exception404(String message) {
        super(message);
    }
}