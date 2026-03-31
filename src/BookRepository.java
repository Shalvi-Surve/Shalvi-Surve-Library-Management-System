import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public void loadAllBooks(BookLinkedList bookList) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, publisher, price, year, status, "
                + "COALESCE(return_date, '') AS return_date, "
                + "COALESCE(studentid, '') AS studentid FROM library_db.book");
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                bookList.insert(mapBook(resultSet));
            }
        }
    }

    public Book findBookById(String id) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, publisher, price, year, status, "
                + "COALESCE(return_date, '') AS return_date, "
                + "COALESCE(studentid, '') AS studentid FROM library_db.book WHERE id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapBook(resultSet);
                }
                return null;
            }
        }
    }

    public Book findIssuedBookByStudentId(String studentId) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, publisher, price, year, status, "
                + "COALESCE(return_date, '') AS return_date, "
                + "COALESCE(studentid, '') AS studentid "
                + "FROM library_db.book WHERE studentid = ? AND status = ?")) {
            statement.setString(1, studentId);
            statement.setString(2, Book.STATUS_ISSUED);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapBook(resultSet);
                }
                return null;
            }
        }
    }

    public void insertBook(Book book) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO library_db.book(id, name, publisher, price, year, status, return_date, studentid) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getPublisher());
            statement.setString(4, book.getPrice());
            statement.setString(5, book.getYear());
            statement.setString(6, book.getStatus());
            statement.setString(7, book.getDueDate());
            statement.setString(8, book.getStudentId());
            statement.executeUpdate();
        }
    }

    public boolean updateIssuedBook(Book book) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE library_db.book SET status = ?, return_date = ?, studentid = ? WHERE id = ?")) {
            statement.setString(1, book.getStatus());
            statement.setString(2, book.getDueDate());
            statement.setString(3, book.getStudentId());
            statement.setString(4, book.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateReturnedBook(Book book) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE library_db.book SET status = ?, return_date = ?, studentid = ? WHERE id = ?")) {
            statement.setString(1, book.getStatus());
            statement.setString(2, book.getDueDate());
            statement.setString(3, book.getStudentId());
            statement.setString(4, book.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(String id) throws SQLException {
        Connection connection = Connect.ConnectToDB();
        if (connection == null) {
            throw new SQLException("Database connection is not available.");
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM library_db.book WHERE id = ?")) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Book mapBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("publisher"),
                resultSet.getString("price"),
                resultSet.getString("year"),
                resultSet.getString("status"),
                "",
                resultSet.getString("return_date"),
                resultSet.getString("studentid"));
    }
}
