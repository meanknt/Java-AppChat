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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kit
 */
public class MainFrame {

    ArrayList<String> arrayTXT = new ArrayList<String>();
    ArrayList<JTextArea> arrayTA = new ArrayList<JTextArea>();
    JFrame frm = new JFrame("Work");
    JPanel pnel, pnelR, pnelL, pnelF;
    JTextArea ar, ar1, ar2, ar3, ar4, ar5;
    JButton btn;
    private String temp, txt, temp2, temp3;
    private int num;
    private String txtD, txtM, txtY, txtTopic, txtContent;

    public void draw() {
        show();
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

        for (int j = 0; j < num; j++) {
            String Arrtxt = arrayTXT.get(j);
            try {

                JSONArray data = new JSONArray(Arrtxt);

                ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();

                HashMap<String, String> map;

                for (int i = 0; i < data.length(); i++) {

                    JSONObject c = data.getJSONObject(i);

                    map = new HashMap<String, String>();

                    map.put("Month", c.getString("Month"));

                    map.put("Description", c.getString("Description"));

                    map.put("Year", c.getString("Year"));
                    map.put("Day", c.getString("Day"));
                    map.put("Subject", c.getString("Subject"));

                    myArrList.add(map);

                }

                for (int i = 0; i < myArrList.size(); i++) {
                    txtD = myArrList.get(i).get("Day").toString();
                    txtY = myArrList.get(i).get("Year").toString();
                    txtM = myArrList.get(i).get("Month").toString();
                    txtTopic = myArrList.get(i).get("Subject").toString();
                    txtContent = myArrList.get(i).get("Description").toString();

                }

            } catch (Exception e) {

// TODO Auto-generated catch block
                e.printStackTrace();

            }
            ar = new JTextArea();
            ar.setFont(new Font("Arial, sans-serif", Font.PLAIN, 15));
            ar.setAutoscrolls(true);
            ar.setLineWrap(true);
            ar.setEnabled(true);
            ar.setEditable(true);
            ar.setText(txtTopic +"\n"+ "   " +txtD + txtY + txtM +"\n"+"   " +  txtContent);
            arrayTA.add(ar);
        }

        for (JTextArea ta : arrayTA) {
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

    public void show() {
        String sql_read = "SELECT * FROM addwork";
        My_CNK db = new My_CNK();
        ResultSet rs;
        try {
            Connection con = db.ConnectDataBase();
            rs = con.createStatement().executeQuery(sql_read);
            //สร้าง Statement แล้วเอาข้อมูลที่อ่านได้ยัดเข้า แล้วเก็บในตัวแปร rs
//             while (rs.next()) {
//                 num2++;
//             }
//             System.out.println(num2);
//             rs = con.createStatement().executeQuery(sql_read);
            while (rs.next()) { //รันข้อมูลไปเลื่อยๆจนข้อมูลหมด
                txt = rs.getString(1);
                arrayTXT.add(txt);
                num++;                
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new MainFrame().draw();
    }
}
