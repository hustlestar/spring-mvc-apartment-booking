package dao;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.domain.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by Yauheni_Malashchytsk on 4/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseTearDown(value = "/datasets/users/addNewUserTestEXP.xml", type = DatabaseOperation.DELETE_ALL)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    @DatabaseSetup(value = "/datasets/users/addNewUserTestSMPL.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/datasets/users/addNewUserTestEXP.xml")
    public void addNewUserTest() throws Exception {
        User user = new User();
        user.setLogin("second");
        user.setEmail("second@mail.ru");
        user.setFirstName("Sec");
        user.setLastName("Ond");
        user.setPassword("second");
        userDAO.addNewUser(user);
    }

    @Test
    @DatabaseSetup(value = "/datasets/users/getUserTestSMPL.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void getUserTest() throws Exception {
        String login = "admin";
        String pass = "admin";
        User user = userDAO.loginUser(login, pass);
        System.out.println(user);
        Assert.assertEquals("admin@mail.ru", user.getEmail());
    }

    @Test
    @DatabaseSetup(value = "/datasets/users/updateUserPasswordTestSMPL.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/datasets/users/updateUserPasswordTestEXP.xml")
    public void updateUserPasswordTest() throws Exception {
        String login = "second";
        String oldPassword = "second";
        String newPassword = "testpass";
        boolean result = userDAO.updateUserPassword(login, newPassword, oldPassword);
        Assert.assertEquals(true, result);
    }

    @Test
    @DatabaseSetup(value = "/datasets/users/updateUserInfoSMPL.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/datasets/users/updateUserInfoEXP.xml")
    public void updateUserInfoTest() throws Exception {
        User user = new User();
        user.setFirstName("dodo");
        user.setLastName("bingo");
        user.setLogin("admin");
        boolean result = userDAO.updateUserInfo(user);
        Assert.assertEquals(true, result);
    }


}
