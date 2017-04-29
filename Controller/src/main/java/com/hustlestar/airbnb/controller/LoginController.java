package com.hustlestar.airbnb.controller;

import com.hustlestar.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yauheni_Malashchytsk on 4/26/2017.
 */
@Controller
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
}
