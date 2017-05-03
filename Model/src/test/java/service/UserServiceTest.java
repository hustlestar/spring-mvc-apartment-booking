package service;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.domain.User;
import com.hustlestar.airbnb.domain.dto.UserDto;
import com.hustlestar.airbnb.service.UserService;
import com.hustlestar.airbnb.service.exc.UserServiceException;
import com.hustlestar.airbnb.service.exc.ValidationException;
import com.hustlestar.airbnb.service.impl.UserServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Yauheni_Malashchytsk on 4/4/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test(expected = ValidationException.class)
    public void authorizeUser1() throws UserServiceException, ValidationException, DAOException {

        User user = new User();
        String login = "";
        String password = "";

        when(userDAO.loginUser(login, password)).
                thenReturn(user);

        User result = userService.authorizeUser(login, password);

        assertEquals(user, result);
        assertSame(user, result);
        verify(userDAO, never()).loginUser(login, password);

    }

    @Test
    public void authorizeUser2() throws UserServiceException, ValidationException, DAOException {

        User user = new User();
        String login = "login";
        String password = "right";
        user.setLogin(login);

        when(userDAO.loginUser(login, password)).
                thenReturn(user);

        User result = userService.authorizeUser(login, password);

        assertEquals(user, result);
        assertSame(user, result);
        verify(userDAO).loginUser(login, password);

    }

    @Test(expected = UserServiceException.class)
    public void authorizeUser3() throws UserServiceException, ValidationException, DAOException {

        User user = new User();
        String login = "login";
        String password = "wrong";
        user.setLogin(login);

        when(userDAO.loginUser(login, password)).
                thenThrow(DAOException.class);

        User result = userService.authorizeUser(login, password);

        assertEquals(user, result);
        assertSame(user, result);
        verify(userDAO).loginUser(login, password);

    }

    @Test(expected = UserServiceException.class)
    public void registerUser1() throws DAOException, UserServiceException, ValidationException {
        User user = new User();
        String login = "duplicate";
        String email = "duplicate@mail.ru";
        String password = "duplicate";
        String password2 = "duplicate";

        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setPassword2(password2);

        doThrow(DAOException.class).when(userDAO).addNewUser(user);

        User result = userService.registerUser(userDto);

        assertNull(result);
        verify(userDAO).addNewUser(user);
    }


    @Test(expected = ValidationException.class)
    public void registerUser2() throws UserServiceException, ValidationException, DAOException {
        User user = new User();
        String login = "invalid";
        String email = "login@mail.ru";
        String password = "right";
        String password2 = "wrong";

        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setPassword2(password2);


        User result = userService.registerUser(userDto);

        assertNull(result);
        verify(userDAO, never()).addNewUser(user);
    }

    @Test
    public void registerUser3() throws UserServiceException, ValidationException, DAOException {
        User user = new User();
        String login = "valid";
        String email = "valid@mail.ru";
        String password = "valid";
        String password2 = "valid";

        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setPassword2(password2);

        when(userDAO.addNewUser(user)).thenReturn(true);
        User result = userService.registerUser(userDto);

        assertEquals(user, result);
        verify(userDAO).addNewUser(user);
    }

    @Test
    public void changePassword1() throws DAOException, UserServiceException, ValidationException {
        String login = "valid";
        String newPassword = "newpass";
        String newPassword2 = "newpass";
        String oldPassword = "oldpass";

        when(userDAO.updateUserPassword(login, newPassword, oldPassword)).thenReturn(true);
        boolean result = userService.changePassword(login, oldPassword, newPassword, newPassword2);

        assertEquals(true, result);
        verify(userDAO).updateUserPassword(login, newPassword, oldPassword);
    }

    @Test(expected = ValidationException.class)
    public void changePassword2() throws DAOException, UserServiceException, ValidationException {
        String login = "valid";
        String newPassword = "valid";
        String newPassword2 = "INVALID";
        String oldPassword = "valid";

        boolean result = userService.changePassword(login, oldPassword, newPassword, newPassword2);

        assertEquals(false, result);
        verify(userDAO, never()).updateUserPassword(login, newPassword, oldPassword);
    }

    @Test(expected = UserServiceException.class)
    public void changePassword3() throws DAOException, UserServiceException, ValidationException {
        String login = "valid";
        String newPassword = "valid";
        String newPassword2 = "valid";
        String oldPassword = "INVALID";

        doThrow(DAOException.class).when(userDAO).updateUserPassword(login, newPassword, oldPassword);
        boolean result = userService.changePassword(login, oldPassword, newPassword, newPassword2);

        assertEquals(false, result);
        verify(userDAO).updateUserPassword(login, newPassword, oldPassword);
    }


    @Test
    public void restorePassword1() throws DAOException, ValidationException, UserServiceException {
        String login = "valid";
        String email = "valid@mail.ru";

        when(userDAO.createNewPasswordForUser(eq(login), anyString())).thenReturn(true);
        String result = userService.restorePassword(login, email);

        assertNotNull(result);
        verify(userDAO).createNewPasswordForUser(eq(login), anyString());
    }

    @Test(expected = UserServiceException.class)
    public void restorePassword2() throws DAOException, ValidationException, UserServiceException {
        String login = "invalid";
        String email = "invalid@mail.ru";

        when(userDAO.createNewPasswordForUser(eq(login), anyString())).thenThrow(DAOException.class);
        String result = userService.restorePassword(login, email);

        assertNull(result);
        verify(userDAO).createNewPasswordForUser(eq(login), anyString());
    }

    @Test
    public void editProfile1() throws DAOException, UserServiceException {
        User user = new User();
        String update = "valid";

        when(userDAO.getUser(update)).thenReturn(user);


        user.setLogin(update);
        user.setFirstName(update);
        user.setLastName(update);

        when(userDAO.updateUserInfo(user)).thenReturn(true);
        User result = userService.editProfile(update, update, update);

        assertSame(user, result);
        verify(userDAO, times(1)).getUser(update);
        verify(userDAO).updateUserInfo(user);
    }

    @Test
    public void editProfile2() throws DAOException, UserServiceException {
        User user = new User();
        String update = "valid";

        when(userDAO.getUser(update)).thenReturn(user);

        user.setLogin(update);
        user.setFirstName(update);
        user.setLastName(update);

        when(userDAO.updateUserInfo(user)).thenReturn(false);
        User result = userService.editProfile(update, update, update);

        assertSame(user, result);
        verify(userDAO, times(2)).getUser(update);
        verify(userDAO).updateUserInfo(user);
    }
}
