package com.hustlestar.airbnb;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public class Main {
    public static void main(String[] args) throws DAOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("model-context.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userOracle");
        userDAO.getUser("user4");
        System.out.println(userDAO.hashCode());
    }
}
