public class BookLinkedList {
    private static class Node {
        private Book data;
        private Node next;

        private Node(Book data) {
            this.data = data;
        }
    }

    private Node head;

    public void insert(Book book) {
        Node newNode = new Node(book.copy());
        if (head == null || compareIds(book.getId(), head.data.getId()) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null && compareIds(book.getId(), current.next.data.getId()) > 0) {
            current = current.next;
        }

        if (current.data.getId().equalsIgnoreCase(book.getId())) {
            current.data.overwrite(book);
            return;
        }

        if (current.next != null && current.next.data.getId().equalsIgnoreCase(book.getId())) {
            current.next.data.overwrite(book);
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public Book searchById(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id.trim())) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Book searchByTitle(String title) {
        Node current = head;
        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title.trim())) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Book searchIssuedBookByStudentId(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId.trim())
                    && Book.STATUS_ISSUED.equalsIgnoreCase(current.data.getStatus())) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean deleteById(String id) {
        if (head == null) {
            return false;
        }

        if (head.data.getId().equalsIgnoreCase(id.trim())) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getId().equalsIgnoreCase(id.trim())) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void bubbleSortByTitle() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.getTitle().compareToIgnoreCase(current.next.data.getTitle()) > 0) {
                    Book temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    private int compareIds(String leftId, String rightId) {
        try {
            return Integer.compare(Integer.parseInt(leftId.trim()), Integer.parseInt(rightId.trim()));
        } catch (NumberFormatException ex) {
            return leftId.compareToIgnoreCase(rightId);
        }
    }
}
