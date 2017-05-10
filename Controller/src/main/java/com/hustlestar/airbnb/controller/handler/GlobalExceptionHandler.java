package com.hustlestar.airbnb.controller.handler;

import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Yauheni_Malashchytsk on 5/5/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public
    @ResponseBody
    String handleUserServiceException(UserServiceException e) {
        return "Handled UserServiceException";
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public
    @ResponseBody
    String handleValidationException(ValidationException e) {
        return "Handled ValidationException";
    }
}
