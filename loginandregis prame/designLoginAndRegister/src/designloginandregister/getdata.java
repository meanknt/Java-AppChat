package designloginandregister;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class getdata {

    public String name;
    public static String test;


    public String Showdata() {
        PreparedStatement st;
        String query = "SELECT * FROM `users`";
        LoginForm user = new LoginForm();
        name = user.username;
        
        
        //System.out.println("firtname" + rs.getString(1, name));
        //System.out.println("firtname " + name);

        try {
            My_CNK con = new My_CNK();
            Connection c = con.ConnectDataBase();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(query);
            //st = con.ConnectDataBase().prepareStatement(query);
            //st.setString(1, name);    
            //rs = st.executeQuery();
            
            while (rs.next()) {                
                
                if(rs.getString(1).equals(name)){
                    //System.out.println(name);
                    test = rs.getString(3);
                    //System.out.println("เข้าเเล้ว");
                    System.out.println(test);
                }
                
                
            }
            //System.out.println(st);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return test;
    }


    

    public static void main(String[] args) {
        new getdata().Showdata();
        
    }
}
