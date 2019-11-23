/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
import userChat.*;

public class ALL_SERVER {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Server(444, "OOP"));
        Thread t2 = new Thread(new Server(555, "WEB"));
        Thread t3 = new Thread(new Server(666, "NW"));
        Thread t4 = new Thread(new Server(777, "DATA"));
        Thread t5 = new Thread(new Server(888, "ENG"));
        Thread t6 = new Thread(new Server(999, "MIS"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
    
}
