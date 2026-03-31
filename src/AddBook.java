/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Shalvi Surve
 */
public class AddBook extends javax.swing.JFrame {
    private final LibraryService libraryService = LibraryService.getInstance();
    private JButton btnSearchBook;
    private JButton btnDeleteBook;
    private JButton btnSortBooks;

    public AddBook() {
        initComponents();
        addDynamicControls();
    }

    public void clear() {
        txtid.setText("");
        txtname.setText("");
        txtprice.setText("");
        txtpublisher.setText("");
        txtyear.setText("");
    }

    private void addDynamicControls() {
        btnSearchBook = new JButton("Search");
        btnSearchBook.setBackground(new java.awt.Color(204, 0, 0));
        btnSearchBook.setForeground(new java.awt.Color(242, 242, 242));
        btnSearchBook.addActionListener(evt -> searchBook());
        getContentPane().add(btnSearchBook, new AbsoluteConstraints(710, 215, 100, 30));

        btnDeleteBook = new JButton("Delete");
        btnDeleteBook.setBackground(new java.awt.Color(204, 0, 0));
        btnDeleteBook.setForeground(new java.awt.Color(242, 242, 242));
        btnDeleteBook.addActionListener(evt -> deleteBook());
        getContentPane().add(btnDeleteBook, new AbsoluteConstraints(460, 670, 120, 30));

        btnSortBooks = new JButton("Sort Title");
        btnSortBooks.setBackground(new java.awt.Color(204, 0, 0));
        btnSortBooks.setForeground(new java.awt.Color(242, 242, 242));
        btnSortBooks.addActionListener(evt -> sortBooks());
        getContentPane().add(btnSortBooks, new AbsoluteConstraints(610, 670, 120, 30));
    }

    private Book buildBookFromForm() {
        return new Book(
                txtid.getText(),
                txtname.getText(),
                txtpublisher.getText(),
                txtprice.getText(),
                txtyear.getText(),
                Book.STATUS_NOT_ISSUED);
    }

    private void populateForm(Book book) {
        txtid.setText(book.getId());
        txtname.setText(book.getTitle());
        txtpublisher.setText(book.getPublisher());
        txtprice.setText(book.getPrice());
        txtyear.setText(book.getYear());
    }

    private void searchBook() {
        if (txtid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book ID");
            txtid.requestFocus();
            return;
        }

        try {
            Book book = libraryService.searchBookById(txtid.getText().trim());
            if (book == null) {
                JOptionPane.showMessageDialog(rootPane, "Book not found");
                return;
            }
            populateForm(book);
            JOptionPane.showMessageDialog(rootPane, "Book loaded through the linked list layer");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        if (txtid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book ID to delete");
            txtid.requestFocus();
            return;
        }

        try {
            boolean deleted = libraryService.deleteBook(txtid.getText().trim());
            if (deleted) {
                JOptionPane.showMessageDialog(rootPane, "Book deleted from linked list and database");
                clear();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Book ID not found");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortBooks() {
        try {
            libraryService.sortBooksByTitle();
            JOptionPane.showMessageDialog(rootPane, "Books sorted by title in the linked list");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        txtyear = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtpublisher = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 0, 50, 31));

        txtyear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 320, 36));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Book ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 239, 46));

        txtprice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 320, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Book Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 239, 46));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 670, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Price");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 239, 46));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Publisher");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 239, 46));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Publisher Year");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 239, 46));

        txtpublisher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtpublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 320, 40));

        txtid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 40));

        txtname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 320, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/isue.jpg"))); // NOI18N
        jLabel1.setText("Add Book Details");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 230, 53));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/All Page Backgraound.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book ID");
            txtid.requestFocus();
        } else if (txtname.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book Name");
            txtname.requestFocus();
        } else if (txtpublisher.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Publisher");
            txtpublisher.requestFocus();
        } else if (txtprice.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book Price");
            txtprice.requestFocus();
        } else if (txtyear.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter publisher Year");
            txtyear.requestFocus();
        } else {
            try {
                libraryService.addBook(buildBookFromForm());
                JOptionPane.showMessageDialog(rootPane, "Record Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Duplicate Book", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtpublisher;
    private javax.swing.JTextField txtyear;
    // End of variables declaration//GEN-END:variables
}
