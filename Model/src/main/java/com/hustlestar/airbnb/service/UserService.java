package com.hustlestar.airbnb.service;

import com.hustlestar.airbnb.domain.User;
import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public interface UserService {

    User registerUser(String login, String email, String password, String password2) throws ValidationException, UserServiceException;

    User authorizeUser(String login, String password) throws UserServiceException, ValidationException;

    boolean changePassword(String login, String oldPassword, String newPassword, String newPassword2) throws ValidationException, UserServiceException;

    String restorePassword(String login, String email) throws ValidationException, UserServiceException;

    User editProfile(String login, String firstName, String lastName) throws UserServiceException;

}
