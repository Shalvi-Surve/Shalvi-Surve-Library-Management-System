/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Shalvi Surve
 */
public class Connect {
    static Connection con=null;
    public static Connection ConnectToDB() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                     "root",
                     "1234");
             System.out.println("DB Connected Successfully");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
}
}
