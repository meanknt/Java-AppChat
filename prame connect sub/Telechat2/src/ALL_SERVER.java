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
        Thread t1 = new Thread(new Server(444));
        Thread t2 = new Thread(new Server(555));
        t1.start();
        t2.start();
    }
    
}
