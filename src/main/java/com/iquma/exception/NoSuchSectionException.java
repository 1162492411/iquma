package com.iquma.exception;



/**
 * Created by Mo on 2017/2/25.
 */
public class NoSuchSectionException extends Exception {
    private static final String message = "不存在此版块！";

    @Override
    public String getMessage() {
        return message;
    }

    public NoSuchSectionException() {
    }

    public NoSuchSectionException(String message) {
        super(message);

    }

    public NoSuchSectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchSectionException(Throwable cause) {
        super(cause);
    }

    public NoSuchSectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
