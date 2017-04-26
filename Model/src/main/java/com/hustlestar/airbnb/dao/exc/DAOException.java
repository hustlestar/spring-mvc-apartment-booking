package com.hustlestar.airbnb.dao.exc;

/**
 * Created by Yauheni_Malashchytsk on 4/11/2017.
 */
public class DAOException extends Exception {

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
