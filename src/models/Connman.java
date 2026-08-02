package models;
import java.sql.*;

public class Connman {
    private static String server = "localhost";
    private static String port = "1433";
    private static String database = "PracticasProgra";
    private static String user = "sa";
    private static String password = "d0_n0t_be_l@zy_h3r3";

    public static String getStringConnection()
    {
        return String.format(
                "jdbc:sqlserver:"
                + "//%s"
                + ":%s;"
                + "database=%s;"
                + "user=%s;"
                + "password=%s;"
                + "encrypt=true;"
                + "trustServerCertificate=true;",
                server, port, database, user, password
        );
    }

    public static Connection getConnection() throws SQLException
    { return DriverManager.getConnection(getStringConnection()); }
}
