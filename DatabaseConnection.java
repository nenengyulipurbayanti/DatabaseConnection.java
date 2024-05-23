// DatabaseConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        // Konstruktor private untuk mencegah instansiasi langsung
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            // Ganti URL, username, dan password dengan yang sesuai dengan konfigurasi database Anda
            String url = "jdbc:mysql://localhost:3306/database_name";
            String username = "your_username";
            String password = "your_password";

            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}