import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainConnection {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://92.53.77.198:5432/bookstore", "postgres", "1984");
            statement = connection.prepareStatement("INSERT INTO storage (id, author, title, genre, amount) " + "VALUES (DEFAULT, ?, ?, ?, ?)");
            statement.setString(1, "Stephen King");
            statement.setString(2, "The Shining");
            statement.setString(3, "Novel");
            statement.setInt(4, 205);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed a db operation: " + e.getMessage());
            System.exit(1);
        } finally {
            if (connection != null) {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed closing db connection: " + e.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}
