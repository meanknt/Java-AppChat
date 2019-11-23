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
            message.toLowerCase();
            // Emoji message
            message = message.replace("dance", "<img src='https://giftergo.com/wp-content/uploads/2018/04/Emoji-30.gif' width='60' height='60'>");
            message = message.replace("555", "<img src='https://thumbs.gfycat.com/WeeSizzlingAiredale-size_restricted.gif' width='60' height='60'>");
            message = message.replace("LOL", "<img src='https://thumbs.gfycat.com/WeeSizzlingAiredale-size_restricted.gif' width='60' height='60'>");
            message = message.replace("sad", "<img src='https://media.tenor.com/images/3ef3153dcf281e60ac8ebad488449227/tenor.gif' width='40' height='40'>");
            message = message.replace("T-T", "<img src='https://media.tenor.com/images/3ef3153dcf281e60ac8ebad488449227/tenor.gif' width='40' height='40'>");
            message = message.replace("love", "<img src='https://media.tenor.com/images/37dc12243e77e7127bd891a2eae3d91a/tenor.gif' width='30' height='30'>");
            message = message.replace("?", "<img src='https://i.pinimg.com/originals/57/04/68/570468282d5ce15d740106535b61f533.gif' width='40' height='40'>");
            message = message.replace(":D", "<img src='https://static.wixstatic.com/media/2bca44_59331ee9a752461db3cb741e791b1b7a~mv2.gif' width='45' height='45'>");
            message = message.replace("smile", "<img src='https://i.pinimg.com/originals/05/f3/5d/05f35d28a2abfc34e52946d6727ab37f.gif' width='40' height='40'>");
            message = message.replace("sleep", "<img src='https://i.pinimg.com/originals/96/24/9a/96249adaaa51c0c9073368e868811896.gif' width='45' height='45'>");
            message = message.replace("angry", "<img src='https://media.tenor.com/images/4b7138607c07dccdd5a688999db1d50f/tenor.gif' width='50' height='50'>");
             message = message.replace("-*-", "<img src='https://media.tenor.com/images/4b7138607c07dccdd5a688999db1d50f/tenor.gif' width='50' height='50'>");
            message = message.replace("happy", "<img src='https://i.pinimg.com/originals/37/01/75/37017584561a74ca1a6415a6a5bab76c.gif' width='50' height='50'>");
            message = message.replace("dap", "<img src='https://media.tenor.com/images/b89e368037ccf135b1613983403a0529/tenor.gif' width='60' height='60'>");
            message = message.replace("wow", "<img src='https://i.pinimg.com/originals/bc/07/c5/bc07c5dc154a9dcb7053cf6320f92c3f.gif' width='70' height='70'>");            
             
            if (message.contains("emoji")){
                server.serverMessageToUser("<b>Server---> </b><span style='color:#752364;'><b>อีโมจิ---> dance, 555, haha, sad, T-T, love, ?, -*-<br>"
                        + ", :D, smile, sleep, angry, happy, dap, wow</b></span>", user);
            }
            else if(message.equals("emoji")){
                server.serverMessageToUser("<b>Server---> </b> <span style='color:#752364;'><b>อีโมจิ---> dance, 555, haha, sad, T-T, love, ?<br>"
                        + ", :D, smile, sleep, angry, happy, dap, wow</b></span>", user);  
                        }
            if (message.contains("!help")){
                server.serverMessageToUser("<b>Server---> </b> <span style='color:#752364;'><b>ดูชื่อตัวเองพิม whoami<br>ดูอีโมจิ พิม !emoji </b></span>", user);
            }else if(message.equals("!help")){
                server.serverMessageToUser("<b>Server---> </b> <span style='color:#752364;'><b>ดูชื่อตัวเองพิม whoami<br>ดูอีโมจิ พิม !emoji </b></span>", user);
            }

            if (message.contains("whoami")) {
                server.serverMessageToUser("<b>Server---> </b> Your name is <span style='color:#752364;'><b>" + user.getName() + "</b></span>", user);
            } else if (message.equals("whoami")) {
                server.serverMessageToUser("<b>Server---> </b> Your name is <span style='color:#752364;'><b>" + user.getName() + "</b></span>", user);
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
