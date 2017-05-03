package com.hustlestar.airbnb.dao.orcl;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
@Repository
public class UserOracle extends AbstractDAO implements UserDAO {

    public static final String LAST_NAME = "U_LAST_NAME";
    public static final String FIRST_NAME = "U_FIRST_NAME";
    public static final String ID = "U_ID";
    public static final String EMAIL = "U_EMAIL";
    public static final String LOGIN = "U_LOGIN";

    public static final String LOGIN_USER =
            "SELECT * FROM USERS WHERE U_LOGIN=? AND U_PASSWORD=?";
    public static final String GET_USER =
            "SELECT * FROM USERS WHERE U_LOGIN=?";
    public static final String INSERT_USER =
            "INSERT INTO USERS" +
                    " (U_ID, U_LOGIN, U_EMAIL, U_PASSWORD, U_FIRST_NAME, U_LAST_NAME)" +
                    " VALUES (null, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PASSWORD =
            "UPDATE USERS SET U_PASSWORD=? WHERE U_LOGIN = ? AND U_PASSWORD = ?";
    public static final String NEW_PASSWORD =
            "UPDATE USERS SET U_PASSWORD=? WHERE U_LOGIN = ?";
    public static final String UPDATE_USER_INFO =
            "UPDATE USERS SET U_FIRST_NAME=?, U_LAST_NAME=? WHERE U_LOGIN = ?";

    //ResourceBundle bundle = ResourceBundle.getBundle("");

    //String sql = bundle.getString();
    public boolean addNewUser(User user) throws DAOException {
        try {
            return getJdbcTemplate().update(
                    INSERT_USER,
                    user.getLogin(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName()
            ) > 0;
        } catch (DataAccessException e) {
            throw new DAOException("User with such params already exists", e);
        }
    }

    public User loginUser(String login, String password) throws DAOException {
        try {
            return getJdbcTemplate().queryForObject(
                    LOGIN_USER,
                    new Object[]{login, password},
                    getRowMapper()
            );
        } catch (DataAccessException e) {
            throw new DAOException("Wrong login or pass", e);
        }
    }

    public User getUser(String login) throws DAOException {
        return getJdbcTemplate().queryForObject(
                GET_USER,
                new Object[]{login},
                getRowMapper()
        );
    }

    public boolean updateUserPassword(String login, String newPassword, String oldPassword) throws DAOException {
        return getJdbcTemplate().update(
                UPDATE_PASSWORD,
                newPassword,
                login,
                oldPassword) > 0;
    }

    public boolean createNewPasswordForUser(String login, String newPassword) throws DAOException {
        return getJdbcTemplate().update(
                NEW_PASSWORD,
                newPassword,
                login) > 0;
    }

    public boolean updateUserInfo(User user) throws DAOException {
        return getJdbcTemplate().update(
                UPDATE_USER_INFO,
                user.getFirstName(),
                user.getLastName(),
                user.getLogin()
        ) > 0;
    }

    private RowMapper<User> getRowMapper() {
        return new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                return user;
            }
        };
    }
}
