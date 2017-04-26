package com.hustlestar.airbnb.service.exc;

/**
 * Created by Yauheni_Malashchytsk on 4/11/2017.
 */
public class UserServiceException extends Exception {
    public UserServiceException() {
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }
}
