package userChat;


import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.net.Socket;
import java.nio.*;
import java.sql.SQLException;
import java.util.*;

import javax.imageio.ImageIO;

public class Server implements Runnable{

    private int port;
    private String subject;
    private ArrayList<User> clients;
    private ServerSocket server;

    public Server(int port, String subject) {
        this.port = port;
        this.subject = subject;
        this.clients = new ArrayList<User>();
    }
//    public void run() throws IOException {
//        try{
//           server = new ServerSocket(port);
//            System.out.println("Port " + this.port + " is now open.");
//            while (true) {
//            // accepts a new client
//                Socket client = server.accept();
//                // get nickname of newUser
//                Scanner scn = new Scanner(client.getInputStream());
//                String nickname = scn.nextLine();
//                nickname = nickname.replace(",", "");
//                nickname = nickname.replace(" ", "_");
//                System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());
//                // create new User
//                User newUser = new User(client, nickname);
//                // add newUser message to Arraylist
//                this.clients.add(newUser);
//                // Welcome msg
//                newUser.getOutStream().println(newUser.toString());
//                // Dreate a new thread for newUser incoming messages handling
//                Thread t1 = new Thread(new UserHandler(this, newUser));
//                t1.start();
//            } 
//        } catch(Exception x){
//            System.out.println(x);
//        }
//        
//    }
    // Delete a user from the list

    public void removeUser(User user) {
        this.clients.remove(user);
    }
    // Send incoming msg to all Users

    public void broadcastMessages(String msg, User userSender) {
        for (User client : this.clients) {
            client.getOutStream().println("<b>" + userSender.toString() + "</b>" + "<span> : " + msg + "</span>");
        }
    }
     public void broadcastConnect(String msg, User userSender) {
        for (User client : this.clients) {
            client.getOutStream().println("<span style='color:green;'><b>" + userSender.toString()+ "</b>" +msg+"</span>");
        }
    }
     public void broadcastDisconnect(String msg, User userSender) {
        for (User client : this.clients) {
            client.getOutStream().println("<span style='color:red;'><b>" + userSender.toString()+ "</b>" +msg+"</span>");
        }
    }
     
    // Send list of clients to all Users

    public void broadcastAllUsers() {
        for (User client : this.clients) {
            client.getOutStream().println(this.clients);
        }
    }

    //Server send message to user
    public void serverMessageToUser(String msg, User userSender) {
        userSender.getOutStream().println(msg);
    }

    /*public static void main(String[] args) throws IOException {
        Server s = new Server(12345);
        s.run();
    }*/

    @Override
    public void run() {
        try{
           server = new ServerSocket(port);
            System.out.println("Port " + this.port +"("+ subject +")"+ " open.");
            while (true) {
            // accepts a new client
                Socket client = server.accept();
                // get nickname of newUser
                Scanner scn = new Scanner(client.getInputStream());
                String nickname = scn.nextLine();
                nickname = nickname.replace(",", "");
                nickname = nickname.replace(" ", "_");
                System.out.println(subject+" new Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());
                // create new User
                User newUser = new User(client, nickname);
                // add newUser message to Arraylist
                this.clients.add(newUser);
                // Welcome msg
                newUser.getOutStream().println(newUser.toString());
                // Dreate a new thread for newUser incoming messages handling
                Thread t1 = new Thread(new UserHandler(this, newUser));
                t1.start();
            } 
        } catch(Exception x){
            System.out.println(x);
        }
    }
  
}
