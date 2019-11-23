/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designloginandregister;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author User
 */
public class My_CNK {
    private String url = "jdbc:mysql://ihost.it.kmitl.ac.th:3306/it61070121_prame";
    private static String servername = "ihost.it.kmitl.ac.th:3306";
    private static String username = "it61070121_prame";
    private static String dbname = "it61070121_prame";
    //private static Integer portnumber = 3306;
    private static String password = "prame2512";
    
    
    
    public  Connection ConnectDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("Conneted");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
