package table_generation;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static table_generation.GeneratorStart.*;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class Users {

    public static final String INSERT_STMT =
            "INSERT INTO USERS " +
                    " (U_ID, U_LOGIN, U_EMAIL, U_PASSWORD, U_FIRST_NAME, U_LAST_NAME) " +
                    " VALUES (null, ?, ?, ?, ?, ?) ";

    public static void main(String[] args) throws Exception {
        users();
    }

    public static void users() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        java.sql.Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = c.prepareStatement(INSERT_STMT);
        for (int i = 2; i < 20; i++) {
            preparedStatement.setString(1, "user" + i);
            preparedStatement.setString(2, "user" + i + "mail.ru");
            preparedStatement.setString(3, "pass" + i);
            preparedStatement.setString(4, "name" + i);
            preparedStatement.setString(5, "surname" + i);
            preparedStatement.executeUpdate();
        }
        System.out.println("finished");
        c.close();
    }
}
