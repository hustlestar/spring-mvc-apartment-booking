package com.hustlestar.airbnb.service.utils;

/**
 * Created by Yauheni_Malashchytsk on 4/11/2017.
 */
public class Validation {

    private Validation() {
    }

    public static boolean validateString(String title) {
        return title.matches("[A-Za-z 0-9]{3,}");
    }

    public static boolean validateLogin(String login) {
        return login.matches("[A-Za-z0-9]{5,}");
    }

    public static boolean validatePassword(String password) {
        return password.matches("[A-Za-z0-9]{5,}");
    }

    public static boolean validateEmail(String email) {
        return email.matches("[A-Za-z0-9@.]{5,}");
    }
}
