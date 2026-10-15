
package passwordgenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoryDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/password_generator";

    private static final String USER = "root";

    // Use your actual MySQL password.
    private static final String PASSWORD =
        "Akmal@123";

    public static void saveHistory(
            int length,
            boolean uppercase,
            boolean lowercase,
            boolean numbers,
            boolean symbols) throws SQLException {

        String sql =
            "INSERT INTO generation_history " +
            "(password_length, uppercase_selected, " +
            "lowercase_selected, numbers_selected, " +
            "symbols_selected) VALUES (?, ?, ?, ?, ?)";

        try (Connection con =
                 DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps =
                 con.prepareStatement(sql)) {

            ps.setInt(1, length);
            ps.setBoolean(2, uppercase);
            ps.setBoolean(3, lowercase);
            ps.setBoolean(4, numbers);
            ps.setBoolean(5, symbols);

            ps.executeUpdate();
        }
    }
}
