package com.hustlestar.airbnb.controller;

import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Yauheni_Malashchytsk on 5/5/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public
    @ResponseBody
    String handleUserServiceException(UserServiceException e) {
        return "Handled UserServiceException";
    }

    @ExceptionHandler
    public
    @ResponseBody
    String handleValidationException(ValidationException e) {
        return "Handled ValidationException";
    }
}
