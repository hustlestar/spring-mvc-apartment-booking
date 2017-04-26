package com.hustlestar.airbnb.service.exc;

/**
 * Created by Yauheni_Malashchytsk on 4/11/2017.
 */
public class ValidationException extends ServiceException {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
