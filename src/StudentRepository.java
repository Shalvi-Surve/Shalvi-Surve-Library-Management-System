import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {

    public String findStudentNameById(String studentId) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT name FROM library_db.student WHERE student_id = ?")) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
                return null;
            }
        }
    }
}
