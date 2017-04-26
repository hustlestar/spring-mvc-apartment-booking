package to_xml;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Yauheni_Malashchytsk on 4/4/2017.
 */
public class ExportDbToXml {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@:1521:ORCL";
    public static final String USER = "c##airbnb";
    public static final String PASSWORD = "pass";

    public static void main(String[] args) throws Exception {
        Class.forName(JDBC_DRIVER);
        Connection c  = DriverManager.getConnection(URL, USER, PASSWORD);
        IDatabaseConnection connection = new DatabaseConnection(c);

        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable("cities", "SELECT * FROM cities");
        FlatXmlDataSet.write(dataSet, new FileOutputStream("citiesSMPL.xml"));
    }
}
