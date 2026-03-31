/*
class Book {
    int id;
    String name;
    String publisher;
    int price;
    int year;
    String status;
    Book next;

    // Constructor
    Book(int id, String name, String publisher, int price, int year, String status) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.price = price;
        this.year = year;
        this.status = status;
        this.next = null;
    }
}

class BookList {
    Book head;

    // INSERT (Add new book at end)
    void insert(Book newBook) {
        if (head == null) {
            head = newBook;
        } else {
            Book temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newBook;
        }
    }

    // SEARCH (Linear Search)
    Book search(int id) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // DELETE (Remove book by ID)
    void delete(int id) {
        if (head == null) return;

        // If first node is target
        if (head.id == id) {
            head = head.next;
            return;
        }

        Book temp = head;

        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // DISPLAY (for testing / report)
    void display() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.id + " " + temp.name + " " + temp.status);
            temp = temp.next;
        }
    }
}
    */