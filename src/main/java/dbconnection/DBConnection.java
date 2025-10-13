package dbconnection;
import java.sql.*;

public class DBConnection
{
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://liftflowserver.database.windows.net:1433;"
                + "database=LiftFlowDB;"
                + "user=wadmin@liftflowserver;"
                + "password=;"
                + "encrypt=true;trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to Azure SQL Database.");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT GETDATE()");
            while (rs.next()) {
                System.out.println("Current Time: " + rs.getString(1));
            }

        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
