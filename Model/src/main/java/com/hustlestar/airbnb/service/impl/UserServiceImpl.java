package com.hustlestar.airbnb.service.impl;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.User;
import com.hustlestar.airbnb.domain.dto.UserDto;
import com.hustlestar.airbnb.service.UserService;
import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;
import com.hustlestar.airbnb.service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User registerUser(UserDto userDto) throws ValidationException, UserServiceException {
        User user = null;
        try {
            if (!Validation.validateLogin(userDto.getLogin())
                    || !Validation.validatePassword(userDto.getPassword())
                    || !Validation.validatePassword(userDto.getPassword2())
                    || !Validation.validateEmail(userDto.getEmail())
                    || !userDto.getPassword().equals(userDto.getPassword2())) {
                throw new ValidationException("Password and login should be at least 5 symbols including letters and digits");
            } else {
                user = new User();
                user.setLogin(userDto.getLogin());
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
            }
            if (userDAO.addNewUser(user)) {
                return user;
            } else{
                throw new UserServiceException("Cant create user with such params");
            }
        } catch (DAOException e) {
            throw new UserServiceException("User with such login already exists", e);
        }
    }

    public User authorizeUser(String login, String password) throws UserServiceException, ValidationException {
        User user;
        try {
            if (!Validation.validateLogin(login) || !Validation.validatePassword(password)) {
                throw new ValidationException("Password and login should be at least 5 symbols including letters and digits");
            }
            user = userDAO.loginUser(login, password);
        } catch (DAOException e) {
            throw new UserServiceException("Wrong login or pass", e);
        }
        return user;
    }

    public boolean changePassword(String login, String oldPassword, String newPassword, String newPassword2) throws ValidationException, UserServiceException {
        try {
            if (!Validation.validateLogin(login) || !Validation.validatePassword(oldPassword)
                    || !Validation.validatePassword(newPassword)
                    || !Validation.validatePassword(newPassword2)
                    || !newPassword.equals(newPassword2)) {
                throw new ValidationException("Password and login should be at least 5 symbols including letters and digits");
            }
            return userDAO.updateUserPassword(login, newPassword, oldPassword);
        } catch (DAOException e) {
            throw new UserServiceException("Wrong login or pass", e);
        }
    }

    public String restorePassword(String login, String email) throws ValidationException, UserServiceException {
        String newPassword = passwordGenerator();
        try {
            if (!Validation.validateLogin(login) ||
                    !Validation.validateEmail(email)) {
                throw new ValidationException("Email or login are incorrect");
            }
            if (userDAO.createNewPasswordForUser(login, newPassword)) {
                sendNewPassword(email, newPassword);
                return newPassword;
            }
        } catch (DAOException e) {
            throw new UserServiceException("Cannot restore password for user", e);
        }
        return null;
    }

    @Transactional
    public User editProfile(String login, String firstName, String lastName) throws UserServiceException {
        try {
            User user = userDAO.getUser(login);
            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            if (userDAO.updateUserInfo(user)) {
                return user;
            } else {
                return userDAO.getUser(login);
            }
        } catch (DAOException e) {
            throw new UserServiceException("Cannot update user data", e);
        }
    }

    private void sendNewPassword(String email, String newPassword) {
        //send email to user
    }

    private String passwordGenerator() {
        return "newPassword";
    }
}
