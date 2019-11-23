/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author admin
 */
public class NewAdd {

    private JFrame fr;
    private JPanel p1, p2, p3, p4, p5, pnel;
    private JButton bn1, bn2, bn3;
    private JLabel l1, l2, l3;
    private int num = 10;
    ArrayList<JLabel> arrayJP = new ArrayList<JLabel>();

    public void init() {
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        pnel = new JPanel();
        l1 = new JLabel("HOMEWORK");
//        l1.setVerticalAlignment(JLabel.TOP);
        l1.setPreferredSize(new Dimension(250, 100));
        l1.setFont(new Font("Verdana", Font.PLAIN, 32));
        l1.setForeground(new Color(240, 240, 240));
//        l1.setBackground(new Color(100, 20, 70));
        fr = new JFrame("Button Sample");
//        topic2 temp = new topic2();
        pnel.setLayout(new GridLayout(99, 1));
//        for (int i = 1; i < 100; i++) {
//            JLabel lbel = new JLabel();
        JLabel lbel = new JLabel();
//            lbel.setText(temp.getsDescrip());
//            arrayJP.add(lbel);
//        }
//        for (JLabel test : arrayJP) {
//            pnel.add(test);
//        }
        fr.add(new JScrollPane(pnel));
        fr.setTitle("AddWork");
        fr.setSize(500, 1000);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bn1 = new JButton("B1");
        bn2 = new JButton("B2");
        bn3 = new JButton("B3");
        p1.add(l1);
        p2.add(bn2);
        p2.add(bn3);
        p2.setPreferredSize(new Dimension(250, 100));
        p1.setPreferredSize(new Dimension(460, 150));
//        p1.setBackground(Color.yellow);
        p2.setSize(400, 500);
        p1.setBackground(Color.blue);
        p3.setBackground(Color.gray);
        p5.add(p3);
        p4.add(p1);
        p3.setPreferredSize(new Dimension(460, 1000));
        fr.setLayout(new BorderLayout());
        fr.add(p4, BorderLayout.NORTH);
        fr.add(p2, BorderLayout.SOUTH);
//        fr.add(p5);
//        fr.add(new JScrollPane(pnel));

        fr.setVisible(true);
        bn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                topic2 temp = new topic2();
                ArrayList<JLabel> arrayJP = new ArrayList<JLabel>();
                lbel.setText("game");
                arrayJP.add(lbel);
                for (JLabel test : arrayJP) {
                    pnel.add(test);
                }
                fr.add(new JScrollPane(pnel));
                fr.setVisible(true);
            }
        });
        bn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                topic2 temp = new topic2();
                ArrayList<JLabel> arrayJP = new ArrayList<JLabel>();
                JLabel lbel = new JLabel();
                lbel.setText("sdsfsfd");
                arrayJP.add(lbel);
                fr.add(new JScrollPane(pnel));
                for (JLabel test : arrayJP) {
                    pnel.add(test);
                }
            }
        });
    }

    public static void main(String args[]) {
        NewAdd b = new NewAdd();
        b.init();
    }

}
