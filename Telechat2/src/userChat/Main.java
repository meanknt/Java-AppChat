/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userChat;

import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author kit
 */
public class Main {

    ArrayList<Character> arrayChr = new ArrayList<Character>();
    private String text, txt = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    private String txt2 = "asdfghsafsadfs", TestText;
    private char[] chr1, chr = txt.toCharArray();
    private char[] chr2 = {'a', 's', 'd', 'f'};
    private int x = 0, i = 0, num;
    private double dou = 3.14;

    public void check() {
        JFrame frm = new JFrame();
        frm.setSize(500, 300);
        
//        num = txt.length() % 78;
//        if (num == 0) {
//            for (int j = 0; j < txt.length() / 78; j++) {
//                for (int k = 0; k < 78; k++) {
//                    arrayChr.add(chr[x]);
//                    x++;
//                }
//                StringBuilder result = new StringBuilder(arrayChr.size());
//                for (Character c : arrayChr) {
//                    result.append(c);
//                }
//                text += result.toString();
//                arrayChr.removeAll(arrayChr);
//            }
//        } else {
//            for (int j = 0; j < 10; j++) {
//
//            }
//        }

//        chr1 = txt2.toCharArray();
//        while (i < txt2.length()) {
//            arrayChr.add(chr1[i]);
//            i++;
//        }
//        StringBuilder result = new StringBuilder(arrayChr.size());
//        for (Character c : arrayChr) {
//            result.append(c);
//        }
//        String output = result.toString();
//        System.out.println(i);
//        System.out.println(result);
//        arrayChr.removeAll(arrayChr);
//        System.out.println(arrayChr);
    }

    public static void main(String[] args) {
        new Main().check();
    }
}
