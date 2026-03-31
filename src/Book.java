public class Book {
    public static final String STATUS_ISSUED = "Issued";
    public static final String STATUS_NOT_ISSUED = "NotIssued";

    private String id;
    private String title;
    private String publisher;
    private String price;
    private String year;
    private String status;
    private String issueDate;
    private String dueDate;
    private String studentId;

    public Book(String id, String title, String publisher, String price, String year, String status) {
        this(id, title, publisher, price, year, status, "", "", "");
    }

    public Book(String id, String title, String publisher, String price, String year, String status,
            String issueDate, String dueDate, String studentId) {
        this.id = sanitize(id);
        this.title = sanitize(title);
        this.publisher = sanitize(publisher);
        this.price = sanitize(price);
        this.year = sanitize(year);
        this.status = sanitize(status).isEmpty() ? STATUS_NOT_ISSUED : sanitize(status);
        this.issueDate = sanitize(issueDate);
        this.dueDate = sanitize(dueDate);
        this.studentId = sanitize(studentId);
    }

    public Book copy() {
        return new Book(id, title, publisher, price, year, status, issueDate, dueDate, studentId);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setTitle(String title) {
        this.title = sanitize(title);
    }

    public void setPublisher(String publisher) {
        this.publisher = sanitize(publisher);
    }

    public void setPrice(String price) {
        this.price = sanitize(price);
    }

    public void setYear(String year) {
        this.year = sanitize(year);
    }

    public void markIssued(String issueDate, String dueDate, String studentId) {
        this.status = STATUS_ISSUED;
        this.issueDate = sanitize(issueDate);
        this.dueDate = sanitize(dueDate);
        this.studentId = sanitize(studentId);
    }

    public void markReturned() {
        this.status = STATUS_NOT_ISSUED;
        this.issueDate = "";
        this.dueDate = "";
        this.studentId = "";
    }

    public void overwrite(Book other) {
        this.id = other.id;
        this.title = other.title;
        this.publisher = other.publisher;
        this.price = other.price;
        this.year = other.year;
        this.status = other.status;
        this.issueDate = other.issueDate;
        this.dueDate = other.dueDate;
        this.studentId = other.studentId;
    }

    private String sanitize(String value) {
        return value == null ? "" : value.trim();
    }
}
