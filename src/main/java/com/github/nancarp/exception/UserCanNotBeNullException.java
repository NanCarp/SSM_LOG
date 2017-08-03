package com.github.nancarp.exception;

/**
 * Created by NanCarp on 2017/8/3.
 */
public class UserCanNotBeNullException extends Exception {
    public UserCanNotBeNullException(String message) {
        super(message);
    }

    public UserCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
