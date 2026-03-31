import java.sql.SQLException;

public class LibraryService {
    private static final LibraryService INSTANCE = new LibraryService();

    private final BookLinkedList bookList = new BookLinkedList();
    private final BookRepository bookRepository = new BookRepository();
    private final StudentRepository studentRepository = new StudentRepository();
    private boolean booksLoaded;

    private LibraryService() {
    }

    public static LibraryService getInstance() {
        return INSTANCE;
    }

    public synchronized void preloadBooks() throws SQLException {
        if (!booksLoaded) {
            bookRepository.loadAllBooks(bookList);
            booksLoaded = true;
        }
    }

    public synchronized Book addBook(Book book) throws SQLException {
        preloadBooks();
        Book existingBook = bookList.searchById(book.getId());
        if (existingBook != null) {
            throw new IllegalArgumentException("Book ID already exists in the linked list.");
        }

        bookRepository.insertBook(book);
        bookList.insert(book);
        return book;
    }

    public synchronized Book searchBookById(String bookId) throws SQLException {
        preloadBooks();
        Book book = bookList.searchById(bookId);
        if (book != null) {
            return book;
        }

        Book bookFromDatabase = bookRepository.findBookById(bookId);
        if (bookFromDatabase != null) {
            bookList.insert(bookFromDatabase);
            return bookList.searchById(bookId);
        }
        return null;
    }

    public synchronized Book searchBookByTitle(String title) throws SQLException {
        preloadBooks();
        return bookList.searchByTitle(title);
    }

    public synchronized Book searchIssuedBookByStudentId(String studentId) throws SQLException {
        preloadBooks();
        Book book = bookList.searchIssuedBookByStudentId(studentId);
        if (book != null) {
            return book;
        }

        Book bookFromDatabase = bookRepository.findIssuedBookByStudentId(studentId);
        if (bookFromDatabase != null) {
            bookList.insert(bookFromDatabase);
            return bookList.searchIssuedBookByStudentId(studentId);
        }
        return null;
    }

    public synchronized boolean deleteBook(String bookId) throws SQLException {
        preloadBooks();
        boolean deletedFromDatabase = bookRepository.deleteBook(bookId);
        if (deletedFromDatabase) {
            bookList.deleteById(bookId);
        }
        return deletedFromDatabase;
    }

    public synchronized Book issueBook(String bookId, String studentId, String issueDate, String dueDate)
            throws SQLException {
        preloadBooks();
        Book book = searchBookById(bookId);
        if (book == null) {
            return null;
        }

        if (Book.STATUS_ISSUED.equalsIgnoreCase(book.getStatus())) {
            throw new IllegalStateException("Book is already issued.");
        }

        book.markIssued(issueDate, dueDate, studentId);
        bookRepository.updateIssuedBook(book);
        return book;
    }

    public synchronized Book returnBook(String bookId) throws SQLException {
        preloadBooks();
        Book book = searchBookById(bookId);
        if (book == null) {
            return null;
        }

        book.markReturned();
        bookRepository.updateReturnedBook(book);
        return book;
    }

    public synchronized String findStudentNameById(String studentId) throws SQLException {
        return studentRepository.findStudentNameById(studentId);
    }

    public synchronized void sortBooksByTitle() throws SQLException {
        preloadBooks();
        bookList.bubbleSortByTitle();
    }
}
