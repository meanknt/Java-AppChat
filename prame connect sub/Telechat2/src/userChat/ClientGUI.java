package userChat;

import static com.sun.tools.javac.code.TypeAnnotationPosition.field;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.*;
import java.nio.ByteBuffer;
import java.text.AttributedString;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ClientGUI extends Thread{

    public JButton jsbtn;
    final JTextPane jtextFilDiscu = new JTextPane();
    final JTextPane jtextListUsers = new JTextPane();
    final JTextField jtextInputChat = new JTextField();
    private JFrame jfr;
//    private String oldMsg = "";
    private Thread read;
    private String serverName;
    private int PORT;
    private String name;
    BufferedReader input;
    PrintWriter output;
    Socket server;
    private Font font;
    private int privc = 0;
    private String priname = "";
    Calendar d;
    private int sec;
    private int min;
    private int hour;
    private String myname;
    private JPanel p1, ptop;

    public ClientGUI(int port) {
        this.PORT = port;
    }

    public void init() {
        Main m = new Main(); //For get name
        p1 = new JPanel();
        p1.setSize(700, 500);
        p1.setLayout(null);
        p1.setBackground(Color.DARK_GRAY);
        this.serverName = "localhost";
        this.name = m.name;
        String fontfamily = "Arial, sans-serif";
        font = new Font(fontfamily, Font.PLAIN, 15);
        jfr = new JFrame("ClassroomChat");
        jfr.getContentPane().setLayout(null);
        jfr.setSize(700, 490);
        jfr.setResizable(false);
        //jfr.setShape(new RoundRectangle2D.Double(0,0, 1000,700, 50,50));
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jtextFilDiscu.setBounds(25, 25, 490, 320);
        jtextFilDiscu.setFont(font);
        jtextFilDiscu.setMargin(new Insets(6, 6, 6, 6));
        jtextFilDiscu.setEditable(false);
        JScrollPane jtextFilDiscuSP = new JScrollPane(jtextFilDiscu);
        jtextFilDiscuSP.setBounds(8, 25, 520, 340);
        jtextFilDiscu.setContentType("text/html");
        jtextFilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
             
        jtextListUsers.setBounds(520, 25, 138, 320);
        jtextListUsers.setFont(font);
        jtextListUsers.setMargin(new Insets(6, 6, 6, 6));
        jtextListUsers.setEditable(false);
        //jtextListUsers.setBackground(Color.DARK_GRAY);
        jtextListUsers.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        JScrollPane jsplistuser = new JScrollPane(jtextListUsers);
        jsplistuser.setBounds(537, 25, 138, 235);
        jtextListUsers.setContentType("text/html");
        jtextListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        
        
        // Field message user input
        jtextInputChat.setBounds(0, 350, 400, 50);
        jtextInputChat.setFont(font);
        jtextInputChat.setMargin(new Insets(6, 6, 6, 6));
        final JScrollPane jtextInputChatSP = new JScrollPane(jtextInputChat);
        jtextInputChatSP.setBounds(8, 380, 520, 50);
        jtextInputChatSP.getViewport().getView().setBackground(Color.DARK_GRAY);
        jtextInputChatSP.getViewport().getView().setForeground(Color.YELLOW);
        jtextInputChatSP.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        // Send Button
        //final JButton jsbtn = new JButton("Send");
        jsbtn = new JButton("Send");
        jsbtn.setFont(font);
        jsbtn.setBounds(537, 387, 138, 35);
        jsbtn.setBorder(new RoundedBorder(30)); //10 is the radius       
        jsbtn.setForeground(Color.YELLOW);
        jsbtn.setOpaque(false);
        jsbtn.setContentAreaFilled(false);
        jsbtn.setFocusPainted(false);

        // Disconnect Button
        final JButton jsbtndeco = new JButton("Disconnect");
        jsbtndeco.setFont(font);
        jsbtndeco.setBounds(537, 333, 138, 35);
        jsbtndeco.setBorder(new RoundedBorder(30)); //10 is the radius       
        jsbtndeco.setForeground(Color.YELLOW);
        jsbtndeco.setOpaque(false);
        jsbtndeco.setContentAreaFilled(false);
        jsbtndeco.setFocusPainted(false);

        final JButton ShowWork = new JButton("Show");
        ShowWork.setFont(font);
        ShowWork.setBounds(537, 280, 138, 35);
        ShowWork.setBorder(new RoundedBorder(30)); //10 is the radius       
        ShowWork.setForeground(Color.YELLOW);
        ShowWork.setOpaque(false);
        ShowWork.setContentAreaFilled(false);
        ShowWork.setFocusPainted(false);
        

        jtextInputChat.addKeyListener(new KeyAdapter() {
            // send message on Enter
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }

            }
        });
        // Click on send button
        jsbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                sendMessage();
            }
        });

        jsbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jsbtn.setBorder(new RoundedBorder(30)); //10 is the radius       
                jsbtn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jsbtn.setBorder(new RoundedBorder(30)); //10 is the radius       
                jsbtn.setForeground(Color.YELLOW);
            }
        });
        // Connection view
        final JButton jcbtn = new JButton("Connect");
        jcbtn.setBounds(575, 380, 100, 40);
        jcbtn.setBorder(new RoundedBorder(30)); //10 is the radius       
        jcbtn.setForeground(Color.YELLOW);
        jcbtn.setOpaque(false);
        jcbtn.setContentAreaFilled(false);
        jcbtn.setFocusPainted(false);

        jtextFilDiscu.setBackground(Color.WHITE);
        jtextListUsers.setBackground(Color.WHITE);
        p1.add(jtextFilDiscuSP);
        p1.add(jsplistuser);
        p1.add(jtextInputChatSP);
        p1.add(jsbtn);
        p1.add(jsbtndeco);
        p1.add(ShowWork);
        jfr.add(p1);
        jfr.setVisible(true);

        try {
            appendToPane(jtextFilDiscu, "Connecting to " + serverName + " on port " + PORT + "...");
            server = new Socket(serverName, PORT);//ip, port
            appendToPane(jtextFilDiscu, "<span>Connected to " + server.getRemoteSocketAddress());
            input = new BufferedReader(new InputStreamReader(server.getInputStream()));
            output = new PrintWriter(server.getOutputStream(), true);
            // send nickname to server
            output.println(name);
            // create new Read Thread
            read = new Read();
            read.start();
        } catch (IOException ex) {
            appendToPane(jtextFilDiscu, "<span>Could not connect to Server</span>");
            JOptionPane.showMessageDialog(jfr, ex.getMessage());
        }

//connect event
        jcbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
//          mynameFlag = false;
                    appendToPane(jtextFilDiscu, "Connecting to " + serverName + " on port " + PORT + "...");
                    server = new Socket(serverName, PORT);//ip, port
                    appendToPane(jtextFilDiscu, "<span>Connected to "
                            + server.getRemoteSocketAddress());
                    input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    output = new PrintWriter(server.getOutputStream(), true);
                    // send nickname to server
                    output.println(name);
                    // create new Read Thread
                    read = new Read();
                    read.start();
                    //create new ReadImg Thread
                    //สร้างกล่องเขียนและส่ง
                    p1.remove(jcbtn);
                    p1.add(jtextInputChatSP);
                    p1.add(jsbtndeco);
                    p1.add(jsbtn);
                    p1.add(ShowWork);
                    p1.revalidate();
                    p1.repaint();
                    jtextFilDiscu.setBackground(Color.WHITE);
                    jtextListUsers.setBackground(Color.DARK_GRAY);
                    jtextListUsers.setForeground(Color.YELLOW);
                } catch (Exception ex) {
                    appendToPane(jtextFilDiscu, "<span>Could not connect to Server</span>");
                    JOptionPane.showMessageDialog(jfr, ex.getMessage());
                }
            }
        });
        //Disconnect event
        jsbtndeco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
//    	mynameFlag = false;
                p1.add(jcbtn);
                p1.remove(jtextInputChatSP);
                p1.remove(jsbtndeco);
                p1.remove(jsbtn);
                p1.remove(ShowWork);
                p1.revalidate();
                p1.repaint();
                read.interrupt();
                jtextListUsers.setText(null);
                jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
                jtextListUsers.setBackground(Color.LIGHT_GRAY);
                appendToPane(jtextFilDiscu, "<span style='margin-top:10px;'>See You Again (Telechat)</span>");
                output.close();
            }
        });
        jsbtndeco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jsbtndeco.setBorder(new RoundedBorder(30)); //10 is the radius       
                jsbtndeco.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jsbtndeco.setBorder(new RoundedBorder(30)); //10 is the radius       
                jsbtndeco.setForeground(Color.YELLOW);
            }
        });

        ShowWork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                MainFrame frm = new MainFrame();
                frm.draw();
            }
        });
        
        ShowWork.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ShowWork.setBorder(new RoundedBorder(30)); //10 is the radius       
                ShowWork.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ShowWork.setBorder(new RoundedBorder(30)); //10 is the radius       
                ShowWork.setForeground(Color.YELLOW);
            }
        });
    }


    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

    }

//    public void init() {
//        this.serverName = "127.0.0.1";
//        //this.PORT = 12345;
//        this.name = "nickname";
//        String fontfamily = "Arial, sans-serif";
//        font = new Font(fontfamily, Font.PLAIN, 15);
//        jfr = new JFrame("ClassroomChat");
//        jfr.getContentPane().setLayout(null);
//        jfr.setSize(700, 500);
//        jfr.setResizable(false);
//        jfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jtextFilDiscu.setBounds(25, 25, 490, 320);
//        jtextFilDiscu.setFont(font);
//        jtextFilDiscu.setMargin(new Insets(6, 6, 6, 6));
//        jtextFilDiscu.setEditable(false);
//        JScrollPane jtextFilDiscuSP = new JScrollPane(jtextFilDiscu);
//        jtextFilDiscuSP.setBounds(25, 25, 490, 320);
//        jtextFilDiscu.setContentType("text/html");
//        jtextFilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
//        jtextListUsers.setBounds(520, 25, 156, 320);
//        jtextListUsers.setEditable(true);
//        jtextListUsers.setFont(font);
//        jtextListUsers.setMargin(new Insets(6, 6, 6, 6));
//        jtextListUsers.setEditable(false);
//        JScrollPane jsplistuser = new JScrollPane(jtextListUsers);
//        jsplistuser.setBounds(520, 25, 156, 320);
//        jtextListUsers.setContentType("text/html");
//        jtextListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
//        // Field message user input
//        jtextInputChat.setBounds(0, 350, 400, 50);
//        jtextInputChat.setFont(font);
//        jtextInputChat.setMargin(new Insets(6, 6, 6, 6));
//        final JScrollPane jtextInputChatSP = new JScrollPane(jtextInputChat);
//        jtextInputChatSP.setBounds(25, 350, 650, 50);
//        // Send Button
//        final JButton jsbtn = new JButton("Send");
//        jsbtn.setFont(font);
//        jsbtn.setBounds(575, 410, 100, 35);
//        // Disconnect Button
//        final JButton jsbtndeco = new JButton("Disconnect");
//        jsbtndeco.setFont(font);
//        jsbtndeco.setBounds(25, 410, 130, 35);
//
//        jtextInputChat.addKeyListener(new KeyAdapter() {
//            // send message on Enter
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    sendMessage();
//                }
//
//            }
//        });
//        // Click on send button
//        jsbtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                sendMessage();
//            }
//        });
//        // Connection view
//        final JTextField jtfName = new JTextField(this.name);
//        final JTextField jtfport = new JTextField(Integer.toString(this.PORT));
//        final JTextField jtfAddr = new JTextField(this.serverName);
//        final JButton jcbtn = new JButton("Connect");
//        jtfName.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));
//        jtfport.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));
//        jtfAddr.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));
//        jcbtn.setFont(font);
//        jtfAddr.setBounds(10, 380, 135, 40);
//        jtfName.setBounds(375, 380, 135, 40);
//        jtfport.setBounds(200, 380, 135, 40);
//        jcbtn.setBounds(575, 380, 100, 40);
//        jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
//        jtextListUsers.setBackground(Color.LIGHT_GRAY);
//        jfr.add(jcbtn);
//        jfr.add(jtextFilDiscuSP);
//        jfr.add(jsplistuser);
//        //jfr.add(jtfName);
//        //jfr.add(jtfport);
//        //jfr.add(jtfAddr);
//        jfr.setVisible(true);
////connect event
//        jcbtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                try {
////          mynameFlag = false;
//                    name = jtfName.getText();
//                    String port = jtfport.getText();
//                    serverName = jtfAddr.getText();
//                    PORT = Integer.parseInt(port);
//                    appendToPane(jtextFilDiscu, "Connecting to " + serverName + " on port " + PORT + "...");
//                    server = new Socket(serverName, PORT);//ip, port
//                    appendToPane(jtextFilDiscu, "<span>Connected to "
//                            + server.getRemoteSocketAddress());
//                    input = new BufferedReader(new InputStreamReader(server.getInputStream()));
//                    output = new PrintWriter(server.getOutputStream(), true);
//                    // send nickname to server
//                    output.println(name);
//                    // create new Read Thread
//                    read = new Read();
//                    read.start();
//                    //create new ReadImg Thread
//                    jfr.remove(jtfName);
//                    jfr.remove(jtfport);
//                    jfr.remove(jtfAddr);
//                    jfr.remove(jcbtn);
//                    jfr.add(jsbtn);
//                    jfr.add(jtextInputChatSP);
//                    jfr.add(jsbtndeco);
//                    jfr.revalidate();
//                    jfr.repaint();
//                    jtextFilDiscu.setBackground(Color.WHITE);
//                    jtextListUsers.setBackground(Color.WHITE);
//                } catch (Exception ex) {
//                    appendToPane(jtextFilDiscu, "<span>Could not connect to Server</span>");
//                    JOptionPane.showMessageDialog(jfr, ex.getMessage());
//                }
//            }
//        });
//        //Disconnect event
//        jsbtndeco.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                privc = 0;
////    	mynameFlag = false;
//                //jfr.add(jtfName);
//                //jfr.add(jtfport);
//                //jfr.add(jtfAddr);
//                jfr.add(jcbtn);
//                jfr.remove(jsbtn);
//                jfr.remove(jtextInputChatSP);
//                jfr.remove(jsbtndeco);
//                jfr.revalidate();
//                jfr.repaint();
//                read.interrupt();
//                jtextListUsers.setText(null);
//                jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
//                jtextListUsers.setBackground(Color.LIGHT_GRAY);
//                appendToPane(jtextFilDiscu, "<span style='margin-top:10px;'>See You Again (Telechat)</span>");
//                output.close();
//            }
//        });
//    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void MainWindow() {
        JFrame jfm = new JFrame("Class");
        ptop = new JPanel();
        final JTextPane Chat = new JTextPane();
        final JTextPane ListUsers = new JTextPane();
        final JTextField InputChat = new JTextField();

        jfm.setSize(700, 500);
        ptop.setLayout(null);
        ptop.setBackground(Color.red);
        InputChat.setBounds(25, 390, 100, 50);
        //Chat.setBounds(25, 25, 340, 390);
        JScrollPane ChatScroll = new JScrollPane(Chat);
        ChatScroll.setBounds(25, 25, 490, 320);
        ptop.add(ChatScroll);
        ptop.add(InputChat);
        jfm.add(ptop);
        jfm.setVisible(true);
    }

//    public void connect(){
//        
//    }
//-----------------------------------------------------------------------------------------------------------------------------------------------
    public JFrame getJfr() {
        return jfr;
    }
    // check if all field are not empty

    public class TextListener implements DocumentListener {

        JTextField jtf1;
        JTextField jtf2;
        JTextField jtf3;
        JButton jcbtn;

        public TextListener(JTextField jtf1, JTextField jtf2, JTextField jtf3, JButton jcbtn) {
            this.jtf1 = jtf1;
            this.jtf2 = jtf2;
            this.jtf3 = jtf3;
            this.jcbtn = jcbtn;
        }

        public void changedUpdate(DocumentEvent e) {
        }

        public void removeUpdate(DocumentEvent e) {
            if (jtf1.getText().trim().equals("")
                    || jtf2.getText().trim().equals("")
                    || jtf3.getText().trim().equals("")) {
                jcbtn.setEnabled(false);
            } else {
                jcbtn.setEnabled(true);
            }
        }

        public void insertUpdate(DocumentEvent e) {
            if (jtf1.getText().trim().equals("")
                    || jtf2.getText().trim().equals("")
                    || jtf3.getText().trim().equals("")) {
                jcbtn.setEnabled(false);
            } else {
                jcbtn.setEnabled(true);
            }
        }

    }
    // Send message method

    public void sendMessage() {
        try {
            String message = jtextInputChat.getText().trim();
            if (message.equals("")) {
                return;
            } else {
                output.println(" " + message);
                jtextInputChat.requestFocus();
                jtextInputChat.setText(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ClientGUI client = new ClientGUI(444);
        client.init();
    }

    // Read new incoming messages
    class Read extends Thread {

        public void run() {
            String message;
            ArrayList<String> ListUser;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    d = Calendar.getInstance();
                    sec = d.get(Calendar.SECOND);
                    min = d.get(Calendar.MINUTE);
                    hour = d.get(Calendar.HOUR_OF_DAY);
                    message = input.readLine();
                    //System.out.println(message);
                    //Append user to JTextPane
                    if (message != null) {
                        if (message.charAt(0) == '[') {
                            message = message.substring(1, message.length() - 1);
                            ListUser = new ArrayList<String>(Arrays.asList(message.split(", ")));
                            jtextListUsers.setText(null);
                            //List all user to JPane
                            appendToPane(jtextListUsers, "&nbsp;Online");
                            for (String user : ListUser) {
                                appendToPane(jtextListUsers, "@" + user);
                            }
                        } else {
                            //Append Message To JTextPane
                            message = "<p>" + message + "  " + "<span style='font-size:8px'>" + String.format("%02d:%02d:%02d", hour, min, sec) + "</span>" + "</p>";
                            appendToPane(jtextFilDiscu, message);
                        }
                    }
                } catch (IOException ex) {
                    System.err.println("Failed to parse incoming message");
                }
            }
        }
    }

    // Send html to pane
    private void appendToPane(JTextPane tp, String msg) {
        //Setting
        HTMLDocument doc = (HTMLDocument) tp.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
        try {
            editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
            tp.setCaretPosition(doc.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}