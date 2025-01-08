package com.metacoding.restserver._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> Resp<T> success(T data) {
        return new Resp<>(true, "성공", data);
    }

    public static <T> Resp<T> fail(String message) {
        return new Resp<>(false, message, null);
    }
}