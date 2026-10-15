package passwordgenerator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnectionTest {
    public static void main(String[] args) {

        String url =
            "jdbc:mysql://localhost:3306/password_generator";

        String username = "root";

        // Replace with your actual MySQL password.
        String password = "Akmal@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                url, username, password
            );

            System.out.println(
                "MySQL connected successfully!"
            );

            System.out.println(
                "Database: " +
                con.getCatalog()
            );

            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println(
                "MySQL JDBC driver not found."
            );
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println(
                "MySQL connection failed."
            );
            e.printStackTrace();
        }
    }
}
