/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument;


/**
 *
 * @author kit
 */
public class MainFrame {

    ArrayList<JTextArea> arrayTA = new ArrayList<JTextArea>();
    JFrame frm = new JFrame("Work");
    JPanel pnel, pnelR, pnelL, pnelF;
    JTextArea ar, ar1, ar2, ar3, ar4, ar5;
    JButton btn;
    private int num = 7;
    private String txt1 = "Test Topic";
    private String txt3 = "Test Topic\n";
    private String txt4 = "Test Topic\n";
    private String txt5 = "Test Topic\n";
    private String txt6 = "Test Topic";
    private String txt2 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";

    public void draw() {
        btn = new JButton("Add Work");
        pnel = new JPanel();
        pnelR = new JPanel();
        pnelL = new JPanel();
        pnelF = new JPanel();
        ar1 = new JTextArea();
        ar2 = new JTextArea();
        ar3 = new JTextArea();
        ar4 = new JTextArea();
        ar5 = new JTextArea();
        frm.setSize(600, 500);
        pnelR.setBackground(Color.YELLOW);
        pnelL.setBackground(Color.YELLOW);
        pnelF.setBackground(Color.YELLOW);
        pnel.repaint();
        pnel.setLayout(new GridLayout(num, 1, 10, 20));
        pnel.setBackground(Color.DARK_GRAY);
        
        
        for (int i = 0; i < num; i++) {
            ar = new JTextArea();
            ar.setFont(new Font("Arial, sans-serif", Font.PLAIN,15));
            ar.setAutoscrolls(true);
            ar.setLineWrap(true);
            ar.setEnabled(true);
            ar.setEditable(true);
            ar.setText(txt1+txt2+txt3+txt4+txt5+txt6);
            arrayTA.add(ar);
        }
        
        for(JTextArea ta: arrayTA){
            pnel.add(ta);
        }
        
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.YELLOW);
        btn.setFocusPainted(false);
        pnelF.add(btn);
        JScrollPane jp = new JScrollPane(pnel);
        frm.add(pnelR, BorderLayout.EAST);
        frm.add(pnelL, BorderLayout.WEST);
        frm.add(pnelF, BorderLayout.SOUTH);
        frm.add(jp, BorderLayout.CENTER);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        btn.addMouseListener(new MouseAdapter() {
           
        });
    }
    

//    public static void main(String[] args) {
//        new MainFrame().draw();
//    }
}
