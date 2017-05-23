package com.hustlestar.airbnb.controller;

import com.hustlestar.airbnb.domain.User;
import com.hustlestar.airbnb.domain.dto.UserDto;
import com.hustlestar.airbnb.service.UserService;
import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.Callable;

/**
 * Created by Yauheni_Malashchytsk on 4/26/2017.
 */
@Controller
public class LoginController {

    private static final String PLEASE_LOGIN_TO_VIEW_THIS_PAGE = "Please login to view this page";

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register", "userDto", new UserDto());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute UserDto userDto,
                                 HttpSession session) throws UserServiceException, ValidationException {
        User user = userService.registerUser(userDto);
        session.setAttribute("user", user);
        return new ModelAndView("redirect:welcome", "user", user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        HttpSession session) throws UserServiceException, ValidationException {
        User user = userService.authorizeUser(login, password);
        session.setAttribute("user", user);
        return "redirect:welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(@SessionAttribute(required = false) User user, ModelMap modelMap) {
        if (user != null) {
            System.out.println(user);
            return "welcome";
        } else {
            modelMap.put("error", PLEASE_LOGIN_TO_VIEW_THIS_PAGE);
            return "error";
        }
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(
            @SessionAttribute(required = false) User user,
            ModelMap modelMap) throws UserServiceException {
        if (user != null) {
            return "editProfile";
        } else {
            modelMap.put("error", PLEASE_LOGIN_TO_VIEW_THIS_PAGE);
            return "error";
        }
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String editProfile(
            @SessionAttribute(required = false) User user,
            @RequestParam String firstName,
            @RequestParam String lastName,
            HttpSession session,
            ModelMap modelMap) throws UserServiceException {
        if (user != null) {
            User updatedUser = userService.editProfile(user.getLogin(), firstName, lastName);
            session.setAttribute("user", updatedUser);
            return "redirect:welcome";
        } else {
            modelMap.put("error", PLEASE_LOGIN_TO_VIEW_THIS_PAGE);
            return "error";
        }
    }

    @RequestMapping(value = "/restorePassword", method = RequestMethod.GET)
    public String restorePassword() {
        return "restorePassword";
    }

    @RequestMapping(value = "/restorePassword", method = RequestMethod.POST)
    public Callable<String> restorePassword(
            @RequestParam final String login,
            @RequestParam final String email,
            final HttpSession session) {

        return new Callable<String>() {
            public String call() throws Exception {
                User user = userService.findUserByEmail(email);
                String newPassword = userService.restorePassword(login, email);
                session.setAttribute("newPassword", newPassword);
                return "redirect:result";
            }
        };
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword() {
        return "changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Callable<String> changePassword(
            @RequestParam final String oldPassword,
            @RequestParam final String newPassword,
            @RequestParam final String newPassword2,
            @SessionAttribute(required = false) final User user,
            final HttpSession session) {

        return new Callable<String>() {
            public String call() throws Exception {
                Boolean result = userService.changePassword(user.getLogin(), oldPassword, newPassword, newPassword2);
                session.setAttribute("result", result);
                return "redirect:result";
            }
        };
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result() {
        return "password";
    }

    @RequestMapping(value = "/logout")
    public String logOut(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
