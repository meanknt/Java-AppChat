package userChat;


import java.awt.Image;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import java.util.Scanner;

class UserHandler implements Runnable {

    private Server server;
    private User user;
    private String mode = "Mode -> <span style='color:#447728'>Global</span>";

    public UserHandler(Server server, User user) {
        this.server = server;
        this.user = user;
        this.server.broadcastAllUsers();
        this.server.broadcastConnect(" has connect", user);
    }

    public void run() {
        String message;
        Scanner scn = new Scanner(this.user.getInputStream());
        while (scn.hasNextLine()) {
            message = scn.nextLine();
            // Emoji message  
//      message = message.replace(":)", "<img src='http://4.bp.blogspot.com/-ZgtYQpXq0Yo/UZEDl_PJLhI/AAAAAAAADnk/2pgkDG-nlGs/s1600/facebook-smiley-face-for-comments.png'>");
//      message = message.replace(":D", "<img src='http://2.bp.blogspot.com/-OsnLCK0vg6Y/UZD8pZha0NI/AAAAAAAADnY/sViYKsYof-w/s1600/big-smile-emoticon-for-facebook.png'>");
            message.toLowerCase();

            if (message.contains("whoami")) {
                server.serverMessageToUser("Your name is <span style='color:#752364;'><b>" + user.getName() + "</b></span>", user);
            } else if (message.equals("whoami")) {
                server.serverMessageToUser("Your name is <span style='color:#752364;'><b>" + user.getName() + "</b></span>", user);
            } else {
                // Update user list
                server.broadcastMessages(message, user);
            }
        }
        // End of Thread
        server.removeUser(user);
        server.broadcastDisconnect(" has disconnect", user);
        this.server.broadcastAllUsers();
        scn.close();
    }
}
