package com.hustlestar.airbnb;

import com.hustlestar.airbnb.dao.UserDAO;
import com.hustlestar.airbnb.dao.exc.DAOException;
import com.hustlestar.airbnb.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni_Malashchytsk on 4/3/2017.
 */
public class Main {
    public static void main(String[] args) throws DAOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, dear user!\n");
        sb.append("\n");
        sb.append("Password for your account was successfully changed\n");
        sb.append("It's ");
        sb.append("lololo");
        userService.sendEmail("New password", sb.toString(), "hustlerka@mail.ru");

    }
}
