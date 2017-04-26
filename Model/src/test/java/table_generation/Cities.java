package table_generation;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static table_generation.GeneratorStart.*;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class Cities {

    public static final String INSERT_STMT =
            "INSERT INTO CITIES (CI_ID, CI_TITLE, CI_CO_ID)" +
                    " VALUES (null, ?, ?)";

    private static String[] cities =
            {"Los Angeles", "Berlin", "Moscow", "Minsk", "Tokyo",
                    "Beijing", "Kiev", "Paris", "Toronto", "Rio", "Stockholm", "Helsinki", "Warsaw"};

    public static void main(String[] args) throws Exception {
        cities();
    }

    public static void cities() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        java.sql.Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = c.prepareStatement(INSERT_STMT);
        for (int i = 0; i < cities.length; i++) {
            preparedStatement.setString(1, cities[i]);
            preparedStatement.setInt(2, i+1);
            preparedStatement.executeUpdate();
        }
        System.out.println("finished");
        c.close();
    }

}
