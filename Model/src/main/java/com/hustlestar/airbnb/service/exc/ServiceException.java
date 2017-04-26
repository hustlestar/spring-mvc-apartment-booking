package com.hustlestar.airbnb.service.exc;

/**
 * Created by Yauheni_Malashchytsk on 4/11/2017.
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
