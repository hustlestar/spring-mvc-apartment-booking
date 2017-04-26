package table_generation;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static table_generation.GeneratorStart.*;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class Countries {

    public static final String INSERT_STMT =
            "INSERT INTO COUNTRIES (CO_ID, CO_TITLE)" +
                    " VALUES (null, ?)";

    private static String[] countries =
            {"USA", "Germany", "Russia", "Belarus", "Japan",
                    "China", "Ukraine", "France", "Canada", "Brazil", "Sweden", "Finland", "Poland"};

    public static void main(String[] args) throws Exception {
        countries();
    }

    public static void countries() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        java.sql.Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = c.prepareStatement(INSERT_STMT);
        for (int i = 0; i < countries.length; i++) {
            preparedStatement.setString(1, countries[i]);
            preparedStatement.executeUpdate();
        }
        System.out.println("finished");
        c.close();
    }

}
