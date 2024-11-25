import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/notesdb", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
