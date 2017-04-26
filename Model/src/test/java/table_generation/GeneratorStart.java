package table_generation;

import java.sql.SQLException;

public class GeneratorStart {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@:1521:ORCL";
    public static final String USER = "c##test";
    public static final String PASSWORD = "pass";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Users.users();
        Countries.countries();
        Cities.cities();
        Apartments.apartments();
    }
}
