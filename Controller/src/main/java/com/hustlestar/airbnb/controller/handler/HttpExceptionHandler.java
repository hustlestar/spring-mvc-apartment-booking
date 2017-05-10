package com.hustlestar.airbnb.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Yauheni_Malashchytsk on 5/10/2017.
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler()
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException e) {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Sorry! No handler for such url found!");
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler()
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Sorry! Wrong parameters of request!");
        model.setViewName("error");
        return model;
    }


}
