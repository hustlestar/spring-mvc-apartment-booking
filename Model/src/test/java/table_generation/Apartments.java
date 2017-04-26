package table_generation;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import static table_generation.GeneratorStart.*;

/**
 * Created by Yauheni_Malashchytsk on 4/5/2017.
 */
public class Apartments {

    public static final String INSERT_STMT =
            "INSERT INTO APARTMENTS (A_ID, A_TITLE, A_ADDRESS, A_CO_ID, A_CI_ID, A_GUESTS)" +
                    " VALUES (null, ?, ?, ?, ?, ?)";

    private static String[] titles =
            {"Beautiful Palace Near Ocean", "Neat Room", "Luxurious Flat", "Cottage", "Big flat",
                    "VIP apartment", "Great bungalow", "Old palace ", "Hi-tech flat", "Big room", "Loft flat",
                    "Big apartment", "Luxurious Apartment"};
    private static String[] addresses =
            {"Some Road, 22", "Reich of 3", "Arbat 24", "Galo 33", "Shibuya 21",
                    "Gwqewqg 221", "Shevchenko 10", "Adasdas 13", "Super road 2", "Christ 88",
                    "King 14", "Yelowpuki 24", "Mickevich 2"};

    public static void main(String[] args) throws Exception {
        apartments();
    }

    public static void apartments() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        java.sql.Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = c.prepareStatement(INSERT_STMT);
        for (int i = 0; i < titles.length; i++) {
            preparedStatement.setString(1, titles[i]);
            preparedStatement.setString(2, addresses[i]);
            preparedStatement.setInt(3, i+1);
            preparedStatement.setInt(4, i+1);
            preparedStatement.setInt(5, new Random().nextInt(10));
            preparedStatement.executeUpdate();
        }
        System.out.println("finished");
        c.close();
    }

}
