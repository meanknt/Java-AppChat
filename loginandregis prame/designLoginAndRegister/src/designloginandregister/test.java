/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designloginandregister;

import javax.swing.*;

/**
 *
 * @author User
 */
public class test {
    private JFrame frm = new JFrame();
    private JLabel lbel = new JLabel();
    private String name;
    
    public void draw(){
        getdata n = new getdata();
        name = n.Showdata();
        System.out.println(name);
        lbel.setText(name);
        frm.add(lbel);
        frm.setSize(400, 400);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
