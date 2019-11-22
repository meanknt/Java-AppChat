/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author kit
 */
public class MainFrame {
    JFrame frm = new JFrame();
    JPanel pnel = new JPanel();
    JTextArea ar = new JTextArea();
    private int num = 10;
    private String txt1 = "asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private String txt2 = "qwcccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
    
    //private JPanel test;
    ArrayList<JLabel> arrayJP = new  ArrayList<JLabel>();
    
    public void draw(){
        frm.setSize(600, 500);
        frm.setLayout(null);
        ar.setBounds(29, 30, 300, 100);
        ar.setLineWrap(true);
//        pnel.setLayout(new GridLayout(29, 1));
//        for (int i = 1; i < 30; i++) {
//            JLabel lbel = new JLabel();
//            //lbel.setText("<html><p>"+txt1+"<br>"+txt2+"<br>"+i+"<br></p></html>");
//            //lbel.setHorizontalAlignment(JLabel.CENTER);
//            lbel.setText(txt1+txt2);
//            arrayJP.add(lbel);
//        }
//        for(JLabel test : arrayJP){
//            //pnel.setSize(500, 20);
//            pnel.add(test);
//        }
//        JScrollPane sc = new JScrollPane(pnel);
//        //sc.setSize(600, 500);
//        //sc.setHorizontalScrollBar(null);
//        frm.add(sc);
        JScrollPane jp = new JScrollPane(ar);
        jp.setHorizontalScrollBar(null);
        frm.add(jp);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    public static void main(String[] args) {
        new MainFrame().draw();
    }
}

