package userChat;


import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private static int amountUser = 0;
    private int userId;
    private InputStream streamIn;
    private PrintStream streamOut;
    private String name;
    private Socket client;
//	  private String color;

    public User(Socket client, String name) throws IOException {
        this.streamOut = new PrintStream(client.getOutputStream());
        this.streamIn = client.getInputStream();
        this.client = client;
        this.name = name;
        this.userId = amountUser;
//	    this.color = ColorInt.getColor(this.userId);
        amountUser += 1;
    }

    //Getter method
    public PrintStream getOutStream() {
        return this.streamOut;
    }

    public InputStream getInputStream() {
        return this.streamIn;
    }

    public String getName() {
        return this.name;
    }

//	  // Print user with his color
    public String toString() {
        return this.getName();
    }
}
