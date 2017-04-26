package com.hustlestar.airbnb.dao;

import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.User;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public interface UserDAO {

    boolean addNewUser(User user) throws DAOException;

    User loginUser(String login, String password) throws DAOException;

    User getUser(String login) throws DAOException;

    boolean updateUserPassword(String login, String newPassword, String oldPassword) throws DAOException;

    boolean createNewPasswordForUser(String login, String newPassword) throws DAOException;

    boolean updateUserInfo(User user) throws DAOException;
}
